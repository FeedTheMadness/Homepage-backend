package com.ftmnet.homepage.entity;

import com.ftmnet.homepage.configuration.RandomIdentifierGenerator;
import com.ftmnet.homepage.dto.mapper.EntityObject;
import com.ftmnet.homepage.dto.mapper.IdMapping;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Item implements IdMapping, EntityObject {

    public static final String DEFAULT_NAME = "Item name";

    @Id
    @GenericGenerator(name = "random_id", type = RandomIdentifierGenerator.class)
    @GeneratedValue(generator = "random_id")
    @Getter private long id;

    @Column(nullable = false)
    @Getter @Setter private String name;

    @Column(nullable = true)
    @Getter @Setter private String url;

    @Column(nullable = true)
    @Getter @Setter private String imageUrl;

    @ManyToOne
    @JoinColumn(nullable = false)
    @Getter @Setter private Category category;
}
