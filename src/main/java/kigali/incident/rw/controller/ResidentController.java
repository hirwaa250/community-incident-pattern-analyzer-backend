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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kigali.incident.rw.domain.Resident;
import kigali.incident.rw.service.ResidentService;

@RestController
@RequestMapping (value = "/api/residents")
public class ResidentController {

    @Autowired
    private ResidentService residentServ;

    @PostMapping(value="/save")
    public ResponseEntity<?> saveResident(@RequestBody Resident resident){
        String returnedMessage = residentServ.saveResident(resident);
        return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
    }

    @GetMapping(value="/all")
    public ResponseEntity<?> getAllResidents(){
        List<Resident> residents = residentServ.getAllResidents();
        return new ResponseEntity<>(residents, HttpStatus.OK);
    }

    @GetMapping(value="/by-village")
    public ResponseEntity<?> getResidentsByVillage(@RequestParam String village){
        List<Resident> residents = residentServ.getResidentsByVillage(village);
        return new ResponseEntity<>(residents, HttpStatus.OK);
    }

    @GetMapping(value="/of-response-officer/{responseOfficerId}")
    public ResponseEntity<?> getResidentsOfResponseOfficer(@PathVariable UUID responseOfficerId){
        Optional<List<Resident>> residents = residentServ.getResidentsOfResponseOfficer(responseOfficerId);
        if(residents.isPresent()){
            return new ResponseEntity<>(residents.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The response officer with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getResidentById(@PathVariable UUID id){
        Optional<Resident> resident = residentServ.getResidentById(id);
        if(resident.isPresent()){
            return new ResponseEntity<>(resident.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The resident with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<?> updateResident(@PathVariable UUID id, @RequestBody Resident resident){
        String returnedMessage = residentServ.updateResident(id, resident);
        if(returnedMessage.equals("Resident updated successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<?> deleteResident(@PathVariable UUID id){
        String returnedMessage = residentServ.deleteResident(id);
        if(returnedMessage.equals("Resident deleted successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
