package com.example.shipping.web;

import com.example.shipping.domain.Address;
import com.example.shipping.domain.Customer;
import com.example.shipping.domain.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.shipping.repository.ShipmentRepository;

@Controller
public class ShippingController {

	private ShipmentRepository shipmentRepository;

	@Autowired
	public ShippingController(ShipmentRepository shipmentRepository) {
		this.shipmentRepository = shipmentRepository;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView Item(@PathVariable("id") long id) {
		return new ModelAndView("shipment", "shipment", shipmentRepository.findById(id).get());
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView ItemList() {
		return new ModelAndView("shipmentlist", "shipments", shipmentRepository.findAll());
	}

//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	public ResponseEntity<?> test() {
//		Shipment shipment = new Shipment();
//		shipment.setCustomer(new Customer(1, "a", "a"));
//		shipment.setShippingAddress(new Address("a", "a", "a"));
//		shipmentRepository.save(shipment);
//		return ResponseEntity.ok(shipment);
//	}
}
