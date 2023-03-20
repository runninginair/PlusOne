package com.example.repository;

import com.example.model.Activity;
import com.example.model.User;
import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {
  //@Transactional
  public List<Activity> findByUserIdAndDate(User user, Date date);
  @Transactional
  @Query(nativeQuery = true,value = "select sum(distance) from activity a where a.user_id = :userId and a.date between :start and :end")
  public double getDistanceByTimeRange(@Param("userId") Long userId, @Param("start")Date start, @Param("end")Date end);
}