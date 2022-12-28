package com.example.springproject.repository;

import com.example.springproject.model.commit;
import com.example.springproject.model.developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommitRepository extends JpaRepository<commit, Long> {
    @Query(value = "SELECT time FROM commit " +
            "            where repo= ?1 " +
            "            order by time ", nativeQuery = true)
    List<String> findTime(int repo);
    @Query(value = "SELECT count(*) FROM commit " +
            "            where commit_hour= ?1  and repo= ?2 ", nativeQuery = true)
    int findHour(int hour,int repo);
}
