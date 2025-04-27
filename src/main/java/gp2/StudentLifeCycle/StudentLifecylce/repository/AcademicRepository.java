package gp2.StudentLifeCycle.StudentLifecylce.repository;

import gp2.StudentLifeCycle.StudentLifecylce.models.Academic;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface  AcademicRepository extends CrudRepository<Academic,Long> {
    Optional<Academic> findAcademicByCandidateId(Long id);
}
