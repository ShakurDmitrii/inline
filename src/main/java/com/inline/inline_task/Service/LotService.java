package com.inline.inline_task.Service;

import com.inline.inline_task.DTO.CustomerDto;
import com.inline.inline_task.DTO.LotDto;
import jooqdata.tables.Customer;
import jooqdata.tables.Lot;
import jooqdata.tables.records.CustomerRecord;
import jooqdata.tables.records.LotRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class LotService {

    private static DSLContext dsl;

    public LotService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<LotDto> findAll() {
        return dsl.selectFrom(Lot.LOT)
                .fetch()
                .stream()
                .map(record -> {
                    LotDto lotDto = new LotDto();
                    lotDto.lot_name = record.getLotName();
                    lotDto.id = record.getId();
                    lotDto.currency_code = record.getCurrencyCode();
                    lotDto.customer_code = record.getCustomerCode();
                    lotDto.date_delivery = record.getDateDelivery();
                    lotDto.nds_rate = record.getNdsRate();
                    lotDto.place_delivery = record.getPlaceDelivery();
                    lotDto.price = record.getPrice();
                    return lotDto;
                })
                .toList();
    }

    public List<LotDto> findByCode(String code) {
        var records = dsl.selectFrom(Lot.LOT)
                .where(Lot.LOT.CUSTOMER_CODE.eq(code))
                .fetch();

        if (records.isEmpty()) {
            throw new RuntimeException("No lots found for customer code: " + code);
        }

        return records.stream()
                .map(record -> {
                    LotDto lotDto = new LotDto();
                    lotDto.lot_name = record.getLotName();
                    lotDto.id = record.getId();
                    lotDto.currency_code = record.getCurrencyCode();
                    lotDto.customer_code = record.getCustomerCode();
                    lotDto.date_delivery = record.getDateDelivery();
                    lotDto.nds_rate = record.getNdsRate();
                    lotDto.place_delivery = record.getPlaceDelivery();
                    lotDto.price = record.getPrice();
                    return lotDto;
                })
                .toList();
    }

    public List<LotDto> findByName(String name) {
        return dsl.selectFrom(Lot.LOT)
                .where(Lot.LOT.LOT_NAME.eq(name))
                .fetch()
                .stream()
                .map(record->{
                    LotDto lotDto = new LotDto();
                    lotDto.lot_name = record.getLotName();
                    lotDto.id = record.getId();
                    lotDto.currency_code = record.getCurrencyCode();
                    lotDto.customer_code = record.getCustomerCode();
                    lotDto.date_delivery = record.getDateDelivery();
                    lotDto.nds_rate = record.getNdsRate();
                    lotDto.place_delivery = record.getPlaceDelivery();
                    lotDto.price = record.getPrice();
                    return lotDto;
                })
                .toList();
    }

    public List<LotDto> findById(int id) {
        return dsl.selectFrom(Lot.LOT)
                .where(Lot.LOT.ID.eq(id))
                .fetch()
                .stream()
                .map(record -> {
                    LotDto lotDto = new LotDto();
                    lotDto.lot_name = record.getLotName();
                    lotDto.id = record.getId();
                    lotDto.currency_code = record.getCurrencyCode();
                    lotDto.customer_code = record.getCustomerCode();
                    lotDto.date_delivery = record.getDateDelivery();
                    lotDto.nds_rate = record.getNdsRate();
                    lotDto.place_delivery = record.getPlaceDelivery();
                    lotDto.price = record.getPrice();
                    return lotDto;
                })
                .toList();
    }

    public static LotDto save(LotDto lot) {

                boolean customerExists = dsl.selectFrom(Customer.CUSTOMER)
                        .where(Customer.CUSTOMER.CUSTOMER_CODE.eq(lot.customer_code))
                        .fetch()
                        .isNotEmpty();
        if(customerExists) {
            LotRecord record = dsl.newRecord(jooqdata.tables.Lot.LOT);
            record.setCustomerCode(lot.customer_code);
            record.setCurrencyCode(lot.currency_code);
            record.setDateDelivery(lot.date_delivery);
            record.setNdsRate(lot.nds_rate);
            record.setPlaceDelivery(lot.place_delivery);
            record.setPrice(lot.price);
            record.setLotName(lot.lot_name);
            record.setId(lot.id);
            record.store();

        }
        else {
            // Можно выбросить исключение или логгировать, если заказчик не найден
            System.out.println("Заказчик с кодом " + lot.customer_code + " не найден.");
        }
        return lot;
    }
    public boolean deleteById(String id) {
        int deletedRows = dsl.deleteFrom(jooqdata.tables.Lot.LOT)
                .where(jooqdata.tables.Lot.LOT.ID.eq(Integer.valueOf(id)))
                .execute();
        if (deletedRows > 0) {
            System.out.println("Запись с id " + id + " успешно удалена.");
            return true;
        } else {
            System.out.println("Запись с id " + id + " не найдена.");
            return false;
        }
    }

    public LotDto update(String id, LotDto dto) {
        boolean customerExists = dsl.selectFrom(Customer.CUSTOMER)
                .where(Customer.CUSTOMER.CUSTOMER_CODE.eq(dto.customer_code))
                .fetch()
                .isNotEmpty();
        LotRecord record = dsl.fetchOne(jooqdata.tables.Lot.LOT,
                jooqdata.tables.Lot.LOT.ID.eq(Integer.valueOf(id)));

        if (record == null) {
            throw new RuntimeException("Customer with code " + id + " not found");
        }

        if (customerExists) {
            // Можно обновлять все поля, включая customerCode, если надо
            record.setId(dto.id);
            record.setLotName(dto.lot_name);
            record.setCustomerCode(dto.customer_code);
            record.setPrice(dto.price);
            record.setCurrencyCode(dto.currency_code);
            record.setNdsRate(dto.nds_rate);
            record.setPlaceDelivery(dto.place_delivery);
            record.setDateDelivery(dto.date_delivery);


            record.store();
        } else {
            throw new RuntimeException("Customer with code " + dto.customer_code + " not found. Update aborted.");
        }
        return dto;
    }

}
