package com.doan.limitsmicroservice.limitscontroller;

import com.doan.limitsmicroservice.bean.Limits;
import com.doan.limitsmicroservice.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired // wire to configuration class
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits retrieveLimits(){
        return new Limits(configuration.getMinimum(),configuration.getMaximum());
    }

}
