package gp2.StudentLifeCycle.StudentLifecylce.repository;

import gp2.StudentLifeCycle.StudentLifecylce.models.Application;
import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import gp2.StudentLifeCycle.StudentLifecylce.models.Document;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends CrudRepository<Document, Long> {


   /* @Query("SELECT d FROM Document d WHERE d.application.id = :applicationId")
    List<Document> findAllByApplicationId(@Param("applicationId")Long applicationId);

    Optional<Document> findByApplicationId(Long applicationId);*/
}
