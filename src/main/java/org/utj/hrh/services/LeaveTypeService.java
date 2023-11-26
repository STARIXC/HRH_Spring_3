package org.utj.hrh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.utj.hrh.model.LeavePolicy;
import org.utj.hrh.model.LeaveType;
import org.utj.hrh.repository.LeavePolicyRepository;
import org.utj.hrh.repository.LeaveTypeRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LeaveTypeService {
    @Autowired
    private LeaveTypeRepository leaveTypeRepository;


    public List<LeaveType> getAll(){
        return  leaveTypeRepository.findAll();
    }

    public void save(LeaveType leaveType) {
        boolean isUpdatingPolicy = (leaveType.getLeaveTypeId() !=null);
//        if (isUpdatingCarderType){
//            CarderType existingCarder = carderTypeDao.findById(carderType.getId()).get();
//            carderTypeDao.save(carderType);
//        }else {
//
//        }
        leaveTypeRepository.save(leaveType);
    }
    //    get carder type
    public LeaveType getLeaveType(Integer id) throws EntityNotFoundException {
        try{
            return leaveTypeRepository.findLeaveTypeByLeaveTypeId(id);
        }catch (NoSuchElementException ex){
            throw new EntityNotFoundException("could not find Policy with  ID :"+id);
        }

    }
    public void delete(Integer id) throws EntityNotFoundException{
        Long countById= leaveTypeRepository.countLeaveTypeByLeaveTypeId(id);
        if (countById==null || countById==0){
            throw new EntityNotFoundException("could not find any Policy with ID :"+id);
        }
        leaveTypeRepository.deleteById(id);
    }
}
