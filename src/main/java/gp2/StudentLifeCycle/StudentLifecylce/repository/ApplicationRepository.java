package gp2.StudentLifeCycle.StudentLifecylce.repository;

import gp2.StudentLifeCycle.StudentLifecylce.models.Application;
import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import gp2.StudentLifeCycle.StudentLifecylce.models.Document;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends CrudRepository<Application, Long> {
    Optional<Application> findApplicationById(Long aLong);

    /*List<Application> findAllByCandidateId( Long candidateId);

    Optional<Application> findApplicationByCandidateId(Long CandidateId);*/


}
