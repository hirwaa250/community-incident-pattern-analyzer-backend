package kigali.incident.rw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.incident.rw.domain.Resident;
import kigali.incident.rw.repository.ResidentRepository;
import kigali.incident.rw.repository.ResponseOfficerRepository;

@Service
public class ResidentService {

    @Autowired
    private ResidentRepository residentRepo;

    @Autowired
    private ResponseOfficerRepository responseOfficerRepo;

    public String saveResident(Resident resident){
        residentRepo.save(resident);
        return "Resident saved successfully";
    }

    public List<Resident> getAllResidents(){
        return residentRepo.findAll();
    }

    public Optional<Resident> getResidentById(UUID id){
        return residentRepo.findById(id);
    }

    public String updateResident(UUID id, Resident resident){
        Optional<Resident> existing = residentRepo.findById(id);
        if(existing.isEmpty()){
            return "The resident with that id does not exist";
        }
        Resident toUpdate = existing.get();
        toUpdate.setFirstName(resident.getFirstName());
        toUpdate.setLastName(resident.getLastName());
        toUpdate.setNationalId(resident.getNationalId());
        toUpdate.setVillage(resident.getVillage());
        residentRepo.save(toUpdate);
        return "Resident updated successfully";
    }

    public String deleteResident(UUID id){
        if(!residentRepo.existsById(id)){
            return "The resident with that id does not exist";
        }
        residentRepo.deleteById(id);
        return "Resident deleted successfully";
    }

    public List<Resident> getResidentsByVillage(String village){
        return residentRepo.findByVillageIgnoreCaseOrderByLastNameAsc(village);
    }

    public Optional<List<Resident>> getResidentsOfResponseOfficer(UUID responseOfficerId){
        if(!responseOfficerRepo.existsById(responseOfficerId)){
            return Optional.empty();
        }
        return Optional.of(residentRepo.findResidentsOfResponseOfficer(responseOfficerId));
    }
}
