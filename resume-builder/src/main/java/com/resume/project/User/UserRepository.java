package com.resume.project.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(nativeQuery = true,value = "select * from tbl_users where id=:userId and is_deleted = 0;")
    User getById(Long userId);
    @Query(nativeQuery = true,value = "select * from tbl_users where is_deleted = 0;")
    List<User> getAllById();
}
