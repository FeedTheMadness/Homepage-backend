package com.ftmnet.homepage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@RequiredArgsConstructor
public class Category {

    @Id
    @Getter private final long id = 0L;
}
