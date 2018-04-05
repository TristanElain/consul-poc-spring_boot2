package fr.sogeti.poc_consul.provider;

import org.springframework.stereotype.Component;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;

@Component
public class ConsulProvider {
	private Consul client;
	
	public ConsulProvider() {
		this.client = Consul.builder().build();
	}

	public KeyValueClient getKVClient() {
		return client.keyValueClient();
	}
	
	public AgentClient getAgentClient() {
		return client.agentClient();
	}
}
