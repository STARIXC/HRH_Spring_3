package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.utj.hrh.model.License;

import org.utj.hrh.repository.LicenseRepository;

import java.util.List;

@Service
public class LicenseService {
    private final LicenseRepository licenseRepository;

    @Autowired
    public LicenseService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;


    }
    public License getLicenseByName(String name){
        return licenseRepository.findByName(name);
    }
    public List<License> getAll() {
        return licenseRepository.findAll();
    }

    public License findLicenseById(Long id) {
        return licenseRepository.findById(id).orElse(null);
    }

    public void save(License license) {
        boolean isUpdatingEducation = (license.getId() !=null);
//        if (isUpdatingCarderType){
//            CarderType existingCarder = carderTypeDao.findById(carderType.getId()).get();
//            carderTypeDao.save(carderType);
//        }else {
//
//        }
        licenseRepository.save(license);
    }

    public void delete(Long id) throws EntityNotFoundException{
        Long countById= licenseRepository.countById(id);
        if (countById==null || countById==0){
            throw new EntityNotFoundException("could not find any Record with ID :"+id);
        }
        licenseRepository.deleteById(id);
    }
}
