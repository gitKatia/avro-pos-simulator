package com.kat.avropossimulator.service;

import com.kat.avropossimulator.model.PosInvoice;
import com.kat.avropossimulator.service.datagenerator.PosInvoiceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceGeneratorService {

    private final PosInvoiceGenerator posInvoiceGenerator;
    private final KafkaProducerService kafkaProducerService;

    @Scheduled(fixedRate = 5000)
    public void generatePosInvoices() {
        PosInvoice posInvoice = posInvoiceGenerator.generatePosInvoice();
        kafkaProducerService.sendInvoice(posInvoice);
    }
}
