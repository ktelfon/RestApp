package lv.home.task.http;

import lv.home.task.dto.UserDto;
import lv.home.task.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void shouldReturnHeaderWithLocation() throws Exception {
        int id = 999;
        UserDto userDto = UserDto.builder()
                .id(id)
                .email("@DD").build();
        when(userService.save(any())).thenReturn(userDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .content("{\"email\":\"@DD\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString(String.valueOf(id))));

    }

    @Test
    public void shouldReturnUserDtoById() throws Exception {
        int id = 999;
        UserDto userDto = UserDto.builder()
                .id(id)
                .email("@DD").build();
        when(userService.getById(any())).thenReturn(userDto);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(String.valueOf(id))));

    }

}
