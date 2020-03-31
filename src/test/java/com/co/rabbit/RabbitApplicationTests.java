package com.co.rabbit;

import com.co.rabbit.controller.RabbitMQWebController;
import com.co.rabbit.model.Employee;
import com.co.rabbit.service.RabbitMQSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
//@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @WebMvcTest(RabbitMQWebController.class)
class RabbitApplicationTests {


    @InjectMocks
    RabbitMQWebController rabbitMQWebController;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RabbitMQSender rabbitMQSender;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProducer() throws Exception {
        String empId="id1";
        String empName="hossein";
       MultiValueMap<String,String> paramMap=new LinkedMultiValueMap<>();

        paramMap.add("empName",empName);
        paramMap.add("empId",empId);
       // rabbitMQWebController.producer(empName,empId);
        when(rabbitMQSender.send(any(Employee.class))).thenReturn(true);
mockMvc.perform(get("/sendmq/producer").params(paramMap)).andExpect(status().isOk()).andReturn();
    }
}
