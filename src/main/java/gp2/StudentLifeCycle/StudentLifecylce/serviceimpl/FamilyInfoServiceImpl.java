package gp2.StudentLifeCycle.StudentLifecylce.serviceimpl;

import gp2.StudentLifeCycle.StudentLifecylce.models.FamilyInfo;
import gp2.StudentLifeCycle.StudentLifecylce.repository.FamilyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyInfoServiceImpl {

    private final FamilyInfoRepository familyInfoRepository;

    @Autowired
    public FamilyInfoServiceImpl(FamilyInfoRepository familyInfoRepository) {
        this.familyInfoRepository = familyInfoRepository;
    }

    public FamilyInfo createFamilyInfo(FamilyInfo familyInfo) {
        return familyInfoRepository.save(familyInfo);
    }

    public Optional<FamilyInfo> getFamilyInfoById(String id) {
        return familyInfoRepository.findById(id);
    }

    public Optional<FamilyInfo> getFamilyInfoByCandidateId(Long id) {
        return familyInfoRepository.findFamilyInfoByCandidateId(id);
    }

    public List<FamilyInfo> getAllFamilyInfos() {
        return (List<FamilyInfo>) familyInfoRepository.findAll();
    }


    public FamilyInfo updateFamilyInfo(String id, FamilyInfo familyInfo) {
        return familyInfoRepository.findById(id)
                .map(existingFamilyInfo -> {
                    existingFamilyInfo.setCandidateId(familyInfo.getCandidateId());
                    existingFamilyInfo.setDadName(familyInfo.getDadName());
                    existingFamilyInfo.setDadJob(familyInfo.getDadJob());
                    existingFamilyInfo.setDadNum(familyInfo.getDadNum());
                    existingFamilyInfo.setMomName(familyInfo.getMomName());
                    existingFamilyInfo.setMomJob(familyInfo.getMomJob());
                    existingFamilyInfo.setMomNum(familyInfo.getMomNum());
                    existingFamilyInfo.setParentStatus(familyInfo.getParentStatus());
                    existingFamilyInfo.setSiblings(familyInfo.getSiblings());
                    existingFamilyInfo.setCandidateId(familyInfo.getCandidateId());
                    return familyInfoRepository.save(existingFamilyInfo);
                }).orElseThrow(() -> new RuntimeException("FamilyInfo not found"));
    }

    public void deleteFamilyInfo(String id) {
        familyInfoRepository.deleteById(id);
    }
}
