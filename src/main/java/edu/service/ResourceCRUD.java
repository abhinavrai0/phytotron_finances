package edu.service;

import edu.model.Resource;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Pinakin Abhyankar on 7/19/2017.
 * This is a DAO layer for the Additional Resource Model.
 */
public interface ResourceCRUD extends CrudRepository <Resource, Long> {
    Resource findFirstByResourceName(String resourceName);
}
