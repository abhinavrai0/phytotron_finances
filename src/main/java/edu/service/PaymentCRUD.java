package edu.service;

import org.springframework.data.repository.CrudRepository;

import edu.model.Payment;
public interface PaymentCRUD extends CrudRepository<Payment, Long> {
}
