package kigali.incident.rw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.incident.rw.domain.Cell;
import kigali.incident.rw.repository.CellRepository;

@Service
public class CellService {

    @Autowired
    private CellRepository cellRepo;

    public String saveCell(Cell cell){
        cellRepo.save(cell);
        return "Cell saved successfully";
    }

    public List<Cell> getAllCells(){
        return cellRepo.findAll();
    }

    public Optional<Cell> getCellById(UUID id){
        return cellRepo.findById(id);
    }

    public String updateCell(UUID id, Cell cell){
        Optional<Cell> existing = cellRepo.findById(id);
        if(existing.isEmpty()){
            return "The cell with that id does not exist";
        }
        Cell toUpdate = existing.get();
        toUpdate.setCellNumber(cell.getCellNumber());
        toUpdate.setName(cell.getName());
        cellRepo.save(toUpdate);
        return "Cell updated successfully";
    }

    public String deleteCell(UUID id){
        if(!cellRepo.existsById(id)){
            return "The cell with that id does not exist";
        }
        cellRepo.deleteById(id);
        return "Cell deleted successfully";
    }
}
