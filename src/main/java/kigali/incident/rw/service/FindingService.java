package kigali.incident.rw.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.incident.rw.domain.Finding;
import kigali.incident.rw.domain.IncidentReport;
import kigali.incident.rw.domain.ReportStatus;
import kigali.incident.rw.repository.FindingRepository;
import kigali.incident.rw.repository.IncidentReportRepository;

@Service
public class FindingService {

    @Autowired
    private FindingRepository findingRepo;

    @Autowired
    private IncidentReportRepository incidentReportRepo;

    public String saveFinding(UUID incidentReportId, Finding finding){
        Optional<IncidentReport> incidentReport = incidentReportRepo.findById(incidentReportId);
        if(incidentReport.isEmpty()){
            return "The incident report with that id does not exist";
        }
        if(incidentReport.get().getStatus() != ReportStatus.CLOSED){
            return "Findings are only for CLOSED incident reports";
        }
        finding.setRecordedDate(LocalDate.now());
        finding.setIncidentReport(incidentReport.get());
        findingRepo.save(finding);
        return "Finding saved successfully";
    }

    public List<Finding> getFindingsOfResident(String nationalId){
        return findingRepo.findFindingsOfResident(nationalId);
    }

    public String deleteFinding(UUID id){
        if(!findingRepo.existsById(id)){
            return "The finding with that id does not exist";
        }
        findingRepo.deleteById(id);
        return "Finding deleted successfully";
    }
}
