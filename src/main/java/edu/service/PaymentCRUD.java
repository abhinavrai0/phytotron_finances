package edu.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.model.Payment;
public interface PaymentCRUD extends CrudRepository<Payment, Long> {
	List<Payment> findByProjectId(Long id);
	List<Payment> findByAmount(int amount);
}
