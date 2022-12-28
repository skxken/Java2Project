package com.example.springproject.repository;
import com.example.springproject.model.open_issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OpenIssueRepository extends JpaRepository<open_issue, Long> {
    @Query(value = "select total_num from open_issue where repo= ?1 ", nativeQuery = true)
    int findAllByRepo(int repo);
}
