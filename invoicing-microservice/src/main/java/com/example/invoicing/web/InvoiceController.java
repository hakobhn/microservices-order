package com.example.invoicing.web;

import com.example.invoicing.domain.Customer;
import com.example.invoicing.domain.Invoice;
import com.example.invoicing.domain.InvoiceLine;
import com.example.invoicing.domain.Item;
import com.example.invoicing.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class InvoiceController {

	private InvoiceRepository invoiceRepository;

	@Autowired
	public InvoiceController(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView Item(@PathVariable("id") long id) {
		return new ModelAndView("invoice", "invoice", invoiceRepository.findById(id).get());
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView ItemList() {
		return new ModelAndView("invoicelist", "invoices", invoiceRepository.findAll());
	}

//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	public ResponseEntity<?> test() {
//		Item item = new Item("Test", 5.0);
//		item.setItemId(1l);
//		InvoiceLine invoiceLine = new InvoiceLine(1, item);
//		Invoice invoice = new Invoice();
//		invoice.setCustomer(new Customer(1, "a", "a", "a"));
//		invoice.setInvoiceLine(List.of(invoiceLine));
//		invoiceRepository.save(invoice);
//		return ResponseEntity.ok(invoice);
//	}

}
