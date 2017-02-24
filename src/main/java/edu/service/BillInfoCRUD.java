package edu.service;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import edu.model.*;
//public interface IMovieRepository extends CrudRepository<Movie, Long> {
public interface BillInfoCRUD extends CrudRepository<BillInfo, Long>{
//	List<BillInfo> findByName(int project_user_name);
}
