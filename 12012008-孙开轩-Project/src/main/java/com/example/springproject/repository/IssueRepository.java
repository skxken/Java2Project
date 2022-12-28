package com.example.springproject.repository;

import com.example.springproject.model.issue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IssueRepository extends JpaRepository<issue, Long> {
    @Query(value = "select count(*) from issue where repo= ?1 ", nativeQuery = true)
    int findAllByRepo(int repo);

    @Query(value = "select opening_days from issue where repo= ?1 ", nativeQuery = true)
    List<Integer> findDaysByRepo(int repo);
}
