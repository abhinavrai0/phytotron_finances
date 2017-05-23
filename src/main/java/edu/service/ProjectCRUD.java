package edu.service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import edu.model.*;
public interface ProjectCRUD extends CrudRepository<Project, Long>{
	List<Project> findByProjectStatus(String status);
}
