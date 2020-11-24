package com.example.handoverapp.service;

import com.example.handoverapp.dto.TaskDTO;
import com.example.handoverapp.entity.Doctor;
import com.example.handoverapp.entity.Patient;
import com.example.handoverapp.entity.Task;
import com.example.handoverapp.repository.DoctorRepository;
import com.example.handoverapp.repository.PatientRepository;
import com.example.handoverapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class TaskService {

    TaskRepository tr;
    PatientRepository pr;
    DoctorRepository dr;

    public TaskService(TaskRepository tr, PatientRepository pr, DoctorRepository dr) {
        this.tr = tr;
        this.pr = pr;
        this.dr = dr;
    }

    public List<Task> getAll() {
        return tr.findAll();
    }

    public Optional<Task> getById(long id) {
        return tr.findById(id);
    }

    public void delete(long id) {
        tr.deleteById(id);
    }

    public Optional<Task> update(TaskDTO t, long id) {
        Optional<Task> opTask = tr.findById(id);
        return opTask.map(task -> updateFromDTO(t, task));
    }

    public Task create(TaskDTO t) {
        return updateFromDTO(t, new Task());
    }

    public List<Task> getTasksSince(String dateString) {
        SimpleDateFormat formatter=new SimpleDateFormat("ddMMyyyyHH");
        try {
            Date date = formatter.parse(dateString);
            return tr.findByDateCreatedGreaterThanEqual(date);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Task> getUncompletedTasks() {
        return tr.findByCompleted(false);
    }

    public List<Task> getRecentOrUncompletedTasks() {
        List<Task> returnList = tr.findByCompleted(false);
        List<Task> recent = tr.findByDateCreatedGreaterThanEqual(getYesterday());
        for (Task t: recent) {
            if (t.isCompleted()) {
                returnList.add(t);
            }
        }
        return returnList;
    }

    private static Date getYesterday() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        return Date.from(yesterday.atStartOfDay(defaultZoneId).toInstant());
    }


    private static boolean isNotNullOrEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    private Doctor retrieveDoctorOrCreateNew(String doctorIdentifier) {
        Optional<Doctor> d = dr.findByIdentifier(doctorIdentifier);
        if (d.isPresent()) {
            return d.get();
        } else {
            Doctor newDoctor = new Doctor();
            newDoctor.setIdentifier(doctorIdentifier);
            return dr.save(newDoctor);
        }
    }

    private Patient updatePatientOrCreateNew(String hospitalNum, String location, String diagnosis) {
        Optional<Patient> p = pr.findByHospitalNumber(hospitalNum);
        Patient patient;
        if (p.isPresent()) {
            patient = p.get();
        } else {
            patient = new Patient();
            patient.setHospitalNumber(hospitalNum);
        }
        if (isNotNullOrEmpty(location)) {
            patient.setLocation(location);
        }
        if (isNotNullOrEmpty(diagnosis)) {
            patient.setDiagnosis(diagnosis);
        }
        return pr.save(patient);
    }

    private Task updateFromDTO(TaskDTO t, Task task) {
        String coi = t.getCompleterIdentifier();
        String cri = t.getCreatorIdentifier();
        String pi = t.getPatientHospitalNum();
        String pl = t.getPatientLocation();
        String pd = t.getPatientDiagnosis();
        if (isNotNullOrEmpty(cri)) {
            task.setCreator(retrieveDoctorOrCreateNew(cri));
        }
        if (isNotNullOrEmpty(coi)) {
            task.setCompleter(retrieveDoctorOrCreateNew(coi));
        }
        if (isNotNullOrEmpty(pi)) {
            task.setPatient(updatePatientOrCreateNew(pi, pl, pd));
        } else if (task.getPatient() != null && (isNotNullOrEmpty(pl) || isNotNullOrEmpty(pd))) {
            task.setPatient(updatePatientOrCreateNew(task.getPatient().getHospitalNumber(), pl, pd));
        }
        if (isNotNullOrEmpty(t.getDescription())) {
            task.setDescription(t.getDescription());
        }
        if (isNotNullOrEmpty(t.getEscalationPlan())) {
            task.setEscalationPlan(t.getEscalationPlan());
        }
        if (isNotNullOrEmpty(t.getOutcome())) {
            task.setOutcome(t.getOutcome());
        }
        task.setCompleted(t.isCompleted());
        task.setDateCompleted(t.getDateCompleted());
        return tr.save(task);
    }



}
