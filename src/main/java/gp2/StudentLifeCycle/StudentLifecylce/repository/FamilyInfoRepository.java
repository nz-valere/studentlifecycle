package gp2.StudentLifeCycle.StudentLifecylce.repository;

import gp2.StudentLifeCycle.StudentLifecylce.models.FamilyInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FamilyInfoRepository extends CrudRepository<FamilyInfo, String> {
    // Add custom query methods if needed
    Optional<FamilyInfo> findFamilyInfoByCandidateId(Long id);
}