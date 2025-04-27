package gp2.StudentLifeCycle.StudentLifecylce.serviceimpl;

import gp2.StudentLifeCycle.StudentLifecylce.models.Document;
import gp2.StudentLifeCycle.StudentLifecylce.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DocumentServiceImplement {
    private final DocumentRepository documentRepository;

    public DocumentServiceImplement(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document saveDocument(MultipartFile file) throws IOException {
        Document document = new Document();
        document.setName(file.getOriginalFilename());
        document.setUrl(file.getBytes());
        document.setDoctype(Document.Doctype.CV);
        return documentRepository.save(document);
    }


    /*public List<Document> getDocumentsByApplicationId(Application application) {
        return documentRepository.findAllByApplicationId(application.getId());
    }

   public Optional<Document> getDocumentByApplicationId(Long applicationId) {
        return documentRepository.findByApplicationId(applicationId);
    }*/


}
