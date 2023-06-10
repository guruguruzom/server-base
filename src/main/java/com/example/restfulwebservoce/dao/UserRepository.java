package com.example.restfulwebservoce.dao;

import com.example.restfulwebservoce.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //data base에 관련된 bean
public interface UserRepository extends JpaRepository<User, Integer> {
}
