package gp2.StudentLifeCycle.StudentLifecylce.UnitTesting;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.StudentDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Candidate;
import gp2.StudentLifeCycle.StudentLifecylce.models.Level;
import gp2.StudentLifeCycle.StudentLifecylce.models.Student;
import gp2.StudentLifeCycle.StudentLifecylce.repository.StudentRepository;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.StudentServiceImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class StudentServiceImplementTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImplement studentService;

    private Student student;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setId(1L);
        Level level = new Level().setFaculty("Ing").setYear(2).setName("Ing 2 Anglo");
        student.setName("John Doe");
        student.setBirthdate(LocalDate.of(2000, 1, 1));
        student.setEmail("john.doe@example.com");
        student.setPhone("1234567890");
        student.setLevel(level);
        Candidate candidate = new Candidate();
        candidate.setId(1L);
        candidate.setName("John Doe");
        candidate.setEmail("john.doe@example.com");
        candidate.setPhone("1234567890");
        candidate.setBirthdate(LocalDate.of(1990, 1, 1));
        candidate.setStatus(Candidate.Status.NEW);
        candidate.setLevel(level);
        student.setCandidateId(candidate);
        student.setStatus(Student.Status.ENROLLED);
    }

    @Test
    public void testGetAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(student);
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> foundStudents = studentService.getAllStudents();

        assertEquals(1, foundStudents.size());
        assertEquals(student, foundStudents.get(0));
    }

    @Test
    public void testCreateStudent() {
        StudentDto studentDto = new StudentDto();
        studentDto.setName("John Doe");
        studentDto.setBirthdate(LocalDate.of(2000, 1, 1));
        studentDto.setEmail("john.doe@example.com");
        studentDto.setPhone("1234567890");
        Level level = new Level().setFaculty("Ing").setYear(2).setName("Ing 2 Anglo");
        studentDto.setLevel(level);
        Candidate candidate = new Candidate();
        candidate.setId(1L);
        candidate.setName("John Doe");
        candidate.setEmail("john.doe@example.com");
        candidate.setPhone("1234567890");
        candidate.setBirthdate(LocalDate.of(2000, 1, 1));
        candidate.setStatus(Candidate.Status.NEW);
        candidate.setLevel(level);
        studentDto.setCandidateId(candidate);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student createdStudent = studentService.createStudent(studentDto);

        assertEquals(student.getName(), createdStudent.getName());
        assertEquals(student.getEmail(), createdStudent.getEmail());
        assertEquals(student.getPhone(), createdStudent.getPhone());
        assertEquals(student.getLevel(), createdStudent.getLevel());
        assertEquals(student.getCandidateId(), createdStudent.getCandidateId());
        assertEquals(student.getStatus(), createdStudent.getStatus());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    public void testUpdateStudent() {
        Student updatedStudent = new Student();
        updatedStudent.setName("Jane Doe");
        updatedStudent.setEmail("jane.doe@example.com");
        updatedStudent.setPhone("0987654321");
        updatedStudent.setStatus(Student.Status.ENROLLED);
        updatedStudent.setBirthdate(LocalDate.of(2000, 1, 1));
        Level level = new Level().setFaculty("Ing").setYear(2).setName("Ing 2 Anglo");
        updatedStudent.setLevel(level);
        Candidate candidate = new Candidate();
        candidate.setId(1L);
        candidate.setName("Jane Doe");
        candidate.setEmail("jane.doe@example.com");
        candidate.setPhone("0987654321");
        candidate.setBirthdate(LocalDate.of(2000, 1, 1));
        candidate.setStatus(Candidate.Status.NEW);
        candidate.setLevel(level);
        updatedStudent.setCandidateId(candidate);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        Student result = studentService.updateStudent(1L, updatedStudent);

        assertEquals(updatedStudent.getName(), result.getName());
        assertEquals(updatedStudent.getEmail(), result.getEmail());
        assertEquals(updatedStudent.getPhone(), result.getPhone());
        assertEquals(updatedStudent.getStatus(), result.getStatus());
        assertEquals(updatedStudent.getBirthdate(), result.getBirthdate());
        assertEquals(updatedStudent.getLevel(), result.getLevel());
        assertEquals(updatedStudent.getCandidateId(), result.getCandidateId());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    public void testGetStudentById() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student foundStudent = studentService.getStudentById(1L);

        assertEquals(student, foundStudent);
    }

    @Test
    public void testDeleteStudent() {
        doNothing().when(studentRepository).deleteById(1L);

        studentService.deleteStudent(1L);

        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateStudentNotFound() {
        Student updatedStudent = new Student();
        updatedStudent.setName("Jane Doe");

        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            studentService.updateStudent(1L, updatedStudent);
        });

        assertEquals("Student not found", exception.getMessage());
    }
}
