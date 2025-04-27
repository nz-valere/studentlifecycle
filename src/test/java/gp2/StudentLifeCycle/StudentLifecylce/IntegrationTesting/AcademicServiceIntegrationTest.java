package gp2.StudentLifeCycle.StudentLifecylce.IntegrationTesting;

import static org.assertj.core.api.Assertions.assertThat;

import gp2.StudentLifeCycle.StudentLifecylce.models.Academic;
import gp2.StudentLifeCycle.StudentLifecylce.repository.AcademicRepository;
import gp2.StudentLifeCycle.StudentLifecylce.serviceimpl.AcademicServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AcademicServiceIntegrationTest {

    @Autowired
    private AcademicServiceImpl academicService;

    @Autowired
    private AcademicRepository academicRepository;

    @Test
    public void testSaveAndGetAcademic() {
        Academic academic = new Academic();
        academic.setField("Mathematics");
        academic.setCandidateId(456L);
        // Set other fields as necessary

        Academic savedAcademic = academicService.saveAcademic(academic);
        assertThat(savedAcademic).isNotNull();
        assertThat(savedAcademic.getId()).isNotNull();

        Optional<Academic> foundAcademic = academicService.getAcademicById(savedAcademic.getId());
        assertThat(foundAcademic).isPresent();
        assertThat(foundAcademic.get().getField()).isEqualTo("Mathematics");
    }

    @Test
    public void testGetAllAcademics() {
        Academic academic1 = new Academic();
        academic1.setField("Physics");
        academic1.setCandidateId(789L);
        academicService.saveAcademic(academic1);

        Academic academic2 = new Academic();
        academic2.setField("Chemistry");
        academic2.setCandidateId(1011L);
        academicService.saveAcademic(academic2);

        List<Academic> academics = academicService.getAllAcademics();
        assertThat(academics).hasSize(2);
    }
}