package com.devsuperior.dsmeta.projections;

import com.devsuperior.dsmeta.entities.Seller;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;

public interface SaleProjection {
    Long getId();
    LocalDate getDate();
    Double getAmount();
    String getName();
}

