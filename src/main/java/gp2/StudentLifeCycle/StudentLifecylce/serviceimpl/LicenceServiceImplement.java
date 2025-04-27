package gp2.StudentLifeCycle.StudentLifecylce.serviceimpl;

import gp2.StudentLifeCycle.StudentLifecylce.models.Academic;
import gp2.StudentLifeCycle.StudentLifecylce.models.Licence;
import gp2.StudentLifeCycle.StudentLifecylce.repository.AcademicRepository;
import gp2.StudentLifeCycle.StudentLifecylce.repository.LicenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LicenceServiceImplement {

    @Autowired
    private LicenceRepository licenceRepository;

    @Autowired
    public LicenceServiceImplement(LicenceRepository licenceRepository) {
        this.licenceRepository = licenceRepository;
    }

    public Licence saveLicence(Licence licence) {
        return licenceRepository.save(licence);
    }

    public Optional<Licence> getLicenceById(Long id) {
        return licenceRepository.findById(id);
    }

    public List<Licence> getAllLicences() {
        return (List<Licence>) licenceRepository.findAll();
    }

    public Licence updateLicence(Long id, Licence updatedLicence) {
        return licenceRepository.findById(id).map(existingLicence -> {
            existingLicence.setUniversity(updatedLicence.getUniversity());
            existingLicence.setField(updatedLicence.getField());
            existingLicence.setSpeciality(updatedLicence.getSpeciality());
            existingLicence.setCandidateId(updatedLicence.getCandidateId());
            return licenceRepository.save(existingLicence);
        }).orElseThrow(() -> new RuntimeException("Licence not found with id: " + id));
    }

    public void deleteLicence(Long id) {
        licenceRepository.deleteById(id);
    }
}
