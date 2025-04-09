package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.devsuperior.dsmeta.dto.SellerDTO;
import com.devsuperior.dsmeta.projections.SaleProjection;
import com.devsuperior.dsmeta.projections.SellerProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;
import java.time.*;
@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly = true)
	public Page<SaleMinDTO> getReport(String initialDate, String finalDate, String name, Pageable pageable) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate maxDate;
		LocalDate minDate;
		if(finalDate.isEmpty()){
			maxDate=LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		else{
			maxDate = LocalDate.parse(finalDate,formatter);
		}

		if(initialDate.isEmpty()){
			minDate=maxDate.minusYears(1L);
		}
		else{
			minDate = LocalDate.parse(initialDate,formatter);
		}

		Page<SaleProjection> result = repository.searchSale(minDate, maxDate, name, pageable);
		return result.map(x -> new SaleMinDTO(x));
	}

	@Transactional(readOnly = true)
	public List<SellerDTO> searchBySeller(String initialDate, String finalDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate maxDate;
		LocalDate minDate;
		if(finalDate.isEmpty()){
			maxDate=LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}
		else{
			maxDate = LocalDate.parse(finalDate,formatter);
		}

		if(initialDate.isEmpty()){
			minDate=maxDate.minusYears(1L);
		}
		else{
			minDate = LocalDate.parse(initialDate,formatter);
		}

		List<SellerProjection> result = repository.searchBySeller(minDate, maxDate);
		return result.stream().map(x->new SellerDTO(x)).toList();
	}

	public SaleMinDTO findById(Long id) {
		Sale entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Sale not found with ID: " + id));
		return new SaleMinDTO(entity);
	}

}
