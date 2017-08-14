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
    private Date allocationDate;

    @Column(name = "deallocation_date")
    private Date deallocationDate;

    @Column(name = "comments")
    private String comments;

    @Column(name = "isInvoiced")
    private Boolean isInvoiced;

    @Transient
    private Boolean isSaved;

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

    public Date getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(Date allocationDate) {
        this.allocationDate = allocationDate;
    }

    public Date getDeallocationDate() {
        return deallocationDate;
    }

    public void setDeallocationDate(Date deallocationDate) {
        this.deallocationDate = deallocationDate;
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

    public Boolean getSaved() {
        return isSaved;
    }

    public void setSaved(Boolean saved) {
        isSaved = saved;
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
                ", allocationDate=" + allocationDate +
                ", deallocationDate=" + deallocationDate +
                ", comments='" + comments + '\'' +
                ", isInvoiced=" + isInvoiced +
                ", isSaved=" + isSaved +
                '}';
    }
}
