package com.example.sbnzproject.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

@Configuration
public class DroolsConfig {

    private final KieServices kieServices = KieServices.Factory.get();

    @Bean
    public KieContainer kieContainer() throws IOException {
        KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        Resource[] resources = getRuleFiles();
        for (Resource resource : resources) {
            kieFileSystem.write("src/main/resources/" + resource.getFilename(), kieServices.getResources().newInputStreamResource(resource.getInputStream()));
        }
        kieServices.newKieBuilder(kieFileSystem).buildAll();
        KieModule kieModule = kieRepository.getKieModule(kieRepository.getDefaultReleaseId());
        return kieServices.newKieContainer(kieModule.getReleaseId());
    }

    @Bean
    public KieSession kieSession(KieContainer kieContainer) {
        return kieContainer.newKieSession();
    }

    private Resource[] getRuleFiles() throws IOException {
        return new PathMatchingResourcePatternResolver().getResources("classpath*:com/example/rules/*.drl");
    }
}