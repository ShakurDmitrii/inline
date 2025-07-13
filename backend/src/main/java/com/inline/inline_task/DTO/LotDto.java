package com.inline.inline_task.DTO;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LotDto {
    public int id;
    public String lot_name;
    public String customer_code;
    public BigDecimal price;
    public String currency_code;
    public String nds_rate;
    public String place_delivery;
    public LocalDateTime date_delivery;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLot_name() {
        return lot_name;
    }

    public void setLot_name(String lot_name) {
        this.lot_name = lot_name;
    }

    public String getCustomer_code() {
        return customer_code;
    }

    public void setCustomer_code(String customer_code) {
        this.customer_code = customer_code;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getNds_rate() {
        return nds_rate;
    }

    public void setNds_rate(String nds_rate) {
        this.nds_rate = nds_rate;
    }

    public String getPlace_delivery() {
        return place_delivery;
    }

    public void setPlace_delivery(String place_delivery) {
        this.place_delivery = place_delivery;
    }

    public LocalDateTime getDate_delivery() {
        return date_delivery;
    }

    public void setDate_delivery(LocalDateTime date_delivery) {
        this.date_delivery = date_delivery;
    }
}
