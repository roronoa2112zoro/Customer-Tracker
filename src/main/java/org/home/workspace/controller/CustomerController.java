package org.home.workspace.controller;

import java.util.List;

import org.home.workspace.model.Customer;
import org.home.workspace.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get Customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add Customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers"; // logical view name
	}
	
	// Mapping for ShowFormForAdd
		@GetMapping("/showFormForAdd")
		public String showFormForAdd(Model theModel){
			
			// create model attribute to bind form data
			Customer theCustomer = new Customer();
			
			theModel.addAttribute("customer", theCustomer);
			
			return "customer-form";
		}
		
		@PostMapping("/saveCustomer")
		public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){
			
			// save the customer using our service
			customerService.saveCustomer(theCustomer);
			
		//	customerService.saveCustomer()
			return "redirect:/customer/list";
		}
		
		@GetMapping("/showFormForUpdate")
		public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
			
			// get the customer from the service
			Customer theCustomer = customerService.getCustomer(theId);
			
			
			// set customer as a model attribute to pre-populate the form
			theModel.addAttribute("customer", theCustomer);
			
			
			// send over to our form
			return "customer-form";
		}
		
		@GetMapping("/delete")
		public String delete(@RequestParam("customerId") int theId, Model theModel) {
			
			// delete the customer 
			customerService.deleteCustomer(theId);
			
			
			return "redirect:/customer/list";
		}

}
