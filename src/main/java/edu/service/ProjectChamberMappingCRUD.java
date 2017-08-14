package edu.service;

import edu.model.ProjectChamberMapping;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by pinak on 8/11/2017.
 */
public interface ProjectChamberMappingCRUD extends CrudRepository<ProjectChamberMapping, Long>{
    List<ProjectChamberMapping> findAllByProjectId(String projectId);

}
