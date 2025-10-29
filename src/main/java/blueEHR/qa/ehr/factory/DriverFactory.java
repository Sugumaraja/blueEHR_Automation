package blueEHR.qa.ehr.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.StackWalker.Option;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.beust.jcommander.Parameter;

import blueEHR.qa.ehr.appexception.AppException;

public class DriverFactory {
	private WebDriver driver;
	private Properties prop;
	private DriverOptions op;
	public static ThreadLocal<WebDriver> tld = new ThreadLocal<>();

	public WebDriver Driverinit(Properties prop) {
		op = new DriverOptions(prop);
		String browserName = prop.getProperty("browserName");
		String incognito = ("incognito " + prop.getProperty("incognito"));
		String headless = ("headless " + prop.getProperty("headless"));
		System.out.println("The current running browser config is " + browserName + " " + incognito + " " + headless);
		switch (browserName.toLowerCase()) {
		case "chrome": {
//			driver = new ChromeDriver(op.chromeOption());
			tld.set(new ChromeDriver(op.chromeOption()));
			break;
		}
		case "edge": {
//			driver = new EdgeDriver(op.edgeOption());
			tld.set(new EdgeDriver(op.edgeOption()));
			break;
		}
		case "firefox": {
//			driver = new FirefoxDriver(op.firefoxOption());?
			tld.set(new FirefoxDriver(op.firefoxOption()));
			break;
		}
		case "safari": {
//			driver = new SafariDriver();
			tld.set(new SafariDriver());
			break;
		}
		default:
			throw new AppException("Please pass the right browser " + browserName);
		}
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.get(prop.getProperty("server"));

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("server"));
		System.out.println(prop.getProperty("server"));
		return getDriver();
	}
	public static WebDriver getDriver() {
		return tld.get();
	}

	public Properties initProp(String envName) {
		prop = new Properties();
		FileInputStream ip = null;
//		String envName = System.getProperty("env");
		System.out.println("The current envionment passed " + envName);
		if (envName == null) {
			System.out.println("No configuration mentioned running in Qa config");
			try {
				ip = new FileInputStream("./src/test/resources/configuration/config.properties");
				try {
					prop.load(ip);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return prop;
		} else {
			switch (envName) {
			case "qa": {
				try {
					ip = new FileInputStream("./src/test/resources/configuration/config.properties");
					try {
						prop.load(ip);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				return prop;
			}
			case "dev": {
				try {
					ip = new FileInputStream("./src/test/resources/configuration/dev_config.properties");
					try {
						prop.load(ip);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				return prop;
			}
			case "stage": {
				try {
					ip = new FileInputStream("./src/test/resources/configuration/stage_config.properties");
					try {
						prop.load(ip);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				return prop;
			}

			default:
				throw new AppException("Please pass right env value: " + envName);
			}
		}
	}

	public static String getScreenshot(String methodName) {
			File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
			String path=System.getProperty("user.dir")+"/screenshot/"+methodName+"_"+System.currentTimeMillis()+".png";
			File destination=new File(path);
			try {
				FileHandler.copy(srcFile, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return path;
	}

}
