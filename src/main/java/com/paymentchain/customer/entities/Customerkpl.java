package com.paymentchain.customer.entities;

import javax.persistence.Entity;

import lombok.Data;

@Data
public class Customerkpl {
	private float average;
	private float standardDeviation;
}
