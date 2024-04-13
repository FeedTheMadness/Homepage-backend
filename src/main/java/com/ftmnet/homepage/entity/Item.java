package com.ftmnet.homepage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @Getter public final long id = 0L;

    @Getter public String name;

    @Getter public String url;
}
