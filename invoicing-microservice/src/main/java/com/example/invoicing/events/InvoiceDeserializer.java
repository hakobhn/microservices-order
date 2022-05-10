package com.example.invoicing.events;

import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.invoicing.domain.Invoice;

public class InvoiceDeserializer extends JsonDeserializer<Invoice> {

	public InvoiceDeserializer() {
		super(Invoice.class);
	}

}
