package com.nexusy.glp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Part;

/**
 * @author lanhuidong
 * @since 2016-12-22
 */
@Controller
public class LogFileUploadController {

    @PostMapping("/form")
    public ModelAndView handleFormUpload(@RequestParam("file") Part file) {

        System.out.println(file.getSize());

        return new ModelAndView("/index");
    }
}
