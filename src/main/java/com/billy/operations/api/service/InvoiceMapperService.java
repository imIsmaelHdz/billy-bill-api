package com.billy.operations.api.service;

import com.billy.operations.api.model.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InvoiceMapperService {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertToJson(Invoice invoice) throws Exception {
        return objectMapper.writeValueAsString(invoice);
    }

    public static Invoice convertToInvoice(String json) throws Exception {
        return objectMapper.readValue(json, Invoice.class);
    }

}