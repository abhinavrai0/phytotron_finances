package edu.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import edu.model.*;
import org.springframework.data.repository.query.Param;

public interface ProjectCRUD extends CrudRepository<Project, Long>{
	List<Project> findByProjectStatus(String status);
	@Query("select p from Project p where p.projectStatus = :status and " +
			"p.endDate>= :quarterStartDate " +
			"and p.lastBillDate <= :quarterEndDate")
	List<Project> findActiveClientsForQuarter(@Param("status") String status,
											  @Param("quarterStartDate") Date quarterStartDate,
											  @Param("quarterEndDate") Date quarterEndDate);
}
