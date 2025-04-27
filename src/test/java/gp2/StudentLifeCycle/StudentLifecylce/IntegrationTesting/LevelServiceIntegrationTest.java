package gp2.StudentLifeCycle.StudentLifecylce.IntegrationTesting;

import gp2.StudentLifeCycle.StudentLifecylce.models.Level;
import gp2.StudentLifeCycle.StudentLifecylce.repository.LevelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // Uses real DB (H2 for tests)
@Transactional  // Rollback after each test
public class LevelServiceIntegrationTest {

    @Autowired
    private LevelRepository levelRepository;

    private Level level;

    @BeforeEach
    public void setUp() {
        level = new Level();
        level.setName("Undergraduate");
        level.setFaculty("Engineering");
        level.setYear(2023);
        level.setLang("English");

        levelRepository.save(level);
    }

    @Test
    public void testCreateLevel() {
        Level newLevel = new Level();
        newLevel.setName("Postgraduate");
        newLevel.setFaculty("Science");
        newLevel.setYear(2024);
        newLevel.setLang("French");

        Level savedLevel = levelRepository.save(newLevel);
        assertNotNull(savedLevel.getId());
        assertEquals("Postgraduate", savedLevel.getName());
        assertEquals("Science", savedLevel.getFaculty());
    }

//    @Test
//    public void testGetAllLevels() {
//        List<Level> levels = (List<Level>) levelRepository.findAll();
//        assertFalse(levels.isEmpty());
//        assertEquals(1, levels.size());  // Only the `@BeforeEach` inserted record
//    }

    @Test
    public void testGetLevelById() {
        Optional<Level> foundLevel = levelRepository.findById(level.getId());
        assertTrue(foundLevel.isPresent());
        assertEquals(level.getName(), foundLevel.get().getName());
    }

    @Test
    public void testUpdateLevel() {
        Optional<Level> existingLevel = levelRepository.findById(level.getId());
        assertTrue(existingLevel.isPresent());

        Level updateLevel = existingLevel.get();
        updateLevel.setName("Updated Name");
        updateLevel.setFaculty("Updated Faculty");

        Level savedLevel = levelRepository.save(updateLevel);

        assertEquals("Updated Name", savedLevel.getName());
        assertEquals("Updated Faculty", savedLevel.getFaculty());
    }

    @Test
    public void testDeleteLevel() {
        levelRepository.deleteById(level.getId());
        Optional<Level> deletedLevel = levelRepository.findById(level.getId());
        assertFalse(deletedLevel.isPresent());
    }
}
