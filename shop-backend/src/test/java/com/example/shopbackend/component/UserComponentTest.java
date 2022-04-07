package com.example.shopbackend.component;

import com.example.shopbackend.model.User;
import com.example.shopbackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.example.shopbackend.mocks.UserMock.getMockUserAdmin;
import static com.example.shopbackend.mocks.UserMock.getMockUserBasic;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class UserComponentTest {

    public static final String BASE_PATH = "/";

    public static final String USERS = "users";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    @Timeout(unit = TimeUnit.MILLISECONDS, value = 1000)
    void test_getAllUsers_withSuccess() throws Exception {
        final User user = getMockUserAdmin();
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(getMockUserAdmin()));

        final MvcResult mvcResult = this.mockMvc.perform(
                        get(BASE_PATH + USERS)
                                .servletPath(BASE_PATH + USERS)
                                .with(httpBasic(user.getUsername(), user.getPassword())))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(mvcResult);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }


    @Test
    @Timeout(unit = TimeUnit.MILLISECONDS, value = 1000)
    void test_getAllUsers_withFailure() throws Exception {
        final User user = getMockUserBasic();
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(getMockUserBasic()));

        final MvcResult mvcResult = this.mockMvc.perform(
                        get(BASE_PATH + USERS)
                                .servletPath(BASE_PATH + USERS)
                                .with(httpBasic(user.getUsername(), user.getPassword())))
                .andExpect(status().isForbidden())
                .andReturn();

        assertNotNull(mvcResult);
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

}
