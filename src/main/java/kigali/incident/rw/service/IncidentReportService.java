package kigali.incident.rw.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.incident.rw.domain.IncidentReport;
import kigali.incident.rw.domain.ReportStatus;
import kigali.incident.rw.repository.IncidentReportRepository;

@Service
public class IncidentReportService {

    @Autowired
    private IncidentReportRepository incidentReportRepo;

    public String saveIncidentReport(IncidentReport incidentReport){
        if(incidentReport.getIncidentDate().isBefore(LocalDate.now())){
            return "Incident report date cannot be in the past";
        }
        if(incidentReport.getResponseOfficer() == null){
            return "Response officer is required";
        }
        boolean alreadyAssigned = incidentReportRepo.existsByResponseOfficerIdAndIncidentDateAndStatusNot(
                incidentReport.getResponseOfficer().getId(), incidentReport.getIncidentDate(), ReportStatus.DISMISSED);
        if(alreadyAssigned){
            return "Response officer is already assigned an incident on that date";
        }
        incidentReport.setStatus(ReportStatus.REPORTED);
        incidentReportRepo.save(incidentReport);
        return "Incident report saved successfully";
    }

    public List<IncidentReport> getAllIncidentReports(){
        return incidentReportRepo.findAll();
    }

    public Optional<IncidentReport> getIncidentReportById(UUID id){
        return incidentReportRepo.findById(id);
    }

    public String updateIncidentReport(UUID id, IncidentReport incidentReport){
        Optional<IncidentReport> existing = incidentReportRepo.findById(id);
        if(existing.isEmpty()){
            return "The incident report with that id does not exist";
        }
        IncidentReport toUpdate = existing.get();
        toUpdate.setIncidentDate(incidentReport.getIncidentDate());
        toUpdate.setLocation(incidentReport.getLocation());
        toUpdate.setDescription(incidentReport.getDescription());
        toUpdate.setResident(incidentReport.getResident());
        toUpdate.setResponseOfficer(incidentReport.getResponseOfficer());
        incidentReportRepo.save(toUpdate);
        return "Incident report updated successfully";
    }

    public String deleteIncidentReport(UUID id){
        if(!incidentReportRepo.existsById(id)){
            return "The incident report with that id does not exist";
        }
        incidentReportRepo.deleteById(id);
        return "Incident report deleted successfully";
    }

    public String updateStatus(UUID id, ReportStatus status){
        Optional<IncidentReport> existing = incidentReportRepo.findById(id);
        if(existing.isEmpty()){
            return "The incident report with that id does not exist";
        }
        IncidentReport toUpdate = existing.get();
        ReportStatus current = toUpdate.getStatus();
        boolean allowed = false;
        if(current == ReportStatus.REPORTED){
            allowed = status == ReportStatus.INVESTIGATING || status == ReportStatus.DISMISSED;
        }else if(current == ReportStatus.INVESTIGATING){
            allowed = status == ReportStatus.CLOSED || status == ReportStatus.DISMISSED;
        }
        if(!allowed){
            return "Cannot change status from " + current + " to " + status;
        }
        toUpdate.setStatus(status);
        incidentReportRepo.save(toUpdate);
        return "Incident report status updated successfully";
    }

    public List<IncidentReport> getIncidentReportsByStatus(ReportStatus status){
        return incidentReportRepo.findByStatusOrderByIncidentDateAsc(status);
    }

    public List<IncidentReport> getIncidentReportsBetween(LocalDate start, LocalDate end){
        return incidentReportRepo.findByIncidentDateBetweenOrderByIncidentDateAsc(start, end);
    }
}
