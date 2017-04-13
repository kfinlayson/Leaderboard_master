package edu.jsu.mcis.gamegogy.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GamegogyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void paramStudentShouldReturnStudentInformation() throws Exception {

        this.mockMvc.perform(get("/gamegogy/student/111124"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("swilkins"));
    }

    @Test
    public void paramCourseShouldReturnCourseInformation() throws Exception {

        this.mockMvc.perform(get("/gamegogy/course/99018"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(16));
    }

}
