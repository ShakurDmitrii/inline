package com.inline.inline_task.Controller;

import com.inline.inline_task.DTO.CustomerDto;
import com.inline.inline_task.Service.CustomerService;
import jooqdata.tables.records.CustomerRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;

    }

    @GetMapping
    public List<CustomerDto> getAll() {
        return customerService.findAll();
    }
    @GetMapping("/{code}")
    public List<CustomerDto> getByCode(@PathVariable String code) {
        return customerService.findByCode(code);
    }
    @PostMapping
    public CustomerDto createOrUpdate(@RequestBody CustomerDto dto) {
      return CustomerService.save(dto);
    }
    @PutMapping("/{customerCode}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String customerCode,
                                                      @RequestBody CustomerDto dto) {
        try {
            CustomerDto updatedDto = CustomerService.update(customerCode, dto); // вызов метода обновления
            return ResponseEntity.ok(updatedDto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // если клиента нет — 404
        }
    }
}
