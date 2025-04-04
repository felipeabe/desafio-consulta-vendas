package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.projections.SaleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true,
    value="select tb_sales.id, tb_sales.date, tb_sales.amount, tb_seller.name " +
            "from tb_sales inner join tb_seller on tb_sales.seller_id=tb_seller.id " +
            "where tb_sales.date between :initialDate and :finalDate " +
            "and UPPER(tb_seller.name) LIKE UPPER(CONCAT('%', :name, '%')) ")
    Page<SaleProjection> searchSale(LocalDate initialDate, LocalDate finalDate, String name, Pageable pageable);
}
