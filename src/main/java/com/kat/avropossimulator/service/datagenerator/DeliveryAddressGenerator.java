package com.kat.avropossimulator.service.datagenerator;

import com.kat.avropossimulator.model.DeliveryAddress;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class DeliveryAddressGenerator {

    private static final List<String> CITIES = Arrays.asList("London", "Manchester", "Liverpool");

    public DeliveryAddress getRandomDeliveryAddress() {
        Random random = new Random();
        return DeliveryAddress.newBuilder()
                .setAddressLine(UUID.randomUUID().toString())
                .setCity(CITIES.get(random.nextInt(CITIES.size())))
                .setCountry("UK")
                .setPostCode(UUID.randomUUID().toString())
                .setContactNumber(UUID.randomUUID().toString())
                .build();
    }
}
