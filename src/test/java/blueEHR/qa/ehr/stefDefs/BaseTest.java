package blueEHR.qa.ehr.stefDefs;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import blueEHR.qa.ehr.factory.DriverFactory;
import blueEHR.qa.ehr.pages.LoginPage;
import blueEHR.qa.ehr.pages.PatientCreationPage;


public class BaseTest {

	protected WebDriver driver;
	protected DriverFactory df;
	protected LoginPage lp;
	protected PatientCreationPage pc;
	protected Properties prop;

//	@Parameters({"browser","environment"})
	protected WebDriver driverSetup(String env, String browserName )
	{	df=new DriverFactory();
		prop = df.initProp(env);
		if(!(browserName.isEmpty())){
		prop.setProperty("browserName", browserName);}
		driver = df.Driverinit(prop);
//		lp=new LoginPage(driver);
		return driver;
	}

}
