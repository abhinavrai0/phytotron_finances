package edu.service;

import edu.model.Invoice;
import edu.model.InvoiceReportView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pinak on 6/27/2017.
 */
public interface InvoiceReportViewCRUD extends CrudRepository<InvoiceReportView, Long> {

    //public InvoiceReportView findFirstByInvoiceId(String invoiceId);
    @Query("select i from InvoiceReportView  i where i.invoice_id = :invoiceId")
    List<InvoiceReportView> findRecordByInvoiceId(@Param("invoiceId") String invoiceId);
}
