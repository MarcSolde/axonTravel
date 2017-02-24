package com.example;

import com.example.coreapi.CreateCustomerCommand;
import com.example.coreapi.CreateOrderCommand;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.axonframework.spring.config.EnableAxon;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

//@EnableAxon
@SpringBootApplication
public class AxonTravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxonTravelApplication.class, args);
	//	ConfigurableApplicationContext config = SpringApplication.run(AxonTravelApplication.class, args);
	//	CommandGateway commandGateway =  config.getBean(CommandGateway.class);

		//commandGateway.send(new CreateCustomerCommand("1234", 1000), LoggingCallback.INSTANCE);
		//commandGateway.send(new CreateOrderCommand("1111","1234", 100), LoggingCallback.INSTANCE);

	}

	//TODO: Clean the goddamn code

	/*
	@Autowired
	public void configure(EventHandlingConfiguration config) {
		config.registerTrackingProcessor("CustomerBalance");
	}

	@Bean
	public EventStorageEngine eventStorageEngine() {
		return new InMemoryEventStorageEngine();
	}


	@Bean
	public SpringTransactionManager springTransactionManager(PlatformTransactionManager platformTransactionManager) {
		return new SpringTransactionManager(platformTransactionManager);
	}
    @Bean
    public EntityManagerProvider entityManagerProvider() {
       return new ContainerManagedEntityManagerProvider();
    }

    @Bean
    public Serializer serializer() {
       return new XStreamSerializer();
	}
*/
}
