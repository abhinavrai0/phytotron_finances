package edu.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import edu.model.*;
import org.springframework.data.repository.query.Param;

//public interface IMovieRepository extends CrudRepository<Movie, Long> {
public interface ClientCRUD extends CrudRepository<Client, Long>{
//	List<BillInfo> findByName(int project_user_name);

}
