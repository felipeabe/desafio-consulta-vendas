package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.Optional;

import com.devsuperior.dsmeta.projections.SaleProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	@Transactional(readOnly = true)
	public Page<SaleMinDTO> getReport(LocalDate initialDate, LocalDate finalDate, String name, Pageable pageable) {
		Page<SaleProjection> result = repository.searchSale(initialDate, finalDate, name, pageable);
		return result.map(x -> new SaleMinDTO(x));
	}

	public SaleMinDTO findById(Long id) {
		Sale entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Sale not found with ID: " + id));
		return new SaleMinDTO(entity);
	}

}
