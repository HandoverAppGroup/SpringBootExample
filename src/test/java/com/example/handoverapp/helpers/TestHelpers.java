package com.example.handoverapp.helpers;

import com.example.handoverapp.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestHelpers {

    public static Task fakeTask_1() {
        Date date;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse("21-11-2020 12:00:00");
        } catch (ParseException e) {
            date = null;
        }
        Task task = new Task();
        Creator creator = new Creator("doctorId");
        TaskStatus status = new TaskStatus(false, date, null);
        TaskDetails details = new TaskDetails("blah","blah","blah");
        Patient patient = new Patient("df","London","Covid");
        task.setStatus(status);
        task.setDetails(details);
        task.setCreator(creator);
        task.setPatient(patient);
        return task;
    }

    public static String fakePostJson() {
        System.out.println(System.getProperty("user.dir"));
        try {
            return new String(Files.readAllBytes(Paths.get("./src/test/MockPostTask.json")));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "";
        }
    }

    public static String fakeGetJson() {
        System.out.println(System.getProperty("user.dir"));
        try {
            return new String(Files.readAllBytes(Paths.get("./src/test/MockTask.json")));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "";
        }
    }

    public static String getJsonFromTask(Task task) throws JsonProcessingException {
        ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().build();
        return objectMapper.writeValueAsString(task);
    }


}
