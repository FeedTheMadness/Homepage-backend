package com.ftmnet.homepage.configuration;

import com.ftmnet.homepage.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain httpSecurity(HttpSecurity httpSecurity,
                                            RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter) throws Exception {
        return httpSecurity
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(requestHeaderAuthenticationFilter, AbstractPreAuthenticatedProcessingFilter.class)
                .authorizeHttpRequests(config -> config.anyRequest().permitAll())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider) {
        return new ProviderManager(preAuthenticatedAuthenticationProvider);
    }

    @Bean
    public RequestHeaderAuthenticationFilter preAuthenticatedProcessingFilter(AuthenticationManager authenticationManager) throws Exception {
        RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
        filter.setRequiresAuthenticationRequestMatcher(request -> request.getHeader("HTTP_REMOTE_USER") != null);
        filter.setPrincipalRequestHeader("HTTP_REMOTE_USER");
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider(UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsService) {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsServiceWrapper(UserService userService) {
        UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper = new UserDetailsByNameServiceWrapper<>();
        wrapper.setUserDetailsService(userService);
        return wrapper;
    }
}
