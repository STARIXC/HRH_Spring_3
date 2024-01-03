package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.Country;
import org.utj.hrh.repository.CountryRepository;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;


    }
    public Country getCountryByName(String name){
        return countryRepository.findByName(name);
    }
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country findCountryByCountryCode(String cou_code) {
        return countryRepository.findById(cou_code).orElse(null);
    }

    public void save(Country country) {
            countryRepository.save(country);
    }

    public void delete(String cou_code) throws EntityNotFoundException{
        Long countById= countryRepository.countByCountryCode(cou_code);
        if (countById==null || countById==0){
            throw new EntityNotFoundException("could not find any Record with ID :"+cou_code);
        }
        countryRepository.deleteById(cou_code);
    }
}
