 package com.project.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.ApplicationListener;

@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication implements ApplicationListener<EurekaInstanceRegisteredEvent> {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}
	
	@Override
    public void onApplicationEvent(EurekaInstanceRegisteredEvent event) {
		System.out.println("applikacija se kaci..: ime -> " + event.getInstanceInfo().getAppName());
		System.out.println("applikacija se kaci..: GRUPA -> " + event.getInstanceInfo().getAppGroupName());
 
    }

}
