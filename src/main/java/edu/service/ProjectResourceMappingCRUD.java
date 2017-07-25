package edu.service;

import edu.model.Project;
import edu.model.ProjectResourceMapping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by pinak on 7/24/2017.
 */
public interface ProjectResourceMappingCRUD extends CrudRepository<ProjectResourceMapping, Long>{
    List<ProjectResourceMapping> findAllByProjectId(String projectId);
}
