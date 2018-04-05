package fr.sogeti.poc_consul.manager;

import java.net.URL;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.model.health.Service;

import fr.sogeti.poc_consul.provider.ConsulProvider;

@Component
public class ServiceManager {
	
	private AgentClient agentClient;
	@Autowired
	public ServiceManager(ConsulProvider consulProvider) {
		agentClient = consulProvider.getAgentClient();
	}
	
	public void registerService(String name, URL url, int port, int interval, String[] tags) {	
		String id = name + "-" + new Date().getTime();
		
		
		
		if (!isServiceNameRegistered(name)) {
			System.out.println("Registering to consul.");
			agentClient.register(port, url, interval, name, id, tags);
		} else {
			System.out.println("Service already registered");
		}
	}
	
	public void deregisterService(String id) {
		agentClient.deregister(id);
	}
	
	public boolean isServiceRegistered(String id) {
		return agentClient.isRegistered(id);
	}
	
	public boolean isServiceNameRegistered(String name) {
		Map<String, Service> services = agentClient.getServices();
		for(String serviceID: services.keySet()) {
			Service service = services.get(serviceID);
			if(name.equals(service.getService())) {
				return true;
			}
		}
		
		return false;
	}
}
