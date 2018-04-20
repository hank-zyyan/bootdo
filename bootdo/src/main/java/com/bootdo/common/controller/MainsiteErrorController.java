package com.bootdo.common.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MainsiteErrorController implements ErrorController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String ERROR_PATH = "/error";

    @RequestMapping(
            value = {ERROR_PATH},
            produces = {MediaType.TEXT_HTML_VALUE}
    )
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        int code = response.getStatus();
        if (404 == code) {
            return new ModelAndView("error/404");
        } else if (403 == code) {
            return new ModelAndView("error/403");
        } else if (401 == code) {
            return new ModelAndView("login");
        } else {
            return new ModelAndView("error/500");
        }
    }

    @RequestMapping(
            value = {ERROR_PATH},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public String errorJson(HttpServletRequest request, HttpServletResponse response) {
        int code = response.getStatus();
        if (404 == code) {
            return "error/404";
        } else if (403 == code) {
            return "error/403";
        } else if (401 == code) {
            return "error/401";
        } else {
            return "error/500";
        }
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}