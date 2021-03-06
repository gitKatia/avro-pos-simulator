package com.kat.avropossimulator.service;

import com.kat.avropossimulator.config.TopicsProperties;
import com.kat.avropossimulator.model.PosInvoice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, PosInvoice> kafkaTemplate;
    private final TopicsProperties topicsProperties;

    public void sendInvoice(PosInvoice posInvoice) {
        log.info("Sending invoice number {} from pos {}", posInvoice.getInvoiceNo(), posInvoice.getPosId());
        kafkaTemplate.send(topicsProperties.getPosTopic(), posInvoice.getStoreId(), posInvoice);
    }
}
