package com.ftmnet.homepage.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "UserEntity", indexes = {@Index(columnList = "name", unique = true)})
public class User implements UserDetails {

    public static final User ANONYMOUS_USER = User.builder()
            .name("Anon")
            .build();

    @Id
    @Getter private final long id = 0L;

    @Column(nullable = false, unique = true)
    @Getter @Setter private String name;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @Getter @Setter private List<Category> categories;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
