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

import kigali.incident.rw.domain.IncidentType;
import kigali.incident.rw.service.IncidentTypeService;

@RestController
@RequestMapping (value = "/api/incident-types")
public class IncidentTypeController {

    @Autowired
    private IncidentTypeService incidentTypeServ;

    @PostMapping(value="/save")
    public ResponseEntity<?> saveIncidentType(@RequestBody IncidentType incidentType){
        String returnedMessage = incidentTypeServ.saveIncidentType(incidentType);
        return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
    }

    @GetMapping(value="/all")
    public ResponseEntity<?> getAllIncidentTypes(){
        List<IncidentType> incidentTypes = incidentTypeServ.getAllIncidentTypes();
        return new ResponseEntity<>(incidentTypes, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getIncidentTypeById(@PathVariable UUID id){
        Optional<IncidentType> incidentType = incidentTypeServ.getIncidentTypeById(id);
        if(incidentType.isPresent()){
            return new ResponseEntity<>(incidentType.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The incident type with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<?> updateIncidentType(@PathVariable UUID id, @RequestBody IncidentType incidentType){
        String returnedMessage = incidentTypeServ.updateIncidentType(id, incidentType);
        if(returnedMessage.equals("Incident type updated successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<?> deleteIncidentType(@PathVariable UUID id){
        String returnedMessage = incidentTypeServ.deleteIncidentType(id);
        if(returnedMessage.equals("Incident type deleted successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
