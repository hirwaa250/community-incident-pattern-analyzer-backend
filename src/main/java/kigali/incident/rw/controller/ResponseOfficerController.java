package kigali.incident.rw.controller;

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

import kigali.incident.rw.domain.ResponseOfficer;
import kigali.incident.rw.service.ResponseOfficerService;

@RestController
@RequestMapping (value = "/api/response-officers")
public class ResponseOfficerController {

    @Autowired
    private ResponseOfficerService responseOfficerServ;

    @PostMapping(value="/save")
    public ResponseEntity<?> saveResponseOfficer(@RequestBody ResponseOfficer responseOfficer){
        String returnedMessage = responseOfficerServ.saveResponseOfficer(responseOfficer);
        return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
    }

    @GetMapping(value="/all")
    public ResponseEntity<?> getAllResponseOfficers(){
        List<ResponseOfficer> responseOfficers = responseOfficerServ.getAllResponseOfficers();
        return new ResponseEntity<>(responseOfficers, HttpStatus.OK);
    }

    @GetMapping(value="/by-incident-type")
    public ResponseEntity<?> getResponseOfficersByIncidentType(@RequestParam String name){
        List<ResponseOfficer> responseOfficers = responseOfficerServ.getResponseOfficersByIncidentType(name);
        return new ResponseEntity<>(responseOfficers, HttpStatus.OK);
    }

    @PutMapping(value="/{responseOfficerId}/incident-types/{incidentTypeId}")
    public ResponseEntity<?> addIncidentType(@PathVariable UUID responseOfficerId, @PathVariable UUID incidentTypeId){
        String returnedMessage = responseOfficerServ.addIncidentType(responseOfficerId, incidentTypeId);
        if(returnedMessage.equals("Incident type added to the response officer")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else if(returnedMessage.equals("The response officer already has that incident type")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value="/{responseOfficerId}/cell")
    public ResponseEntity<?> changeCell(@PathVariable UUID responseOfficerId, @RequestParam int cellNumber){
        String returnedMessage = responseOfficerServ.changeCell(responseOfficerId, cellNumber);
        if(returnedMessage.equals("Cell changed successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else if(returnedMessage.equals("That cell is taken by another response officer")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getResponseOfficerById(@PathVariable UUID id){
        Optional<ResponseOfficer> responseOfficer = responseOfficerServ.getResponseOfficerById(id);
        if(responseOfficer.isPresent()){
            return new ResponseEntity<>(responseOfficer.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The response officer with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<?> updateResponseOfficer(@PathVariable UUID id, @RequestBody ResponseOfficer responseOfficer){
        String returnedMessage = responseOfficerServ.updateResponseOfficer(id, responseOfficer);
        if(returnedMessage.equals("Response officer updated successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<?> deleteResponseOfficer(@PathVariable UUID id){
        String returnedMessage = responseOfficerServ.deleteResponseOfficer(id);
        if(returnedMessage.equals("Response officer deleted successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
