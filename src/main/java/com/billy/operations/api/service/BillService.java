package com.billy.operations.api.service;

import com.billy.operations.api.model.Bill;
import com.billy.operations.api.model.User;
import com.billy.operations.api.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final BillRepository billRepository;
    private final UserService userService;

    public BillService(BillRepository billRepository, UserService userService) {
        this.billRepository = billRepository;
        this.userService = userService;
    }

    @Transactional
    public Bill save(Bill bill) {
        Bill savedBill = billRepository.save(bill);
        userService.addBillToUser(savedBill);
        return savedBill;
    }

    @Transactional
    public List<Bill> getAllBillsForUser(String personName) {
        User user = userService.findByName(personName);
        return new ArrayList<>(user.getBills());
    }

    @Transactional
    public List<Bill> getAllBillsForUserByRFC(String rfc) {
        User user = userService.findByRFC(rfc);
        return new ArrayList<>(user.getBills());
    }

}

