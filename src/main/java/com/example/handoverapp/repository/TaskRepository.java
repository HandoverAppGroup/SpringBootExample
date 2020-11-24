package com.example.handoverapp.repository;


import com.example.handoverapp.entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from Task t where t.patient.hospitalNumber = ?1")
    List<Task> findByPatient(String hospitalNumber);

    @Query("select t from Task t where t.creator.identifier = ?1")
    List<Task> findByCreator(String doctorIdentifier);

    @Query("select t from Task t where t.completer.identifier = ?1")
    List<Task> findByCompleter(String doctorIdentifier);

    @Query("select t from Task t where t.dateCreated >= ?1")
    List<Task> findByDateCreatedGreaterThanEqual(Date earliest);

    @Query("select t from Task t where t.completed = ?1")
    List<Task> findByCompleted(boolean isCompleted);

}
