package com.masterspringboot.graphql.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.masterspringboot.graphql.dao.entity.Item;
import com.masterspringboot.graphql.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemMutation implements GraphQLMutationResolver {

    @Autowired
    private ItemService itemService;

    public Item createItem(final String type, final String model, final int price) {
        return this.itemService.createItem(type, model, price);
    }
}
