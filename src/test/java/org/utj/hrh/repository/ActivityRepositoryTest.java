package org.utj.hrh.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.utj.hrh.model.Activity;

import java.util.List;

@SpringBootTest
class ActivityRepositoryTest {
    @Autowired
    private ActivityRepository activityRepository;

    @Test
    void saveMethod() {
//        activity opbject
        Activity activity = new Activity();
        activity.setActivity_description("Activity Test");
        activity.setActivity_cadre_type(2);
//save method
        Activity saveActivity = activityRepository.save(activity);
//     display activity info
        System.out.println(saveActivity.getActivity_id());
        System.out.println(saveActivity);
    }

    @Test
    void updateUsingSave() {
//        find or retrieve an entity by id

        Long id = 6l;
        Activity activity = activityRepository.findById(id).get();
        //        update entity info
        activity.setActivity_description("Test activity updated");
//        activity.setActivity_description("");
//        save update entity
        activityRepository.save(activity);
    }

    @Test
    void findByIdMethod() {
        Long id = 1l;
        Activity activity = activityRepository.findById(id).get();
        System.out.println(activity);

    }

    @Test
    void saveAll() {
        Activity activity = new Activity();
        activity.setActivity_description("Activity Test");
        activity.setActivity_cadre_type(2);
        Activity activity2 = new Activity();
        activity2.setActivity_description("Activity Test 2");
        activity2.setActivity_cadre_type(2);
        Activity activity3 = new Activity();
        activity3.setActivity_description("Activity Test 3");
        activity3.setActivity_cadre_type(2);

        Activity activity4 = new Activity();
        activity4.setActivity_description("Activity Test 4");
        activity4.setActivity_cadre_type(2);

        activityRepository.saveAll(List.of(activity,activity2,activity3,activity4));

    }

    @Test
    void findAll(){
        List<Activity> activities= activityRepository.findAll();
        activities.forEach((p)->{
            System.out.println(p.getActivity_description());
        });
    }
    @Test
    void deleteByID(){
        Long id=12L;
        activityRepository.deleteById(id);
    }

    @Test
    void countActivities(){
      long activities=  activityRepository.count();
      System.out.println(activities);
    }
//
//    @Test
//    void existById(){
//        Long id =1L;
//        boolean exists=activityRepository.existsById(id);
//
//        System.out.println(exists);
//    }
}