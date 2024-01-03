package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.dto.LeavePolicyDetailsDTO;
import org.utj.hrh.model.FinancialYear;
import org.utj.hrh.model.LeavePolicy;
import org.utj.hrh.repository.LeavePolicyRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LeavePolicyService {
    @Autowired
    private LeavePolicyRepository leavePolicyRepository;


    public List<LeavePolicy> getAll(){
        return  leavePolicyRepository.findAll();
    }

    public void save(LeavePolicy leavePolicy) {
        boolean isUpdatingPolicy = (leavePolicy.getId() !=null);
//        if (isUpdatingCarderType){
//            CarderType existingCarder = carderTypeDao.findById(carderType.getId()).get();
//            carderTypeDao.save(carderType);
//        }else {
//
//        }
        leavePolicyRepository.save(leavePolicy);
    }
    //    get carder type
    public LeavePolicy getPolicy(Long id) throws EntityNotFoundException {
        try{
            return leavePolicyRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new EntityNotFoundException("could not find Policy with  ID :"+id);
        }

    }
    public void delete(Long id) throws EntityNotFoundException{
        Long countById= leavePolicyRepository.countById(id);
        if (countById==null || countById==0){
            throw new EntityNotFoundException("could not find any Policy with ID :"+id);
        }
        leavePolicyRepository.deleteById(id);
    }
    
    public List<LeavePolicy> getPoliciesForFinancialYear(Integer financialYearId) {
        FinancialYear financialYear = new FinancialYear();
        financialYear.setId(financialYearId);
        return leavePolicyRepository.findLeavePoliciesByFinancialYear(financialYear);
    }
    
    public LeavePolicyDetailsDTO getPolicyDetails(Long policyId) throws EntityNotFoundException {
        LeavePolicy policy = leavePolicyRepository.findById(policyId)
                .orElseThrow(() -> new EntityNotFoundException("Leave Policy not found"));
        return convertToDTO(policy);
    }
    
    private LeavePolicyDetailsDTO convertToDTO(LeavePolicy policy) {
        // Conversion logic
        LeavePolicyDetailsDTO dto = new LeavePolicyDetailsDTO();
        // Populate fields from policy to dto
        dto.setId(policy.getId());
        dto.setDays(policy.getDays());
        dto.setGender(policy.getGender());
        dto.setFromDate(policy.getFinancialYear().getStart_date());
        dto.setToDate(policy.getFinancialYear().getEnd_date());
        return dto;
    }
}
