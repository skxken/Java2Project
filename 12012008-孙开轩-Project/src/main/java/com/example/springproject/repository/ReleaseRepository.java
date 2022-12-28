package com.example.springproject.repository;

import com.example.springproject.model.release;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ReleaseRepository extends JpaRepository<release, Long> {
    @Query(value = "select count(*) from release where repo= ?1 ", nativeQuery = true)
    int findAllByRepo(int repo);

    @Query(value = "SELECT release_time FROM release "
            + "            where repo= ?1 "
            + "            order by release_time ", nativeQuery = true)
    List<String> findTime(int repo);
}
