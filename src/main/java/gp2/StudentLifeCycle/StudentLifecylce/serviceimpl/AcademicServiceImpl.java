package gp2.StudentLifeCycle.StudentLifecylce.serviceimpl;

import gp2.StudentLifeCycle.StudentLifecylce.models.Academic;
import gp2.StudentLifeCycle.StudentLifecylce.repository.AcademicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicServiceImpl {

    private AcademicRepository academicRepository;

    @Autowired
    public AcademicServiceImpl(AcademicRepository academicRepository) {
        this.academicRepository = academicRepository;
    }


    public Academic saveAcademic(Academic academic) {
        return academicRepository.save(academic);
    }


    public Optional<Academic> getAcademicById(Long id) {
        return academicRepository.findById(id);

    }

    public Optional<Academic> getAcademicByCandidateId(Long id) {
        return academicRepository.findAcademicByCandidateId(id);
    }

    public List<Academic> getAllAcademics() {
        return (List<Academic>) academicRepository.findAll();
    }

    public Academic updateAcademic(Long id, Academic academic) {
        return academicRepository.findById(id)
                .map(existingAcademic -> {
                    existingAcademic.setId(academic.getId());
                    existingAcademic.setField(academic.getField());
                    existingAcademic.setCandidateId(academic.getCandidateId());
                    existingAcademic.setClassNum(academic.getClassNum());
                    existingAcademic.setCollege(academic.getCollege());
                    existingAcademic.setTown(academic.getTown());
                    existingAcademic.setHaveAL(academic.isHaveAL());
                    existingAcademic.setAlYear(academic.getAlYear());
                    existingAcademic.setClassPosition1(academic.getClassPosition1());
                    existingAcademic.setClassPosition2(academic.getClassPosition2());
                    existingAcademic.setClassPosition3(academic.getClassPosition3());
                    existingAcademic.setGradesAL(academic.getGradesAL());
                    existingAcademic.setGradesOL(academic.getGradesOL());

                    return academicRepository.save(existingAcademic);
                })
                .orElseThrow(() -> new RuntimeException("Academic Background not found"));
        // or throw an exception
    }

    public void deleteAcademic(Long id) {
        academicRepository.deleteById(id);
    }
}