package com.ftmnet.homepage.repository;

import com.ftmnet.homepage.entity.Item;
import com.ftmnet.homepage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Stream<Item> streamAllByCategoryUserOrCategoryUserNull(User user);
}
