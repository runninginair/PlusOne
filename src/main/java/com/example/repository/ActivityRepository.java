package com.example.repository;

import com.example.model.Activity;
import com.example.model.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Long> {

  public List<Activity> findByUserIdAndDate(User user, Date date);

 }