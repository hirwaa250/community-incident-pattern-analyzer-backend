package kigali.incident.rw.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.incident.rw.domain.ReportStatus;
import kigali.incident.rw.repository.CellRepository;
import kigali.incident.rw.repository.FindingRepository;
import kigali.incident.rw.repository.IncidentReportRepository;
import kigali.incident.rw.repository.ResidentRepository;
import kigali.incident.rw.repository.ResponseOfficerRepository;

@Service
public class DashboardService {

    @Autowired
    private ResidentRepository residentRepo;

    @Autowired
    private ResponseOfficerRepository responseOfficerRepo;

    @Autowired
    private CellRepository cellRepo;

    @Autowired
    private IncidentReportRepository incidentReportRepo;

    @Autowired
    private FindingRepository findingRepo;

    public Map<String, Long> getSummary(){
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("residents", residentRepo.count());
        summary.put("responseOfficers", responseOfficerRepo.count());
        summary.put("freeCells", (long) cellRepo.findFreeCells().size());
        summary.put("incidentsToday", incidentReportRepo.countByIncidentDate(LocalDate.now()));
        summary.put("reported", incidentReportRepo.countByStatus(ReportStatus.REPORTED));
        summary.put("findings", findingRepo.count());
        return summary;
    }
}
