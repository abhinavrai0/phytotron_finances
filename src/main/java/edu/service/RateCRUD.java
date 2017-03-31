package edu.service;

import org.springframework.data.repository.CrudRepository;

import edu.model.Chamber;
import edu.model.Rate;

public interface RateCRUD extends CrudRepository<Rate, Long>{
}
