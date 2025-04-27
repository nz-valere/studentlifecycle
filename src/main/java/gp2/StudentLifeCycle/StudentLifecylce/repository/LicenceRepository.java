package gp2.StudentLifeCycle.StudentLifecylce.repository;

import gp2.StudentLifeCycle.StudentLifecylce.models.Academic;
import gp2.StudentLifeCycle.StudentLifecylce.models.Licence;
import org.springframework.data.repository.CrudRepository;

public interface LicenceRepository extends CrudRepository<Licence,Long> {
}
