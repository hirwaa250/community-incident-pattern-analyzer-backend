package kigali.incident.rw.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kigali.incident.rw.domain.IncidentReport;
import kigali.incident.rw.service.IncidentReportService;

@RestController
@RequestMapping (value = "/api/incident-reports")
public class IncidentReportController {

    @Autowired
    private IncidentReportService incidentReportServ;

    @PostMapping(value="/save")
    public ResponseEntity<?> saveIncidentReport(@RequestBody IncidentReport incidentReport){
        String returnedMessage = incidentReportServ.saveIncidentReport(incidentReport);
        return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
    }

    @GetMapping(value="/all")
    public ResponseEntity<?> getAllIncidentReports(){
        List<IncidentReport> incidentReports = incidentReportServ.getAllIncidentReports();
        return new ResponseEntity<>(incidentReports, HttpStatus.OK);
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
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
