package com.inline.inline_task.Service;

import jooqdata.tables.Lot;
import jooqdata.tables.records.LotRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotService {

    private final DSLContext dsl;

    public LotService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<LotRecord> findAll() {
        return dsl.selectFrom(Lot.LOT).fetchInto(LotRecord.class);
    }

    public LotRecord findByCode(String code) {
        return dsl.selectFrom(Lot.LOT)
                .where(Lot.LOT.CUSTOMER_CODE.eq(code))
                .fetchOneInto(LotRecord.class);
    }

    public LotRecord findByName(String name) {
        return dsl.selectFrom(Lot.LOT)
                .where(Lot.LOT.LOT_NAME.eq(name))
                .fetchOneInto(LotRecord.class);
    }

    public LotRecord findById(int id) {
        return dsl.selectFrom(Lot.LOT)
                .where(Lot.LOT.ID.eq(id))
                .fetchOneInto(LotRecord.class);
    }

    public LotRecord save(LotRecord lot) {
        if (findById(lot.getId()) != null) {
            dsl.executeUpdate(lot);
        } else {
            dsl.executeInsert(lot);
        }
        return lot;
    }

}
