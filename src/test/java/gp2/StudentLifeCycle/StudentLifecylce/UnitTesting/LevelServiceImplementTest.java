package gp2.StudentLifeCycle.StudentLifecylce.UnitTesting;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.LevelDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Level;
import gp2.StudentLifeCycle.StudentLifecylce.repository.LevelRepository;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.LevelServiceImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

public class LevelServiceImplementTest {

    @Mock
    private LevelRepository levelRepository;

    @InjectMocks
    private LevelServiceImplement levelService;

    private Level level;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        level = new Level();
        level.setId(1L);
        level.setName("Undergraduate");
        level.setFaculty("Engineering");
        level.setYear(2023);
        level.setLang("English");
    }

    @Test
    public void testCreateLevel() {
        LevelDto levelDto = new LevelDto();
        levelDto.setName("Undergraduate");
        levelDto.setFaculty("Engineering");
        levelDto.setYear(2023);
        levelDto.setLang("English");

        when(levelRepository.save(any(Level.class))).thenReturn(level);

        Level createdLevel = levelService.createLevel(levelDto);

        assertNotNull(createdLevel);
        assertEquals(level.getName(), createdLevel.getName());
        assertEquals(level.getFaculty(), createdLevel.getFaculty());
        assertEquals(level.getYear(), createdLevel.getYear());
        assertEquals(level.getLang(), createdLevel.getLang());
        verify(levelRepository, times(1)).save(any(Level.class));
    }

    @Test
    public void testGetAllLevels() {
        List<Level> levels = new ArrayList<>();
        levels.add(level);
        when(levelRepository.findAll()).thenReturn(levels);

        List<Level> foundLevels = levelService.getAllLevels();

        assertFalse(foundLevels.isEmpty());
        assertEquals(1, foundLevels.size());
        assertEquals(level, foundLevels.get(0));
    }

    @Test
    public void testUpdateLevel() {
        Level updateLevel = new Level();
        updateLevel.setName("Postgraduate");
        updateLevel.setFaculty("Science");
        updateLevel.setYear(2024);
        updateLevel.setLang("French");

        when(levelRepository.findById(1L)).thenReturn(Optional.of(level));
        when(levelRepository.save(any(Level.class))).thenReturn(updateLevel);

        Level updatedLevel = levelService.updateLevel(1L, updateLevel);

        assertNotNull(updatedLevel);
        assertEquals("Postgraduate", updatedLevel.getName());
        assertEquals("Science", updatedLevel.getFaculty());
        assertEquals(2024, updatedLevel.getYear());
        assertEquals("French", updatedLevel.getLang());
        verify(levelRepository, times(1)).save(any(Level.class));
    }

    @Test
    public void testGetLevelById() {
        when(levelRepository.findById(1L)).thenReturn(Optional.of(level));

        Level foundLevel = levelService.getLevelById(1L);

        assertNotNull(foundLevel);
        assertEquals(level, foundLevel);
    }

    @Test
    public void testDeleteLevel() {
        doNothing().when(levelRepository).deleteById(1L);

        levelService.deleteLevel(1L);

        verify(levelRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateLevelNotFound() {
        Level updateLevel = new Level();
        updateLevel.setName("Postgraduate");

        when(levelRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            levelService.updateLevel(1L, updateLevel);
        });

        assertEquals("Level not found", exception.getMessage());
    }

    @Test
    public void testGetLevelByIdNotFound() {
        when(levelRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            levelService.getLevelById(1L);
        });

        assertEquals("Level not found", exception.getMessage());
    }
}
