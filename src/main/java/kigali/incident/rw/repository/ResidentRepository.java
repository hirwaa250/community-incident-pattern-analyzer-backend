package kigali.incident.rw.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.incident.rw.domain.Resident;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, UUID> {

    List<Resident> findByVillageIgnoreCaseOrderByLastNameAsc(String village);
}
