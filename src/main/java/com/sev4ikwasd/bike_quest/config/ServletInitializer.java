package com.sev4ikwasd.bike_quest.config;

import com.sev4ikwasd.bike_quest.BikeQuestApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BikeQuestApplication.class);
    }

}
