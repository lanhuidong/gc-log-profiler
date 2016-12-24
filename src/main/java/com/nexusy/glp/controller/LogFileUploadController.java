package com.nexusy.glp.controller;

import com.nexusy.glp.analyzer.DefaultGCAnalyzer;
import com.nexusy.glp.data.advanced.HighLevelData;
import com.nexusy.glp.data.basic.BasicData;
import com.nexusy.glp.parser.GCLogParser;
import com.nexusy.glp.parser.impl.SimpleLogParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Part;
import java.io.IOException;

/**
 * @author lanhuidong
 * @since 2016-12-22
 */
@RestController
public class LogFileUploadController {

    private GCLogParser parser = new SimpleLogParser();

    private DefaultGCAnalyzer analyzer = new DefaultGCAnalyzer();

    @PostMapping("/form")
    public HighLevelData handleFormUpload(@RequestParam("file") Part file) {
        System.out.println(file.getSize());
        HighLevelData highLevelData = null;
        try {
            BasicData basicData = parser.parse(file.getInputStream());
            highLevelData = analyzer.analyze(basicData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highLevelData;
    }
}
