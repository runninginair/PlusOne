package com.example.repository;

import com.example.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Transactional
    int deleteUserByUserName(String userName);
    User getUserByUserName(String userName);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.height = :newHeight WHERE u.userId = :userID")
    void updateUserHeight(Long userID, Double newHeight);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.weight = :newWeight WHERE u.userId = :userID")
    void updateUserWeight(Long userID, Double newWeight);

}
