package gp2.StudentLifeCycle.StudentLifecylce.serviceimpl;

import gp2.StudentLifeCycle.StudentLifecylce.dtos.DocumentDto;
import gp2.StudentLifeCycle.StudentLifecylce.models.Document;
import gp2.StudentLifeCycle.StudentLifecylce.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class DocumentServiceImplement {
    private final DocumentRepository documentRepository;

    public DocumentServiceImplement(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document saveDocument(DocumentDto file) throws IOException {
        Document document = documentRepository.findDocumentByName(file.getName());
        if (document != null) {
            return updateDocument(document.getId(),file);
        } else {
            Document document1 = new Document();
            document1.setName(file.getName());
            document1.setUrl(file.getUrl());
            document1.setCandidateId(file.getCandidateId());
            document1.setDoctype(Document.Doctype.CV);
            return documentRepository.save(document1);
        }
    }

    public Document updateDocument(Long id,DocumentDto document){
        return documentRepository.findById(id).map(doc -> {
            doc.setUrl(document.getUrl());
            return documentRepository.save(doc);
        }).orElseThrow(() -> new RuntimeException("Academic Background not found"));
    }

    public Document getDocumentById(Long id){
        return documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }

    public Optional<Document> getDocumentsByCandidateId(Long id){
        return documentRepository.findDocumentsByCandidateId(id);
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }

    /*public List<Document> getDocumentsByApplicationId(Application application) {
        return documentRepository.findAllByApplicationId(application.getId());
    }

   public Optional<Document> getDocumentByApplicationId(Long applicationId) {
        return documentRepository.findByApplicationId(applicationId);
    }*/


}
