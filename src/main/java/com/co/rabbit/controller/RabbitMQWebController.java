package com.co.rabbit.controller;

import com.co.rabbit.model.Employee;
import com.co.rabbit.service.RabbitMQSender;
import com.co.rabbit.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/sendmq")
public class RabbitMQWebController {

    private TestService testService;
    public final RabbitMQSender rabbitMQSender;
    public RabbitMQWebController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("empName") String empName,@RequestParam("empId") String empId) {

        Employee emp=new Employee();
        emp.setEmpId(empId);
        emp.setEmpName(empName);
        rabbitMQSender.send(emp);
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }
}
