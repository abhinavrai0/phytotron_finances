package edu.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pinakin Abhyankar on 7/24/2017.
 */

@Entity
@Table(name = "project_resource_mapping")
public class ProjectResourceMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "resource_id")
    private Long resourceId;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "resource_description")
    private String resourceDescription;

    @Column(name = "rate")
    private Float rate;

    @Column(name = "units_consumed")
    private Float unitsConsumed;

    @Column(name = "allocation_date")
    private Date allocatiionDate;

    @Column(name = "deallocation_date")
    private Date deAllocationDate;

    @Column(name = "comments")
    private String comments;

    @Column(name = "isInvoiced")
    private Boolean isInvoiced;

    public ProjectResourceMapping() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Float getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(Float unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public Date getAllocatiionDate() {
        return allocatiionDate;
    }

    public void setAllocatiionDate(Date allocatiionDate) {
        this.allocatiionDate = allocatiionDate;
    }

    public Date getDeAllocationDate() {
        return deAllocationDate;
    }

    public void setDeAllocationDate(Date deAllocationDate) {
        this.deAllocationDate = deAllocationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Boolean getInvoiced() {
        return isInvoiced;
    }

    public void setInvoiced(Boolean invoiced) {
        isInvoiced = invoiced;
    }

    @Override
    public String toString() {
        return "ProjectResourceMapping{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", projectId='" + projectId + '\'' +
                ", resourceDescription='" + resourceDescription + '\'' +
                ", rate=" + rate +
                ", unitsConsumed=" + unitsConsumed +
                ", allocatiionDate=" + allocatiionDate +
                ", deAllocationDate=" + deAllocationDate +
                ", comments='" + comments + '\'' +
                ", isInvoiced=" + isInvoiced +
                '}';
    }
}
