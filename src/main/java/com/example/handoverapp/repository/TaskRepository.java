package com.example.handoverapp.repository;

import com.example.handoverapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


@RepositoryRestResource(collectionResourceRel = "tasks", path = "tasks")
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    // URIs for sorting by date are automatically generated
    // /api/tasks/?sort=dateCreated,asc
    // /api/tasks/?sort=dateCreated,desc

    @Query("SELECT t FROM Task t WHERE t.completed = true ")
    Collection<Task> findAllCompletedTasks();

    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    List<Task> findByPatientId(@Param("patientId") String patientId);

}
