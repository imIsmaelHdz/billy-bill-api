package com.billy.operations.api.service;

import com.billy.operations.api.model.InvoiceTemplate;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProcessorService {
    private final BillService billService;
    private final UserService userService;

    public InvoiceProcessorService(BillService billService, UserService userService) {
        this.billService = billService;
        this.userService = userService;
    }

    public String processInvoiceV4(){
        return "processed";
    }
}
