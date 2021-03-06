package com.example.order.repository;

import java.util.Date;

import com.example.order.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "order", path = "order")
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

	@Query("SELECT max(o.updated) FROM Order o")
	Date lastUpdate();

}
