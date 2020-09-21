package com.masterspringboot.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.masterspringboot.graphql.dao.entity.Item;
import com.masterspringboot.graphql.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ItemQuery implements GraphQLQueryResolver {

    @Autowired
    private ItemService itemService;

    public List<Item> getItems(final int count) {
        return this.itemService.getAllItems(count);
    }

    public Optional<Item> getItem(final int id) {
        return this.itemService.getItem(id);
    }
}
