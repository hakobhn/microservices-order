package com.example.shipping.events;

import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.shipping.domain.Shipment;

public class ShipmentDeserializer extends JsonDeserializer<Shipment> {

	public ShipmentDeserializer() {
		super(Shipment.class);
	}

}
