package com.example.handoverapp.repository;

import com.example.handoverapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.completed = true ")
    Collection<Task> findAllCompletedTasks();

}
