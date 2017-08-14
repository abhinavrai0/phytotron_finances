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
    private Long chamberId;

    @Temporal(value = TemporalType.DATE)
    @NotNull
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(value = TemporalType.DATE)
    @NotNull
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "carts_used")
    private Integer cartsUsed;

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

    public Long getChamberId() {
        return chamberId;
    }

    public void setChamberId(Long chamberId) {
        this.chamberId = chamberId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getCartsUsed() {
        return cartsUsed;
    }

    public void setCartsUsed(Integer cartsUsed) {
        this.cartsUsed = cartsUsed;
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

    @Override
    public String toString() {
        return "ProjectChamberMapping{" +
                "id=" + id +
                ", projectId='" + projectId + '\'' +
                ", chamberId=" + chamberId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cartsUsed=" + cartsUsed +
                ", status='" + status + '\'' +
                ", isSaved=" + isSaved +
                '}';
    }
}
