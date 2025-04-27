package gp2.StudentLifeCycle.StudentLifecylce.UnitTesting;

import gp2.StudentLifeCycle.StudentLifecylce.models.Academic;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import gp2.StudentLifeCycle.StudentLifecylce.repository.AcademicRepository;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.AcademicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class AcademicServiceImplTest {

    @InjectMocks
    private AcademicServiceImpl academicService;

    @Mock
    private AcademicRepository academicRepository;

    private Academic academic;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        academic = new Academic();
        academic.setId(1L);
        academic.setField("Computer Science");
        academic.setCandidateId(123L);
        // Set other fields as necessary
    }

    @Test
    public void testSaveAcademic() {
        when(academicRepository.save(any(Academic.class))).thenReturn(academic);

        Academic savedAcademic = academicService.saveAcademic(academic);

        assertNotNull(savedAcademic);
        assertEquals(academic.getId(), savedAcademic.getId());
        verify(academicRepository, times(1)).save(academic);
    }

    @Test
    public void testGetAcademicById() {
        when(academicRepository.findById(1L)).thenReturn(Optional.of(academic));

        Optional<Academic> foundAcademic = academicService.getAcademicById(1L);

        assertTrue(foundAcademic.isPresent());
        assertEquals(academic.getId(), foundAcademic.get().getId());
        verify(academicRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateAcademic() {
        when(academicRepository.findById(1L)).thenReturn(Optional.of(academic));
        when(academicRepository.save(any(Academic.class))).thenReturn(academic);

        Academic updatedAcademic = academicService.updateAcademic(1L, academic);

        assertNotNull(updatedAcademic);
        verify(academicRepository, times(1)).findById(1L);
        verify(academicRepository, times(1)).save(academic);
    }

    @Test
    public void testDeleteAcademic() {
        doNothing().when(academicRepository).deleteById(1L);

        academicService.deleteAcademic(1L);

        verify(academicRepository, times(1)).deleteById(1L);
    }
}