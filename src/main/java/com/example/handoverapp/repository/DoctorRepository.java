package com.example.handoverapp.repository;

import com.example.handoverapp.entity.Doctor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    @Query("select d from Doctor d where d.identifier = ?1")
    Optional<Doctor> findByIdentifier(String identifier);

}