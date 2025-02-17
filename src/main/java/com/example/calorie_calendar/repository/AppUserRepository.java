package com.example.calorie_calendar.repository;
import java.util.*;

import com.example.calorie_calendar.calendar.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

   Optional<AppUser> findByName(String name);

   @Query("SELECT u.activities FROM AppUser u WHERE u.id = :id")
   List<Activity> findActivitiesByUserID(@Param("id") Long id);

   @Query("SELECT u.weeklyTotal FROM AppUser u WHERE u.id = :id")
   WeeklyTotal findWeeklyTotalByUserID(@Param("id") Long id);

   @Query("SELECT a FROM AppUser u JOIN u.activities a WHERE u.id = :id AND a.distance >= :distance")
   List<Activity> filterActivitiesByDistance(@Param("id") Long id, @Param("distance") double distance);

   Optional<Activity> findActivityById(Long id);
   
   void saveActivity(Activity activity);
}
