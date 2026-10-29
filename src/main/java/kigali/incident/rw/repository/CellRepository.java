package kigali.incident.rw.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kigali.incident.rw.domain.Cell;

@Repository
public interface CellRepository extends JpaRepository<Cell, UUID> {

    @Query("SELECT c FROM Cell c WHERE NOT EXISTS (SELECT o FROM ResponseOfficer o WHERE o.cell = c) ORDER BY c.cellNumber ASC")
    List<Cell> findFreeCells();
}
