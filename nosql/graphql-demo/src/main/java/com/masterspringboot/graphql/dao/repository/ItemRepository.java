package com.masterspringboot.graphql.dao.repository;

import com.masterspringboot.graphql.dao.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
