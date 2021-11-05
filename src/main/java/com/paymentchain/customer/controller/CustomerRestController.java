/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paymentchain.customer.controller;

import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.entities.Customerkpl;
import com.paymentchain.customer.respository.CustomerRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author trodrigc
 */
@RestController
@RequestMapping("/customer")
public class CustomerRestController {
    
    @Autowired
    CustomerRepository customerRepository;
    
    @GetMapping("/listclientes")
    public List<Customer> list() {
        return customerRepository.findAll();
    }
    
    @GetMapping("/kpideclientes")
    public List<Customerkpl> listkpi() {
    	List<Customer> customer = customerRepository.findAll();
    	List<Customerkpl> listCustomerkpl = new ArrayList<Customerkpl>();
    	int suma = 0;
    	for (int i = 0; i < customer.size(); i++) {
    		suma = suma + customer.get(i).getEdad();
		}
    	float average = suma/customer.size();
    	float standarDeviation = 0;
    	for (int i = 0; i < customer.size(); i++) {
    		if (average>customer.get(i).getEdad()) {
    			standarDeviation += (float) Math.pow(average - customer.get(i).getEdad(), 2);
			}else {
				standarDeviation += (float) Math.pow(customer.get(i).getEdad() - average, 2);
			}
		}
    	Customerkpl customerkpl = new Customerkpl();
    	customerkpl.setAverage(average);
    	customerkpl.setStandardDeviation((float) Math.pow(standarDeviation, 0.5));
    	listCustomerkpl.add(customerkpl);
    	return listCustomerkpl;
    }
    
    @PostMapping("/crearcliente")
    public ResponseEntity<?> post(@RequestBody Customer input) {
        Customer save = customerRepository.save(input);
        return ResponseEntity.ok(save);
    }
    
}
