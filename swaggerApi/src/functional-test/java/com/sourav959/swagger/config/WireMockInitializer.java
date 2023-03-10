package com.sourav959.swagger.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.SingleRootFileSource;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

/**
 * This class is configuration for wiremock for functional tests.
 */
public class WireMockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration()
                .fileSource(new SingleRootFileSource("src/main/resources")).dynamicPort());
        wireMockServer.start();
        applicationContext.getBeanFactory()
                .registerSingleton("wireMockServer", wireMockServer);
        applicationContext.addApplicationListener(applicationEvent -> {
            if (applicationEvent instanceof ContextClosedEvent) {
                wireMockServer.stop();
            }
        });
        TestPropertyValues.of("wiremock.port = " + wireMockServer.port())
                .applyTo(applicationContext);
    }

}

