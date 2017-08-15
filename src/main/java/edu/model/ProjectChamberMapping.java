package edu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by pinak on 8/10/2017.
 */
@Entity
@Table(name = "project_chamber_mapping")
public class ProjectChamberMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_id")
    @NotNull
    private String projectId;

    @Column(name = "chamber_id")
    @NotNull
    private String chamberName;

    @Temporal(value = TemporalType.DATE)
    @NotNull
    @Column(name = "start_date")
    private Date allocationDate;

    @Temporal(value = TemporalType.DATE)
    @NotNull
    @Column(name = "end_date")
    private Date deAllocationDate;

    @Column(name = "carts_used")
    private Integer allocatedCarts;

    @Column(name = "status")
    private String status;


    @Transient
    private Boolean isSaved;

    public ProjectChamberMapping() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getChamberName() {
        return chamberName;
    }

    public void setChamberName(String chamberName) {
        this.chamberName = chamberName;
    }

    public Date getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(Date allocationDate) {
        this.allocationDate = allocationDate;
    }

    public Date getDeAllocationDate() {
        return deAllocationDate;
    }

    public void setDeAllocationDate(Date deAllocationDate) {
        this.deAllocationDate = deAllocationDate;
    }

    public Integer getAllocatedCarts() {
        return allocatedCarts;
    }

    public void setAllocatedCarts(Integer allocatedCarts) {
        this.allocatedCarts = allocatedCarts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getSaved() {
        return isSaved;
    }

    public void setSaved(Boolean saved) {
        isSaved = saved;
    }
}
