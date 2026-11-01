package kigali.incident.rw.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kigali.incident.rw.domain.IncidentReport;
import kigali.incident.rw.domain.ReportStatus;
import kigali.incident.rw.service.IncidentReportService;

@RestController
@RequestMapping (value = "/api/incident-reports")
public class IncidentReportController {

    @Autowired
    private IncidentReportService incidentReportServ;

    @PostMapping(value="/save")
    public ResponseEntity<?> saveIncidentReport(@RequestBody IncidentReport incidentReport){
        String returnedMessage = incidentReportServ.saveIncidentReport(incidentReport);
        if(returnedMessage.equals("Incident report saved successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        }else if(returnedMessage.equals("Response officer is already assigned an incident on that date")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value="/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable UUID id, @RequestParam ReportStatus status){
        String returnedMessage = incidentReportServ.updateStatus(id, status);
        if(returnedMessage.equals("Incident report status updated successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else if(returnedMessage.equals("The incident report with that id does not exist")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/by-status")
    public ResponseEntity<?> getIncidentReportsByStatus(@RequestParam ReportStatus status){
        List<IncidentReport> incidentReports = incidentReportServ.getIncidentReportsByStatus(status);
        return new ResponseEntity<>(incidentReports, HttpStatus.OK);
    }

    @GetMapping(value="/between")
    public ResponseEntity<?> getIncidentReportsBetween(@RequestParam String start, @RequestParam String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<IncidentReport> incidentReports = incidentReportServ.getIncidentReportsBetween(startDate, endDate);
        return new ResponseEntity<>(incidentReports, HttpStatus.OK);
    }

    @GetMapping(value="/all")
    public ResponseEntity<?> getAllIncidentReports(){
        List<IncidentReport> incidentReports = incidentReportServ.getAllIncidentReports();
        return new ResponseEntity<>(incidentReports, HttpStatus.OK);
    }

    @GetMapping(value="/stats/by-status")
    public ResponseEntity<?> getIncidentReportCountsByStatus(){
        List<Object[]> stats = incidentReportServ.getIncidentReportCountsByStatus();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @PatchMapping(value="/cancel-day")
    public ResponseEntity<?> cancelResponseOfficerDay(@RequestParam UUID responseOfficerId, @RequestParam String date){
        LocalDate day = LocalDate.parse(date);
        String returnedMessage = incidentReportServ.cancelResponseOfficerDay(responseOfficerId, day);
        return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getIncidentReportById(@PathVariable UUID id){
        Optional<IncidentReport> incidentReport = incidentReportServ.getIncidentReportById(id);
        if(incidentReport.isPresent()){
            return new ResponseEntity<>(incidentReport.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The incident report with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<?> updateIncidentReport(@PathVariable UUID id, @RequestBody IncidentReport incidentReport){
        String returnedMessage = incidentReportServ.updateIncidentReport(id, incidentReport);
        if(returnedMessage.equals("Incident report updated successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<?> deleteIncidentReport(@PathVariable UUID id){
        String returnedMessage = incidentReportServ.deleteIncidentReport(id);
        if(returnedMessage.equals("Incident report deleted successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else if(returnedMessage.equals("That incident report has findings, delete them first")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
