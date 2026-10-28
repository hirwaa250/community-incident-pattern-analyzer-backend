package kigali.incident.rw.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "response_officer")
public class ResponseOfficer {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (name="first_name")
    private String firstName;

    @Column (name="last_name")
    private String lastName;

    @Column (name="phone")
    private String phone;

    @ManyToOne
    @JoinColumn (name="cell_id")
    private Cell cell;

    @ManyToMany
    @JoinTable (
        name = "response_officer_incident_type",
        joinColumns = @JoinColumn (name="response_officer_id"),
        inverseJoinColumns = @JoinColumn (name="incident_type_id")
    )
    private List<IncidentType> incidentTypes = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public List<IncidentType> getIncidentTypes() {
        return incidentTypes;
    }

    public void setIncidentTypes(List<IncidentType> incidentTypes) {
        this.incidentTypes = incidentTypes;
    }

}
