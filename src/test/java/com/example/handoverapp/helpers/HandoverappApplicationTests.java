package com.example.handoverapp.helpers;

import com.example.handoverapp.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HandoverappApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void deleteAllBeforeTests() throws Exception {
        taskRepository.deleteAll();
    }

    @Test
    public void shouldCreateEntity() throws Exception {

        mockMvc.perform(post("/api/tasks")
                .content("{\"description\": \"Check on patient\", \"outcome\":\"They are ok\"}")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("tasks/")));
    }

    @Test
    public void shouldRetrieveEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/api/tasks")
                .content("{\"description\": \"Check on patient\", \"outcome\":\"They are ok\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Check on patient"))
                .andExpect(jsonPath("$.outcome").value("They are ok"));
    }


    @Test
    public void shouldRetrieveUncompletedTasks() throws Exception {

        mockMvc.perform(post("/api/tasks")
                .content("{\"description\": \"Check on patient once\", \"completed\":\"true\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/tasks")
                .content("{\"description\": \"Check on patient twice\", \"completed\":\"false\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(
                get("/api/tasks/uncompleted"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].description").value("Check on patient twice"))
                .andExpect(jsonPath("$",hasSize(1)));
    }

    @Test
    public void shouldUpdateEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/api/tasks")
                .content("{\"description\": \"Check on patient\", \"outcome\":\"They are ok\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(put(location)
                .content("{\"description\": \"Check on patient twice\", \"outcome\":\"They are ok\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get(location))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Check on patient twice"))
                .andExpect(jsonPath("$.outcome").value("They are ok"));
    }

    @Test
    public void shouldPartiallyUpdateEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/api/tasks")
                .content("{\"description\": \"Check on patient twice\", \"outcome\":\"They are ok\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");

        mockMvc.perform(put(location)
                .content("{\"description\": \"Give patient milk\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Give patient milk"))
                .andExpect(jsonPath("$.outcome").value("They are ok"));
    }

    @Test
    public void shouldDeleteEntity() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/api/tasks")
                .content("{\"description\": \"Check on patient twice\", \"outcome\":\"They are ok\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        mockMvc.perform(delete(location)).andExpect(status().isNoContent());

        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }
}
