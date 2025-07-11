/*
 * This file is generated by jOOQ.
 */
package jooqdata.tables.records;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import jooqdata.tables.Lot;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class LotRecord extends UpdatableRecordImpl<LotRecord> implements Record8<String, String, BigDecimal, String, String, String, LocalDateTime, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>purchase.lot.lot_name</code>.
     */
    public void setLotName(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>purchase.lot.lot_name</code>.
     */
    public String getLotName() {
        return (String) get(0);
    }

    /**
     * Setter for <code>purchase.lot.customer_code</code>.
     */
    public void setCustomerCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>purchase.lot.customer_code</code>.
     */
    public String getCustomerCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>purchase.lot.price</code>.
     */
    public void setPrice(BigDecimal value) {
        set(2, value);
    }

    /**
     * Getter for <code>purchase.lot.price</code>.
     */
    public BigDecimal getPrice() {
        return (BigDecimal) get(2);
    }

    /**
     * Setter for <code>purchase.lot.currency_code</code>.
     */
    public void setCurrencyCode(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>purchase.lot.currency_code</code>.
     */
    public String getCurrencyCode() {
        return (String) get(3);
    }

    /**
     * Setter for <code>purchase.lot.nds_rate</code>.
     */
    public void setNdsRate(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>purchase.lot.nds_rate</code>.
     */
    public String getNdsRate() {
        return (String) get(4);
    }

    /**
     * Setter for <code>purchase.lot.place_delivery</code>.
     */
    public void setPlaceDelivery(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>purchase.lot.place_delivery</code>.
     */
    public String getPlaceDelivery() {
        return (String) get(5);
    }

    /**
     * Setter for <code>purchase.lot.date_delivery</code>.
     */
    public void setDateDelivery(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>purchase.lot.date_delivery</code>.
     */
    public LocalDateTime getDateDelivery() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>purchase.lot.id</code>.
     */
    public void setId(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>purchase.lot.id</code>.
     */
    public Integer getId() {
        return (Integer) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<String, String, BigDecimal, String, String, String, LocalDateTime, Integer> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<String, String, BigDecimal, String, String, String, LocalDateTime, Integer> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Lot.LOT.LOT_NAME;
    }

    @Override
    public Field<String> field2() {
        return Lot.LOT.CUSTOMER_CODE;
    }

    @Override
    public Field<BigDecimal> field3() {
        return Lot.LOT.PRICE;
    }

    @Override
    public Field<String> field4() {
        return Lot.LOT.CURRENCY_CODE;
    }

    @Override
    public Field<String> field5() {
        return Lot.LOT.NDS_RATE;
    }

    @Override
    public Field<String> field6() {
        return Lot.LOT.PLACE_DELIVERY;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return Lot.LOT.DATE_DELIVERY;
    }

    @Override
    public Field<Integer> field8() {
        return Lot.LOT.ID;
    }

    @Override
    public String component1() {
        return getLotName();
    }

    @Override
    public String component2() {
        return getCustomerCode();
    }

    @Override
    public BigDecimal component3() {
        return getPrice();
    }

    @Override
    public String component4() {
        return getCurrencyCode();
    }

    @Override
    public String component5() {
        return getNdsRate();
    }

    @Override
    public String component6() {
        return getPlaceDelivery();
    }

    @Override
    public LocalDateTime component7() {
        return getDateDelivery();
    }

    @Override
    public Integer component8() {
        return getId();
    }

    @Override
    public String value1() {
        return getLotName();
    }

    @Override
    public String value2() {
        return getCustomerCode();
    }

    @Override
    public BigDecimal value3() {
        return getPrice();
    }

    @Override
    public String value4() {
        return getCurrencyCode();
    }

    @Override
    public String value5() {
        return getNdsRate();
    }

    @Override
    public String value6() {
        return getPlaceDelivery();
    }

    @Override
    public LocalDateTime value7() {
        return getDateDelivery();
    }

    @Override
    public Integer value8() {
        return getId();
    }

    @Override
    public LotRecord value1(String value) {
        setLotName(value);
        return this;
    }

    @Override
    public LotRecord value2(String value) {
        setCustomerCode(value);
        return this;
    }

    @Override
    public LotRecord value3(BigDecimal value) {
        setPrice(value);
        return this;
    }

    @Override
    public LotRecord value4(String value) {
        setCurrencyCode(value);
        return this;
    }

    @Override
    public LotRecord value5(String value) {
        setNdsRate(value);
        return this;
    }

    @Override
    public LotRecord value6(String value) {
        setPlaceDelivery(value);
        return this;
    }

    @Override
    public LotRecord value7(LocalDateTime value) {
        setDateDelivery(value);
        return this;
    }

    @Override
    public LotRecord value8(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public LotRecord values(String value1, String value2, BigDecimal value3, String value4, String value5, String value6, LocalDateTime value7, Integer value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LotRecord
     */
    public LotRecord() {
        super(Lot.LOT);
    }

    /**
     * Create a detached, initialised LotRecord
     */
    public LotRecord(String lotName, String customerCode, BigDecimal price, String currencyCode, String ndsRate, String placeDelivery, LocalDateTime dateDelivery, Integer id) {
        super(Lot.LOT);

        setLotName(lotName);
        setCustomerCode(customerCode);
        setPrice(price);
        setCurrencyCode(currencyCode);
        setNdsRate(ndsRate);
        setPlaceDelivery(placeDelivery);
        setDateDelivery(dateDelivery);
        setId(id);
    }
}
