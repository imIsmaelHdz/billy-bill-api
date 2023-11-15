package com.billy.operations.api.service;

import com.billy.operations.api.model.Bill;
import com.billy.operations.api.model.Person;
import com.billy.operations.api.repository.BillRepository;
import com.billy.operations.api.repository.PersonRepository;
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
    private final PersonService personService;

    public BillService(BillRepository billRepository, PersonService personService) {
        this.billRepository = billRepository;
        this.personService = personService;
    }

    @Transactional
    public Bill save(Bill bill) {
        Bill savedBill = billRepository.save(bill);
        personService.addBillToPerson(savedBill);
        return savedBill;
    }

    @Transactional
    public List<Bill> getAllBillsForPerson(String personName) {
        Person person = personService.findByName(personName);
        return new ArrayList<>(person.getBills());
    }
}

