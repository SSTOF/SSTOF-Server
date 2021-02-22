package com.sstof;

import com.sstof.users.domain.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class ApiIntegrationTest {
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected TestRestTemplate restTemplate;

    protected static final String EMAIL = "test@test.com";
    protected static final String NAME = "NAME";
    protected static final String PASSWORD = "PasswordPasswordPasswordPasswordPasswordPasswordPasswordPassword";

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }
}
