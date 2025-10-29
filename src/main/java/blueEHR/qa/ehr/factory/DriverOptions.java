package blueEHR.qa.ehr.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverOptions {
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	

public DriverOptions(Properties prop) {
	this.prop=prop;
}
	public ChromeOptions chromeOption() {
		co=new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) 
		{co.addArguments("--headless");}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) 
		{co.addArguments("--incognito");}
		return co;
	}
	public FirefoxOptions firefoxOption() {
		fo=new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) 
		{fo.addArguments("--headless");}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) 
		{fo.addArguments("--incognito");}
		return fo;
	}
	public EdgeOptions edgeOption() {
		eo=new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) 
		{eo.addArguments("--headless");}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) 
		{eo.addArguments("--inprivate");}
		return eo;
	}
	
}
