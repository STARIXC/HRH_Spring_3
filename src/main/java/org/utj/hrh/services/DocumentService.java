package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.Document;
import org.utj.hrh.repository.DocumentRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getAll(){
        return (List<Document>) documentRepository.findAll();
    }


    public void save(Document document) {
        boolean isUpdatingCarderCategory = (document.getId() !=null);
//        if (isUpdatingCarderType){
//            CarderType existingCarder = carderTypeDao.findById(carderType.getId()).get();
//            carderTypeDao.save(carderType);
//        }else {
//
//        }
        documentRepository.save(document);
    }

    //    get document
    public Document getDocument(Integer id) throws DocumentNotFoundException {
        try{
            return documentRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new DocumentNotFoundException("could not find any carder with ID :"+id);
        }

    }
    public void delete(Integer id) throws  DocumentNotFoundException {
        Long countById= documentRepository.countById(id);
        if (countById==null || countById==0){
            throw new DocumentNotFoundException("could not find any carder with ID :"+id);
        }
        documentRepository.deleteById(id);
    }
}
