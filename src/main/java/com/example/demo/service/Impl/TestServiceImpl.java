package com.example.demo.service.Impl;

import com.example.demo.service.TestService;
import org.springframework.stereotype.Service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
/**
 * @ClassName TestServiceImpl
 * @Description
 * @Author ykw
 * @Date 2021/6/28 15:39
 */
@Service("testService")
@Path("/testService")
public class TestServiceImpl implements TestService {

    @Override
    @Path("/get123")
    @POST
    public String get123() {
        return "123";
    }
}
