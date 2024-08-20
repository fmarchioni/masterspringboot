package com.example.demo.demo3_4.controller;

import java.util.List;

import com.example.demo.demo3_4.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.demo3_4.exception.CustomerNotFoundException;
import com.example.demo.demo3_4.services.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService service;

	@GetMapping("/")
	public String showHomePage() {
		return "homePage";
	}

	@GetMapping("/register")
	public String showRegistration() {
		return "registerCustomerPage";
	}

	@PostMapping("/save")
	public String saveInvoice(@ModelAttribute Customer customer, Model model) {
		service.save(customer);
		Long id = service.save(customer).getId();
		String message = "Record with id : '" + id + "' is saved successfully !";
		model.addAttribute("message", message);
		return "registerCustomerPage";
	}

	@GetMapping("/getAllCustomers")
	public String getAllCustomers(@RequestParam(value = "message", required = false) String message, Model model) {
		List<Customer> customers = service.findAll();
		model.addAttribute("list", customers);
		model.addAttribute("message", message);

		return "allCustomersPage";
	}

	@GetMapping("/edit")
	public String getEditPage(Model model, RedirectAttributes attributes, @RequestParam Long id) {
		String page = null;
		try {
			Customer customer = service.getCustomerById(id);
			model.addAttribute("customer", customer);
			page = "editCustomerPage";
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:getAllCustomers";
		}
		return page;
	}

	@PostMapping("/update")
	public String updateInvoice(@ModelAttribute Customer customer, RedirectAttributes attributes) {
		service.update(customer);
		Long id = customer.getId();
		attributes.addAttribute("message", "Customer with id: '" + id + "' is updated successfully !");
		return "redirect:getAllCustomers";
	}

	@GetMapping("/delete")
	public String deleteInvoice(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			service.deleteCustomerById(id);
			attributes.addAttribute("message", "Customer with Id : '" + id + "' is removed successfully!");
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:getAllCustomers";
	}
}