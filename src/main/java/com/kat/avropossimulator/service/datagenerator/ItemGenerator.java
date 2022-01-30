package com.kat.avropossimulator.service.datagenerator;

import com.kat.avropossimulator.model.Item;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class ItemGenerator {

    public Item getRandomItem() {
        Random random = new Random();
        Double itemPrice = random.nextDouble() * 50;
        Integer itemQuantity = random.nextInt(5);
        Double totalValue = itemPrice * itemQuantity;
        return Item.newBuilder()
                .setItemCode(UUID.randomUUID().toString())
                .setItemDescription(UUID.randomUUID().toString())
                .setItemPrice(itemPrice)
                .setItemQuantity(itemQuantity)
                .setTotalValue(totalValue)
                .build();
    }
}
