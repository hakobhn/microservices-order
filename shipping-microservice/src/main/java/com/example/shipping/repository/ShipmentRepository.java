package com.example.shipping.repository;

import java.util.Date;

import com.example.shipping.domain.Shipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShipmentRepository extends PagingAndSortingRepository<Shipment, Long> {

	@Query("SELECT max(s.updated) FROM Shipment s")
	Date lastUpdate();

}
