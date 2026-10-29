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

import kigali.incident.rw.domain.Cell;
import kigali.incident.rw.service.CellService;

@RestController
@RequestMapping (value = "/api/cells")
public class CellController {

    @Autowired
    private CellService cellServ;

    @PostMapping(value="/save")
    public ResponseEntity<?> saveCell(@RequestBody Cell cell){
        String returnedMessage = cellServ.saveCell(cell);
        return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
    }

    @GetMapping(value="/all")
    public ResponseEntity<?> getAllCells(){
        List<Cell> cells = cellServ.getAllCells();
        return new ResponseEntity<>(cells, HttpStatus.OK);
    }

    @GetMapping(value="/free")
    public ResponseEntity<?> getFreeCells(){
        List<Cell> cells = cellServ.getFreeCells();
        return new ResponseEntity<>(cells, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> getCellById(@PathVariable UUID id){
        Optional<Cell> cell = cellServ.getCellById(id);
        if(cell.isPresent()){
            return new ResponseEntity<>(cell.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The cell with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<?> updateCell(@PathVariable UUID id, @RequestBody Cell cell){
        String returnedMessage = cellServ.updateCell(id, cell);
        if(returnedMessage.equals("Cell updated successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<?> deleteCell(@PathVariable UUID id){
        String returnedMessage = cellServ.deleteCell(id);
        if(returnedMessage.equals("Cell deleted successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
