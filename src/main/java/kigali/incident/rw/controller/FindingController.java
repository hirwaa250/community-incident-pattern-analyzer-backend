package kigali.incident.rw.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kigali.incident.rw.domain.Finding;
import kigali.incident.rw.service.FindingService;

@RestController
@RequestMapping (value = "/api/findings")
public class FindingController {

    @Autowired
    private FindingService findingServ;

    @PostMapping(value="/save")
    public ResponseEntity<?> saveFinding(@RequestParam UUID incidentReportId, @RequestBody Finding finding){
        String returnedMessage = findingServ.saveFinding(incidentReportId, finding);
        if(returnedMessage.equals("Finding saved successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        }else if(returnedMessage.equals("The incident report with that id does not exist")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/resident/{nationalId}")
    public ResponseEntity<?> getFindingsOfResident(@PathVariable String nationalId){
        List<Finding> findings = findingServ.getFindingsOfResident(nationalId);
        return new ResponseEntity<>(findings, HttpStatus.OK);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<?> deleteFinding(@PathVariable UUID id){
        String returnedMessage = findingServ.deleteFinding(id);
        if(returnedMessage.equals("Finding deleted successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
