package com.ftmnet.homepage.entity;

import com.ftmnet.homepage.configuration.RandomIdentifierGenerator;
import com.ftmnet.homepage.dto.mapper.EntityObject;
import com.ftmnet.homepage.dto.mapper.IdMapping;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Category implements IdMapping, EntityObject {

    public static final String DEFAULT_NAME = "Category name";

    @Id
    @GenericGenerator(name = "random_id", type = RandomIdentifierGenerator.class)
    @GeneratedValue(generator = "random_id", strategy = GenerationType.IDENTITY)
    @Getter private long id;

    @Column(nullable = false)
    @Getter @Setter private String name;

    @ManyToOne
    @JoinColumn
    @Getter @Setter private User user;

    @OneToMany(mappedBy = "category")
    @Getter @Setter private List<Item> items;
}
