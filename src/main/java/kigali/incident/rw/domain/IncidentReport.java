package kigali.incident.rw.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="incident_report")
public class IncidentReport {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (name="incident_date")
    private LocalDate incidentDate;

    @Enumerated (EnumType.STRING)
    @Column (name="status")
    private ReportStatus status;

    @Column (name="location")
    private String location;

    @Column (name="description")
    private String description;

    @ManyToOne
    @JoinColumn (name="resident_id")
    private Resident resident;

    @ManyToOne
    @JoinColumn (name="response_officer_id")
    private ResponseOfficer responseOfficer;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(LocalDate incidentDate) {
        this.incidentDate = incidentDate;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public ResponseOfficer getResponseOfficer() {
        return responseOfficer;
    }

    public void setResponseOfficer(ResponseOfficer responseOfficer) {
        this.responseOfficer = responseOfficer;
    }

}
