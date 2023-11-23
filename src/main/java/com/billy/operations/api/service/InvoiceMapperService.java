package com.billy.operations.api.service;

import com.billy.operations.api.model.InvoiceTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InvoiceMapperService {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertToJson(InvoiceTemplate invoice) throws Exception {
        return objectMapper.writeValueAsString(invoice);
    }

    public static InvoiceTemplate convertToInvoice(String json) throws Exception {
        return objectMapper.readValue(json, InvoiceTemplate.class);
    }

}