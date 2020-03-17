package com.dashboard.controller;

import com.dashboard.aop.CONS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController

@RequestMapping("/welcome")
public class BackEndController {
     final Logger logger = (Logger) LoggerFactory.getLogger(BackEndController.class);
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @CONS
    public String info() {
        return ("**** __Response From this Service__ ***");

        //return "welcome " + String.valueOf(ApplicationInfoManager.getInstance().getInfo().getPort()) + "  has responded";
    }


}
