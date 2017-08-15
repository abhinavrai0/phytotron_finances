package edu.service;

import edu.model.Project;
import edu.model.ProjectChamberMapping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by pinak on 8/11/2017.
 */
public interface ProjectChamberMappingCRUD extends CrudRepository<ProjectChamberMapping, Long>{
    List<ProjectChamberMapping> findAllByProjectIdAndStatusEquals(String projectId, String status);
    //List<ProjectChamberMapping> findDistinctByChamberNameAndStatusEqualsAndAllocationDateBetweenAndDeAllocationDateBetween(String status, Date startDate, Date endDate, Date dStartDate, Date dEndDate);
    //ProjectChamberMapping findTopByChamberIdAAndStatusEqualsOrderByAllocatedCartsDesc(String chamberId, String status);

    @Query("select p from ProjectChamberMapping p where p.status = :status and " +
            "p.deallocationDate>= :startDate " +
            "and p.allocationDate <= :endDate")
    List<ProjectChamberMapping> getChamberUsageSummary(@Param("status") String status,
                                              @Param("startDate") Date startDate,
                                              @Param("endDate") Date endDate);

}
