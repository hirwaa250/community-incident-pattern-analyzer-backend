package kigali.incident.rw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.incident.rw.domain.Cell;
import kigali.incident.rw.domain.IncidentType;
import kigali.incident.rw.domain.ResponseOfficer;
import kigali.incident.rw.repository.CellRepository;
import kigali.incident.rw.repository.IncidentTypeRepository;
import kigali.incident.rw.repository.ResponseOfficerRepository;

@Service
public class ResponseOfficerService {

    @Autowired
    private ResponseOfficerRepository responseOfficerRepo;

    @Autowired
    private CellRepository cellRepo;

    @Autowired
    private IncidentTypeRepository incidentTypeRepo;

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

    public String addIncidentType(UUID responseOfficerId, UUID incidentTypeId){
        Optional<ResponseOfficer> responseOfficer = responseOfficerRepo.findById(responseOfficerId);
        if(responseOfficer.isEmpty()){
            return "The response officer with that id does not exist";
        }
        Optional<IncidentType> incidentType = incidentTypeRepo.findById(incidentTypeId);
        if(incidentType.isEmpty()){
            return "That incident type does not exist";
        }
        ResponseOfficer toUpdate = responseOfficer.get();
        if(toUpdate.getIncidentTypes().contains(incidentType.get())){
            return "The response officer already has that incident type";
        }
        toUpdate.getIncidentTypes().add(incidentType.get());
        responseOfficerRepo.save(toUpdate);
        return "Incident type added to the response officer";
    }

    public String changeCell(UUID responseOfficerId, int cellNumber){
        Optional<ResponseOfficer> responseOfficer = responseOfficerRepo.findById(responseOfficerId);
        if(responseOfficer.isEmpty()){
            return "The response officer with that id does not exist";
        }
        Cell cell = cellRepo.findByCellNumber(cellNumber);
        if(cell == null){
            return "The cell with that cellNumber does not exist";
        }
        if(responseOfficerRepo.existsByCellCellNumberAndIdNot(cellNumber, responseOfficerId)){
            return "That cell is taken by another response officer";
        }
        ResponseOfficer toUpdate = responseOfficer.get();
        toUpdate.setCell(cell);
        responseOfficerRepo.save(toUpdate);
        return "Cell changed successfully";
    }
}
