package fr.sogeti.poc_consul.manager;

import org.springframework.stereotype.Component;

@Component
public class HealthManager {
	private boolean healthy = true;
	
	public void setHealth(boolean health) {
		healthy = health;
	}
	
	public boolean isHealthy() {
		return healthy;
	}
}
