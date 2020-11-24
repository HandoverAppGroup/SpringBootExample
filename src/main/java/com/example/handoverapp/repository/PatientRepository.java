package com.example.handoverapp.repository;

import com.example.handoverapp.entity.Patient;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    @Query("select p from Patient p where p.hospitalNumber = ?1")
    Optional<Patient> findByHospitalNumber(String hospitalNumber);

}