package edu.service;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import edu.model.*;
public interface CropCRUD extends CrudRepository<Crop, Long>{
}
