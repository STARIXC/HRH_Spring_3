package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.CarderType;
import org.utj.hrh.model.LeavePolicy;
import org.utj.hrh.repository.CarderTypeRepository;
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
    public LeavePolicy getPolicy(Integer id) throws EntityNotFoundException {
        try{
            return leavePolicyRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw new EntityNotFoundException("could not find Policy with  ID :"+id);
        }

    }
    public void delete(Integer id) throws EntityNotFoundException{
        Long countById= leavePolicyRepository.countById(id);
        if (countById==null || countById==0){
            throw new EntityNotFoundException("could not find any Policy with ID :"+id);
        }
        leavePolicyRepository.deleteById(id);
    }
}
