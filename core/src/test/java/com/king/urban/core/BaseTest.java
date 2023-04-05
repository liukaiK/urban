package com.king.urban.core;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailsServiceImpl")
public class BaseTest {
}
