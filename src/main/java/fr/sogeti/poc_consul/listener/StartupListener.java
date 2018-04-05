package fr.sogeti.poc_consul.listener;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import fr.sogeti.poc_consul.manager.ServiceManager;

public class StartupListener implements SpringApplicationRunListener {
	
	public StartupListener(SpringApplication application, String[] args) { }
	
	@Override
	public void contextLoaded(ConfigurableApplicationContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextPrepared(ConfigurableApplicationContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void environmentPrepared(ConfigurableEnvironment environment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void failed(ConfigurableApplicationContext context, Throwable exception) {
		// TODO Auto-generated method stub

	}

	@Override
	public void running(ConfigurableApplicationContext context) {
		// Once the app is started, register to consul
		URL url;
		try {
			url = new URL("http://localhost:8080/health");
			ServiceManager sm = context.getBean(ServiceManager.class);
			sm.registerService("webservice-java", url, 8080, 10, new String[] {"java", "spring"});
			// sm.deregisterService("webservice-java-1522936302646");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void started(ConfigurableApplicationContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void starting() {
		// TODO Auto-generated method stub

	}

}
