package edu.service;

import edu.model.ProjectChamberMapping;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by pinak on 8/11/2017.
 */
public interface ProjectChamberMappingCRUD extends CrudRepository<ProjectChamberMapping, Long>{
    List<ProjectChamberMapping> findAllByProjectIdAndStatusEquals(String projectId, String status);
    //List<ProjectChamberMapping> findDistinctByChamberNameAndStatusEqualsAndAllocationDateBetweenAndDeAllocationDateBetween(String status, Date startDate, Date endDate, Date dStartDate, Date dEndDate);
    //ProjectChamberMapping findTopByChamberIdAAndStatusEqualsOrderByAllocatedCartsDesc(String chamberId, String status);

}
