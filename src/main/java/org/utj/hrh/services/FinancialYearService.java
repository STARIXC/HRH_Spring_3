package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.FinancialYear;
import org.utj.hrh.repository.FyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialYearService {
    private final FyRepository fyRepository;
    @Autowired
    public FinancialYearService(FyRepository fyRepository) {
        this.fyRepository = fyRepository;
    }




    public List<FinancialYear> getAllFY() {
        return fyRepository.findAll();
    }

    public void save(FinancialYear financialYear) {
        boolean isUpdatingCarderCategory = (financialYear.getId() !=null);

        fyRepository.save(financialYear);
    }
    
    public Optional<FinancialYear> getYear(Integer year) {
        return fyRepository.findById(year);
    }
    
    public FinancialYear getFinancialYear(Integer financialYearId) {
        return fyRepository.findById(financialYearId).get();
    }
}
