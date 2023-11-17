package com.billy.operations.api.controller;

import com.billy.operations.api.model.Bill;
import com.billy.operations.api.service.BillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Bills")
@RequestMapping("/bills")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @ApiResponse(responseCode = "201", description = "CREATED")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @Operation(summary = "Create a Bill ")
    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Bill> addBill(@RequestBody Bill bill) {
        Bill saved =  billService.save(bill);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(saved);
    }

    @Operation(summary = "Get all bills by a given name")
    @GetMapping(value = "/user/name/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Bill>> getAllBillsByUser(@PathVariable String name) {
        List<Bill> bills = billService.getAllBillsForUser(name);
        return ResponseEntity.ok(bills);
    }
    @Operation(summary = "Get all bills by a given RFC")
    @GetMapping(value = "/person/rfc/{rfc}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<Bill>> getBillsByRFC(@PathVariable String rfc) {
        List<Bill> bills = billService.getAllBillsForUserByRFC(rfc);
        return ResponseEntity.ok(bills);
    }
}
