package fr.sogeti.poc_consul.provider;

import java.util.List;
import java.util.Map;

import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Component;

import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import com.orbitz.consul.model.kv.Value;

@Component
public class ConfigurationProvider {
	private Consul client;
	private KeyValueClient kvClient;
	
	public ConfigurationProvider() {
		this.client = Consul.builder().build();
		this.kvClient = this.client.keyValueClient();
	}
	
	public String getJSONProperties(String key) {
		List<Value> values = kvClient.getValues(key);
		StringBuilder sbValues = new StringBuilder();
		for(Value value : values) {
			sbValues.append(value.getValueAsString().get() + ", ");
		}
		return sbValues.toString();
	}
	
	public Map<String, Object> getProperties(String key) {
		String jsonProperties = getJSONProperties(key);
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		
		return jsonParser.parseMap(jsonProperties);
	}
}
