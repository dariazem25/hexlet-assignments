package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;


@SpringBootTest
@AutoConfigureMockMvc

// BEGIN
@Testcontainers
@Transactional
// END
public class AppTest {

    @Autowired
    private MockMvc mockMvc;

    // BEGIN
    @Container
    private static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("dbname")
            .withUsername("sa")
            .withPassword("sa")
            .withInitScript("init.sql");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
    }
    // END

    @Test
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
            .perform(
                post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }

    @Test
    void testGetAllPeople() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(
                        get("/people"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);

        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("John", "Smith");
        assertThat(response.getContentAsString()).contains("Jack", "Doe");
        assertThat(response.getContentAsString()).contains("Jassica", "Simpson");
        assertThat(response.getContentAsString()).contains("Robert", "Lock");
    }

    @Test
    void testGetPerson() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(
                        get("/people/1"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isNull();

        assertThat(response.getContentAsString()).contains("John", "Smith");
        assertThat(response.getContentAsString()).doesNotContain("Jack", "Doe");
        assertThat(response.getContentAsString()).doesNotContain("Jassica", "Simpson");
        assertThat(response.getContentAsString()).doesNotContain("Robert", "Lock");
    }

    @Test
    void testDeletePerson() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(
                        delete("/people/1"))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);

        assertThat(response.getContentType()).isNull();

        MockHttpServletResponse response2 = mockMvc
                .perform(get("/people"))
                .andReturn()
                .getResponse();

        assertThat(response2.getStatus()).isEqualTo(200);
        assertThat(response2.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response2.getContentAsString()).doesNotContain("John", "Smith");
        assertThat(response2.getContentAsString()).contains("Jack", "Doe");
        assertThat(response2.getContentAsString()).contains("Jassica", "Simpson");
        assertThat(response2.getContentAsString()).contains("Robert", "Lock");
    }

    @Test
    void testUpdatePerson() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(
                        patch("/people/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");

        MockHttpServletResponse response2 = mockMvc
                .perform(get("/people"))
                .andReturn()
                .getResponse();

        assertThat(response2.getStatus()).isEqualTo(200);
        assertThat(response2.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response2.getContentAsString()).doesNotContain("John", "Smith");
        assertThat(response2.getContentAsString()).contains("Jackson", "Bind");
        assertThat(response2.getContentAsString()).contains("Jack", "Doe");
        assertThat(response2.getContentAsString()).contains("Jassica", "Simpson");
        assertThat(response2.getContentAsString()).contains("Robert", "Lock");

    }
}
