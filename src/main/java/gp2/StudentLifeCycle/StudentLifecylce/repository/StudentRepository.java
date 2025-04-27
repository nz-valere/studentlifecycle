package gp2.StudentLifeCycle.StudentLifecylce.repository;

import gp2.StudentLifeCycle.StudentLifecylce.models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findStudentById(Long aLong);


}
