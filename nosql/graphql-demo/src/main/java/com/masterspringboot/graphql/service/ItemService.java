package com.masterspringboot.graphql.service;

import com.masterspringboot.graphql.dao.repository.ItemRepository;
import com.masterspringboot.graphql.dao.entity.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Item createItem(final String type, final String model, final int price) {
        final Item item = new Item();
        item.setType(type);
        item.setModel(model);
        item.setPrice(price);

        return this.itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public List<Item> getAllItems(final int count) {
        return this.itemRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Item> getItem(final int id) {
        return this.itemRepository.findById(id);
    }
}
