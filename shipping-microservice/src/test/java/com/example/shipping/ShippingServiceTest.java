package com.example.shipping;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import com.example.shipping.domain.Address;
import com.example.shipping.domain.Customer;
import com.example.shipping.domain.Shipment;
import com.example.shipping.domain.ShipmentLine;
import com.example.shipping.repository.ShipmentRepository;
import com.example.shipping.service.ShipmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShippingTestApp.class, webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("test")
public class ShippingServiceTest {

	@Autowired
	private ShipmentRepository shipmentRepository;

	@Autowired
	private ShipmentService shipmentService;

	@Test
	public void ensureIdempotencySecondCallIgnored() {
		long countBefore = shipmentRepository.count();
		Shipment shipment = new Shipment(42L,
				new Customer(23L, "Eberhard", "Wolff"),
				new Date(0L), new Address("Krischstr. 100", "40789", "Monheim am Rhein"),
				new ArrayList<ShipmentLine>());
		shipmentService.ship(shipment);
		assertThat(shipmentRepository.count(), is(countBefore + 1));
		assertThat(shipmentRepository.findById(42L).get().getUpdated().getTime(), equalTo(0L));
		shipment = new Shipment(42,
				new Customer(23L, "Eberhard", "Wolff"),
				new Date(), new Address("Krischstr. 100", "40789", "Monheim am Rhein"), new ArrayList<ShipmentLine>());
		shipmentService.ship(shipment);
		assertThat(shipmentRepository.count(), is(countBefore + 1));
		assertThat(shipmentRepository.findById(42L).get().getUpdated().getTime(), equalTo(0L));
	}

}
