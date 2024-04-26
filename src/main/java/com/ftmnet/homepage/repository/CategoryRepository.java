package com.ftmnet.homepage.repository;

import com.ftmnet.homepage.entity.Category;
import com.ftmnet.homepage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByUser(User user);

    Stream<Category> streamAllByUserOrUserNull(User user);

    Stream<Category> streamAllByUser(User user);

    Stream<Category> streamAllByUserNull();

}
