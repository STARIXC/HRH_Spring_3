package org.utj.hrh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.utj.hrh.model.DocumentType;
import org.utj.hrh.services.DocumentTypeNotFoundException;
import org.utj.hrh.services.DocumentTypeService;

import java.util.List;

@Controller
@RequestMapping("/system/documents")
public class DocumentTypeController {
    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping("/list")
    public String viewDocumentTypes(Model model) {
        List<DocumentType> documentTypeList = documentTypeService.getAll();
        DocumentType documentType= new DocumentType();
        model.addAttribute("documentTypeList", documentTypeList);
        model.addAttribute("documentType",documentType);
        model.addAttribute("pageTitle", "Document Type");
        return "pages/documents";
    }
    @PostMapping("/save")
    public String saveDocType(@ModelAttribute("documentType") DocumentType documentType) {
        if (documentType.getId() == null) {
            // It's a new role, so save it as a new record
            documentTypeService.save(documentType);
        } else {
            // It's an edit, so update the existing role
            documentTypeService.save(documentType);
        }
        return "redirect:/system/documents/list"; // Redirect back to the same page
    }

    @GetMapping("/deleted/{id}")
    public String deleteRole(@PathVariable Integer id) throws DocumentTypeNotFoundException {
        // Delete the role with the specified ID
        documentTypeService.delete(id);
        return "redirect:/system/documents/list"; // Redirect back to the same page
    }


    @DeleteMapping("/delete/{id}")
    public String deleteDocument(@PathVariable Integer id) throws DocumentTypeNotFoundException {
        documentTypeService.delete(id);
        return "redirect:/system/documents/list";
    }

    // Endpoint to fetch document type data by ID
    @GetMapping("/get/{docTypeId}")
    @ResponseBody
    public DocumentType getDocumentTypeById(@PathVariable Integer docTypeId) throws DocumentTypeNotFoundException {
        // Use your service to retrieve the document type by its ID
        DocumentType documentType = documentTypeService.getDocumentType(docTypeId);
        return documentType;
    }

}
