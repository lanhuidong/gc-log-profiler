package com.nexusy.glp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lanhuidong
 * @since 2016-12-20
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/", "/index.shtml"})
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

}
