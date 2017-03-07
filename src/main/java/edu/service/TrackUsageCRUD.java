package edu.service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import edu.model.*;
public interface TrackUsageCRUD extends CrudRepository<TrackUsage, Long>{
}
