package edu.service;

import edu.model.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pinak on 6/23/2017.
 */
public interface InvoiceCRUD extends CrudRepository<Invoice, Long> {
    /*@Query("select i from Invoice i where i.projectId = :projectId and " +
            "i.generation_date = " +
            "(select max(ii.generation_date) from Invoice  ii where " +
            "ii.projectId = :projectId)" +
            "ORDER BY i.projectId DESC")
    List<Invoice> findInvoicesByProjectId(@Param("projectId") String projectId);*/
    //Invoice findTopByProjectIdEqualsOrderByGeneration_dateDesc(Long projectId);
    Invoice findFirstByProjectIdOrderByGenerationDateDesc(Long projectId);
}
