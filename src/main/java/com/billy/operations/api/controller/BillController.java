package com.billy.operations.api.controller;

import com.billy.operations.api.model.Bill;
import com.billy.operations.api.service.BillService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @ApiResponse(responseCode = "201", description = "CREATED")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Bill> addBill(@RequestBody Bill bill) {
        Bill saved =  billService.save(bill);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(saved);
    }

    @GetMapping("/person/{name}")
    public ResponseEntity<List<Bill>> getAllBillsForPerson(@PathVariable String name) {
        List<Bill> bills = billService.getAllBillsForPerson(name);
        return ResponseEntity.ok(bills);
    }
}
