package com.billy.operations.api.service;

import com.billy.operations.api.model.Bill;
import com.billy.operations.api.repository.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BillServiceTest {

    @InjectMocks
    private BillService billService;

    @Mock
    private BillRepository billRepository;

    @Mock
    private UserService userService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Bill bill = new Bill(
                UUID.randomUUID(),
                "John Doe",
                "12345",
                new BigDecimal("100.00"),
                new BigDecimal("10.0")
        );

        when(billRepository.save(any(Bill.class))).thenReturn(bill);

        Bill savedBill = billService.save(bill);

        verify(billRepository, times(1)).save(eq(bill));
        assertEquals(bill, savedBill);
    }

    @Test
    void getAllBillsForUser() {
    }

    @Test
    void getAllBillsForUserByRFC() {
    }
}

