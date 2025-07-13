package com.inline.inline_task.Service;

import com.inline.inline_task.DTO.LotDto;
import jooqdata.tables.Lot;
import jooqdata.tables.records.LotRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
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
      return lot;
    }

}
