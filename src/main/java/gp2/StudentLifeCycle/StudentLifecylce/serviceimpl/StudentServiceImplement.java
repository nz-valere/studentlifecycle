package gp2.StudentLifeCycle.StudentLifecylce.serviceimpl;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.CandidateDto;
import gp2.StudentLifeCycle.StudentLifecylce.dtos.StudentDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Application;
import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import gp2.StudentLifeCycle.StudentLifecylce.models.Level;
import gp2.StudentLifeCycle.StudentLifecylce.models.Student;
import gp2.StudentLifeCycle.StudentLifecylce.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImplement {

    @Autowired
    public StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();

    }

    public Student createStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setBirthdate(studentDto.getBirthdate());
        student.setEmail(studentDto.getEmail());
        student.setPhone(studentDto.getPhone());
        student.setLevel(studentDto.getLevel());
        student.setCandidateId(studentDto.getCandidateId());
        student.setStatus(Student.Status.ENROLLED);
        return studentRepository.save(student);
    }



    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setName(updatedStudent.getName());
                    existingStudent.setEmail(updatedStudent.getEmail());
                    existingStudent.setPhone(updatedStudent.getPhone());
                    existingStudent.setStatus(updatedStudent.getStatus());
                    existingStudent.setBirthdate(updatedStudent.getBirthdate());
                    existingStudent.setLevel(updatedStudent.getLevel());
                    existingStudent.setCandidateId(updatedStudent.getCandidateId());

                    return studentRepository.save(existingStudent);
                })
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }


    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
