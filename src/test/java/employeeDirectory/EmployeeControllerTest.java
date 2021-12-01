package employeeDirectory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
public class EmployeeControllerTest {

    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(new EmployeeController(employeeService)).build();
    }

    @Test
    public void testGetEmployee() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/employees")
                .queryParam("lowRange", "45000")
                .queryParam("highRange", "225000")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testCreateEmployee() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/employees")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .put("/employees/1")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        Assertions.assertEquals(200, result.getResponse().getStatus());
    }

}
