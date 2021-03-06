package com.emc.ecs.serviceBroker.config;

import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.servicebroker.model.BrokerApiVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.emc.ecs.managementClient.Connection;
import com.emc.ecs.serviceBroker.EcsManagementClientException;
import com.emc.ecs.serviceBroker.EcsManagementResourceNotFoundException;
import com.emc.ecs.serviceBroker.EcsService;
import com.emc.ecs.serviceBroker.repository.ServiceInstanceBindingRepository;
import com.emc.ecs.serviceBroker.repository.ServiceInstanceRepository;
import com.emc.ecs.serviceBroker.service.EcsServiceInstanceBindingService;
import com.emc.ecs.serviceBroker.service.EcsServiceInstanceService;

@EnableAutoConfiguration
@ComponentScan
public class Application {
	
	@Autowired
	private BrokerConfig broker;

	public Application() {
		super();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Connection ecsConnection() {
		URL certificate = getClass().getClassLoader()
				.getResource(broker.getCertificate());
		return new Connection(broker.getManagementEndpoint(),
				broker.getUsername(), broker.getPassword(), certificate);
	}
	
	@Bean
	public BrokerApiVersion brokerApiVersion() {
		return new BrokerApiVersion(broker.getBrokerApiVersion());
	}
	
	@Bean
	public EcsService ecsService() throws EcsManagementClientException,
			EcsManagementResourceNotFoundException {
		return new EcsService();
	}
	
	@Bean
	public EcsServiceInstanceBindingService ecsServiceInstanceBindingService()
			throws EcsManagementClientException,
			EcsManagementResourceNotFoundException, URISyntaxException {
		return new EcsServiceInstanceBindingService();
	}
	
	@Bean
	public ServiceInstanceRepository serviceInstanceRepository()
			throws EcsManagementClientException,
			EcsManagementResourceNotFoundException, URISyntaxException {
		return new ServiceInstanceRepository();
	}

	@Bean
	public EcsServiceInstanceService ecsServiceInstanceService()
			throws EcsManagementClientException,
			EcsManagementResourceNotFoundException, URISyntaxException {
		return new EcsServiceInstanceService();
	}
	
	@Bean
	public ServiceInstanceBindingRepository serviceInstanceBindingRepository()
			throws EcsManagementClientException,
			EcsManagementResourceNotFoundException, URISyntaxException {
		return new ServiceInstanceBindingRepository();
	}
	
	public BrokerConfig getBroker() {
		return broker;
	}

	public void setBroker(BrokerConfig broker) {
		this.broker = broker;
	}
   	
}