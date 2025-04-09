package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.projections.SaleProjection;
import com.devsuperior.dsmeta.projections.SellerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true,
            value = "select tb_sales.id, tb_sales.date, tb_sales.amount, tb_seller.name " +
                    "from tb_sales inner join tb_seller on tb_sales.seller_id=tb_seller.id " +
                    "where tb_sales.date between :minDate and :maxDate " +
                    "and UPPER(tb_seller.name) LIKE UPPER(CONCAT('%', :name, '%'))",
            countQuery = "select count(*) " +
                    "from tb_sales inner join tb_seller on tb_sales.seller_id=tb_seller.id " +
                    "where tb_sales.date between :minDate and :maxDate " +
                    "and UPPER(tb_seller.name) LIKE UPPER(CONCAT('%', :name, '%'))"
    )
    Page<SaleProjection> searchSale(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query(nativeQuery = true,
        value="select tb_seller.name, sum(tb_sales.amount) "+
                "from tb_sales inner join tb_seller on tb_sales.seller_id=tb_seller.id " +
                "where tb_sales.date between :minDate and :maxDate "+
                "group by tb_seller.name "
                )
    List<SellerProjection> searchBySeller(LocalDate minDate, LocalDate maxDate);
}
