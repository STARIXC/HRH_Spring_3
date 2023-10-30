package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.DocumentType;
import org.utj.hrh.repository.DocumentTypeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DocumentTypeService {
    @Autowired
    private DocumentTypeRepository documentTypeRepository;


    public List<DocumentType> getAll(){
        return (List<DocumentType>) documentTypeRepository.findAll();
    }

    public void save(DocumentType documentType) {
        boolean isUpdatingDocumentType = (documentType.getId() !=null);
//        if (isUpdatingDocumentType){
//            DocumentType existingCarder = carderTypeDao.findById(carderType.getId()).get();
//            carderTypeDao.save(carderType);
//        }else {
//
//        }
        documentTypeRepository.save(documentType);
    }
    //    get carder type
    public DocumentType getDocumentType(Integer id) throws DocumentTypeNotFoundException {
        try{
            return documentTypeRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new DocumentTypeNotFoundException("could not find any carder with ID :"+id);
        }

    }
    public void delete(Integer id) throws DocumentTypeNotFoundException{
        Long countById= documentTypeRepository.countById(id);
        if (countById==null || countById==0){
            throw new DocumentTypeNotFoundException("could not find any carder with ID :"+id);
        }
        documentTypeRepository.deleteById(id);
    }
}
