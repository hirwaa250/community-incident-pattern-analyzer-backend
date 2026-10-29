package kigali.incident.rw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.incident.rw.domain.ResponseOfficer;
import kigali.incident.rw.repository.ResponseOfficerRepository;

@Service
public class ResponseOfficerService {

    @Autowired
    private ResponseOfficerRepository responseOfficerRepo;

    public String saveResponseOfficer(ResponseOfficer responseOfficer){
        responseOfficerRepo.save(responseOfficer);
        return "Response officer saved successfully";
    }

    public List<ResponseOfficer> getAllResponseOfficers(){
        return responseOfficerRepo.findAll();
    }

    public Optional<ResponseOfficer> getResponseOfficerById(UUID id){
        return responseOfficerRepo.findById(id);
    }

    public String updateResponseOfficer(UUID id, ResponseOfficer responseOfficer){
        Optional<ResponseOfficer> existing = responseOfficerRepo.findById(id);
        if(existing.isEmpty()){
            return "The response officer with that id does not exist";
        }
        ResponseOfficer toUpdate = existing.get();
        toUpdate.setFirstName(responseOfficer.getFirstName());
        toUpdate.setLastName(responseOfficer.getLastName());
        toUpdate.setPhone(responseOfficer.getPhone());
        toUpdate.setCell(responseOfficer.getCell());
        responseOfficerRepo.save(toUpdate);
        return "Response officer updated successfully";
    }

    public String deleteResponseOfficer(UUID id){
        if(!responseOfficerRepo.existsById(id)){
            return "The response officer with that id does not exist";
        }
        responseOfficerRepo.deleteById(id);
        return "Response officer deleted successfully";
    }

    public List<ResponseOfficer> getResponseOfficersByIncidentType(String name){
        return responseOfficerRepo.findResponseOfficersByIncidentTypeName(name);
    }
}
