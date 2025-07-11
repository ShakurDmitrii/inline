package com.inline.inline_task.Controller;

import com.inline.inline_task.DTO.CustomerDto;
import com.inline.inline_task.Service.CustomerService;
import jooqdata.tables.Customer;
import jooqdata.tables.records.CustomerRecord;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final DSLContext DSLContext;

    public CustomerController(CustomerService customerService, DSLContext DSLContext) {
        this.customerService = customerService;
        this.DSLContext = DSLContext;
    }

    @GetMapping
    public List<CustomerDto> getAll() {
        return customerService.findAll();
    }
    @GetMapping("/{code}")
    public CustomerRecord getByCode(@PathVariable String code) {
        return customerService.findByCode(code);
    }
    @PostMapping
    public CustomerDto createOrUpdate(@RequestBody CustomerDto dto) {
        CustomerRecord record = DSLContext.newRecord(jooqdata.tables.Customer.CUSTOMER);
        record.setCustomerCode(dto.customerCode);
        record.setCustomerName(dto.customerName);
        record.setCustomerInn(dto.customerInn);
        record.setCustomerKpp(dto.customerKpp);
        record.setCustomerLegalAddress(dto.customerLegalAddress);
        record.setCustomerPostalAddress(dto.customerPostalAddress);
        record.setCustomerEmail(dto.customerEmail);
        record.setCustomerCodeMain(dto.customerCodeMain);
        record.setIsOrganization(dto.isOrganization);
        record.setIsPerson(dto.isPerson);
        record.store(); // insert/update
        return dto;
    }
}
