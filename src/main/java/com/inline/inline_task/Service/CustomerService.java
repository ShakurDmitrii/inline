package com.inline.inline_task.Service;

import com.inline.inline_task.DTO.CustomerDto;
import jooqdata.tables.Customer;
import jooqdata.tables.records.CustomerRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CustomerService {

    private final DSLContext dsl;

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

    public CustomerRecord findByCode(String customerCode){
    return dsl.selectFrom(Customer.CUSTOMER)
            .where(Customer.CUSTOMER.CUSTOMER_CODE.eq(customerCode))
            .fetchOneInto(CustomerRecord.class);
    }

    public CustomerRecord save(CustomerRecord customer) {
        // Если существует, обновляем, иначе вставляем
        if (findByCode(customer.getCustomerCode()) != null) {
            dsl.executeUpdate(customer);
        } else {
            dsl.executeInsert(customer);
        }
        return customer;
    }

}
