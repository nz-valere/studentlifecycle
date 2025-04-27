package gp2.StudentLifeCycle.StudentLifecylce.repository;

import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {
    Optional<Candidate> findCandidateById(Long aLong);
    Optional<Candidate> findCandidateByEmail(String email);
    List<Candidate> findCandidateByStatus(Candidate.Status status);
}
