package gp2.StudentLifeCycle.StudentLifecylce.serviceimpl;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.LevelDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Level;
import gp2.StudentLifeCycle.StudentLifecylce.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImplement {

    @Autowired
    private LevelRepository levelRepository;

    public Level createLevel(LevelDto levelDto) {
        Level level = new Level();
        level.setName(levelDto.getName());
        level.setFaculty(levelDto.getFaculty());
        level.setYear(levelDto.getYear());
        level.setLang(levelDto.getLang());
        return levelRepository.save(level);
    }

    public List<Level> getAllLevels() {
        return (List<Level>) levelRepository.findAll();
    }

    public Level updateLevel(Long id, Level updatelevel) {
        return levelRepository.findById(id)
                .map(existingLevel -> {
                    existingLevel.setName(updatelevel.getName());
                    existingLevel.setFaculty(updatelevel.getFaculty());
                    existingLevel.setYear(updatelevel.getYear());
                    existingLevel.setLang((updatelevel.getLang()));

                    return levelRepository.save(existingLevel);
                })
                .orElseThrow(() -> new RuntimeException("Level not found"));
    }




    public Level getLevelById(Long id) {
        return levelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Level not found"));
    }

    public void deleteLevel(Long id) {
        levelRepository.deleteById(id);
    }
}
