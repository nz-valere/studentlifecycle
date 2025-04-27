package gp2.StudentLifeCycle.StudentLifecylce.repository;

import gp2.StudentLifeCycle.StudentLifecylce.models.Level;
import gp2.StudentLifeCycle.StudentLifecylce.models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LevelRepository extends CrudRepository<Level, Long> {

}
