package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;
import com.devsuperior.dsmeta.projections.SellerProjection;

public class SellerDTO {

    private String name;
    private Double amount;

    public SellerDTO(String name, Double amount) {
        this.name = name;
        this.amount = amount;
    }

    public SellerDTO(SellerProjection projection) {
        name = projection.getName();
        amount = projection.getAmount();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setTotal(Double amount) {
        this.amount = amount;
    }

}
