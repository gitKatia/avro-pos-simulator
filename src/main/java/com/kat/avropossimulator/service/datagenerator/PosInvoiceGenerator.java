package com.kat.avropossimulator.service.datagenerator;

import com.kat.avropossimulator.model.DeliveryAddress;
import com.kat.avropossimulator.model.Item;
import com.kat.avropossimulator.model.PosInvoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class PosInvoiceGenerator {

    private static final List<String> PAYMENT_METHODS = Arrays.asList("card", "cash");
    private static final List<String> CUSTOMER_TYPES = Arrays.asList("prime", "standard");
    private static final List<String> DELIVERY_TYPES = Arrays.asList("delivery", "in store");

    private final DeliveryAddressGenerator deliveryAddressGenerator;
    private final ItemGenerator itemGenerator;

    public PosInvoice generatePosInvoice() {
        Random random = new Random();
        String customerType = CUSTOMER_TYPES.get(random.nextInt(CUSTOMER_TYPES.size()));
        String deliveryType = DELIVERY_TYPES.get(random.nextInt(DELIVERY_TYPES.size()));
        String paymentMethod = PAYMENT_METHODS.get(random.nextInt(PAYMENT_METHODS.size()));
        DeliveryAddress deliveryAddress = "delivery".equals(deliveryType) ?
                deliveryAddressGenerator.getRandomDeliveryAddress() : null;
        List<Item> invoiceItems = new ArrayList<>();
        Integer totalNumberOfItems = random.nextInt(10);
        IntStream.range(0, totalNumberOfItems)
                .forEach(i -> {
                    invoiceItems.add(itemGenerator.getRandomItem());
                });
        Double totalAmount = invoiceItems.stream()
                .map(it -> it.getTotalValue())
                .reduce(Double::sum)
                .orElse(0.00);
        return PosInvoice.newBuilder()
                .setCreatedAt(System.currentTimeMillis())
                .setDeliveryAddress(deliveryAddress)
                .setCustomerCardNo(UUID.randomUUID().toString())
                .setPosId(UUID.randomUUID().toString())
                .setStoreId(UUID.randomUUID().toString())
                .setCustomerType(customerType)
                .setDeliveryType(deliveryType)
                .setPaymentMethod(paymentMethod)
                .setTaxableAmount(totalAmount)
                .setInvoiceNo(UUID.randomUUID().toString())
                .setItemQuantity(totalNumberOfItems)
                .setInvoiceItems(invoiceItems)
                .setTotalAmount(totalAmount)
                .build();
    }
}
