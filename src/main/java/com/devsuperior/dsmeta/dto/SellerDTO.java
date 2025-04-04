package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SellerDTO {

    private String name;


    public SellerDTO(String name) {
        this.name=name;
    }

    public SellerDTO(Seller entity) {
        name = entity.getName();
    }


    public String getName() {
        return name;
    }

}
