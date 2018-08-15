package com.ott.installation.controller;

import com.ott.installation.domain.EnviromentStatus;
import com.ott.installation.domain.HostEnviroment;
import com.ott.installation.service.ConfigService;
import com.ott.installation.service.HostEnvService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * HomeController
 */
@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private final ConfigService configService;
    private final HostEnvService hostEnvService;

    @Autowired
    public HomeController(ConfigService configService, HostEnvService hostEnvService) {
        this.configService = configService;
        this.hostEnvService = hostEnvService;
    }

    @RequestMapping("/")
    public String index() {
        LOGGER.info("index action");
        return "ott_install.html";
    }

    @RequestMapping("/env_check")
    public ModelAndView enviromentCheck(ModelAndView modelAndView) {
        LOGGER.info("env check action");
        HostEnviroment hostEnviroment = hostEnvService.getHostProperties();
        EnviromentStatus enviromentStatus = hostEnvService.checkEnviroment(hostEnviroment);
        modelAndView.addObject(hostEnviroment);
        modelAndView.addObject(enviromentStatus);
        modelAndView.setViewName("env_check");
        return modelAndView;
    }
}