package com.example.invoicing.repository;

import java.util.Date;

import com.example.invoicing.domain.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {

	@Query("SELECT max(i.updated) FROM Invoice i")
	Date lastUpdate();

}
