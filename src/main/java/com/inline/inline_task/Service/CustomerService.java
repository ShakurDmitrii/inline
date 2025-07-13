package com.inline.inline_task.Service;

import com.inline.inline_task.DTO.CustomerDto;
import jooqdata.tables.Customer;
import jooqdata.tables.records.CustomerRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CustomerService {

    private static DSLContext dsl;

    public CustomerService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<CustomerDto> findAll(){
        return dsl.selectFrom(Customer.CUSTOMER)
                .fetch()
                .stream()
                .map(record -> {
                    CustomerDto dto = new CustomerDto();
                    dto.customerCode = record.getCustomerCode();
                    dto.customerName = record.getCustomerName();
                    dto.customerInn = record.getCustomerInn();
                    dto.customerKpp = record.getCustomerKpp();
                    dto.customerLegalAddress = record.getCustomerLegalAddress();
                    dto.customerPostalAddress = record.getCustomerPostalAddress();
                    dto.customerEmail = record.getCustomerEmail();
                    dto.customerCodeMain = record.getCustomerCodeMain();
                    dto.isOrganization = record.getIsOrganization();
                    dto.isPerson = record.getIsPerson();
                    return dto;
                })
                .toList();
    }

    public List<CustomerDto> findByCode(String customerCode){
    return dsl.selectFrom(Customer.CUSTOMER)
            .where(Customer.CUSTOMER.CUSTOMER_CODE.eq(customerCode))
            .fetch()
            .stream()
            .map(customerRecord -> {
                CustomerDto dto = new CustomerDto();
                dto.customerCode = customerRecord.getCustomerCode();
                dto.customerName = customerRecord.getCustomerName();
                dto.customerInn = customerRecord.getCustomerInn();
                dto.customerKpp = customerRecord.getCustomerKpp();
                dto.customerLegalAddress = customerRecord.getCustomerLegalAddress();
                dto.customerPostalAddress = customerRecord.getCustomerPostalAddress();
                dto.customerEmail = customerRecord.getCustomerEmail();
                dto.customerCodeMain = customerRecord.getCustomerCodeMain();
                dto.isOrganization = customerRecord.getIsOrganization();
                dto.isPerson = customerRecord.getIsPerson();
                return dto;
            })
            .toList();
    }

    public static CustomerDto save(CustomerDto dto) {


        CustomerRecord record = dsl.newRecord(jooqdata.tables.Customer.CUSTOMER);
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
