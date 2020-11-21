package com.example.handoverapp.helpers;

import com.example.handoverapp.controller.TaskController;
import com.example.handoverapp.entity.Task;
import com.example.handoverapp.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;


@WebMvcTest(value = TaskController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskRepository taskRepository;


    @Test
    public void testGetTaskById() throws Exception {
        Task mockTask = TestHelpers.fakeTask_1();
        String mockTaskJson = TestHelpers.fakeGetJson();
        Mockito.when(taskRepository.findById(Mockito.anyLong()))
                .thenReturn(java.util.Optional.of(mockTask));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tasks/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andDo(document("get_task")).andReturn();
        System.out.println(result.getResponse());
        JSONAssert.assertEquals(mockTaskJson, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testCreateTask() throws Exception {
        Task mockTask = TestHelpers.fakeTask_1();
        Mockito.when(taskRepository.save(Mockito.any())).thenReturn(mockTask);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/tasks")
                .accept(MediaType.APPLICATION_JSON)
                .content(TestHelpers.fakePostJson())
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andDo(document("create_task")).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void testCreateAndThenGetTask() throws Exception {
        Task mockTask = TestHelpers.fakeTask_1();
        Mockito.when(taskRepository.save(Mockito.any())).thenReturn(mockTask);
        Mockito.when(taskRepository.findById(Mockito.anyLong()))
                .thenReturn(java.util.Optional.of(mockTask));
        RequestBuilder requestBuilderPost = MockMvcRequestBuilders
                .post("/api/tasks")
                .accept(MediaType.APPLICATION_JSON)
                .content(TestHelpers.fakePostJson())
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilderPost).andReturn();
        RequestBuilder requestBuilderGet = MockMvcRequestBuilders.get("/api/tasks/1")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilderGet).andReturn();
        JSONAssert.assertEquals(TestHelpers.fakeGetJson(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testUpdateTask() throws Exception {
        Task mockTask = TestHelpers.fakeTask_1();
        Mockito.when(taskRepository.save(Mockito.any())).thenReturn(mockTask);
        Mockito.when(taskRepository.findById(Mockito.anyLong()))
                .thenReturn(java.util.Optional.of(mockTask));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/tasks/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(TestHelpers.fakePostJson())
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andDo(document("update_task")).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }



}
