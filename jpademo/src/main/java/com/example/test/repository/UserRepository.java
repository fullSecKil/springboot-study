package com.example.test.repository;

import com.example.pojo.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Resource,Integer> {
    List<Resource> findAllById(Integer id);
    List<Resource> findAll();
}
