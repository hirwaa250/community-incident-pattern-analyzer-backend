package kigali.incident.rw.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.incident.rw.domain.Cell;

@Repository
public interface CellRepository extends JpaRepository<Cell, UUID> {

}
