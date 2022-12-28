package com.example.springproject.repository;

import com.example.springproject.model.developer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DeveloperRepository extends JpaRepository<developer, Long> {
    @Query(value = "select count(*) from developer where repo= ?1 ", nativeQuery = true)
    int findAllByRepo(int repo);

    @Query(value = "SELECT * FROM developer "
            + "where repo= ?1 "
            + "order by cast(commit_times as decimal) desc ", nativeQuery = true)
    List<developer> findByCommit_times(int repo);
}
