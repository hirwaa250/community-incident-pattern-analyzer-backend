package kigali.incident.rw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.incident.rw.domain.IncidentType;
import kigali.incident.rw.repository.IncidentTypeRepository;

@Service
public class IncidentTypeService {

    @Autowired
    private IncidentTypeRepository incidentTypeRepo;

    public String saveIncidentType(IncidentType incidentType){
        incidentTypeRepo.save(incidentType);
        return "Incident type saved successfully";
    }

    public List<IncidentType> getAllIncidentTypes(){
        return incidentTypeRepo.findAll();
    }

    public Optional<IncidentType> getIncidentTypeById(UUID id){
        return incidentTypeRepo.findById(id);
    }

    public String updateIncidentType(UUID id, IncidentType incidentType){
        Optional<IncidentType> existing = incidentTypeRepo.findById(id);
        if(existing.isEmpty()){
            return "The incident type with that id does not exist";
        }
        IncidentType toUpdate = existing.get();
        toUpdate.setName(incidentType.getName());
        incidentTypeRepo.save(toUpdate);
        return "Incident type updated successfully";
    }

    public String deleteIncidentType(UUID id){
        if(!incidentTypeRepo.existsById(id)){
            return "The incident type with that id does not exist";
        }
        incidentTypeRepo.deleteById(id);
        return "Incident type deleted successfully";
    }
}
