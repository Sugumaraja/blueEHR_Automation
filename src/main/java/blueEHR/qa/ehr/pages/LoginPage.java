package blueEHR.qa.ehr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import blueEHR.qa.ehr.constants.AppConstants;
import blueEHR.qa.ehr.utils.ElementUtil;
import blueEHR.qa.ehr.utils.JSutil;

public class LoginPage {
private WebDriver driver;
private ElementUtil ele;
private JSutil js;
//private String parent;

private By facility=By.id("facility");
private By userName=By.id("username");
private By password=By.id("clearpassword");
private By loginButton=By.cssSelector("button.btn-login");
private By lpUserName=By.cssSelector(".user-details");
private By newPatientBtn=By.cssSelector("div.add-patient-wrapper>button.add-patient-btn.partial-new-hp");
private boolean newWindowStatus;
private By errorMsgField=By.cssSelector("span.invalid-msg");
private	String server;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		ele=new ElementUtil(this.driver);
		js=new JSutil(driver);
	}
	public void enterCredentials(String facilityValue,String userNameValue, String pwdValue,int waitTime){
		server=ele.getCurrentURL();
		ele.sendTextwithWait(facility,facilityValue,waitTime);
		ele.sendText(userName,userNameValue);
		ele.sendText(password,pwdValue);
	}
	public void clickLoginBtn(){
		ele.clickElement(loginButton);
		ele.sessionSwitch();
		ele.waitForDashboardPageLoad(server);
	}
	public String getUserDetail(){
		String userNamevalue=ele.getTextWait(lpUserName, AppConstants.MED_WAIT).toLowerCase();
			System.out.println(userNamevalue);
		return userNamevalue;
	}

	public String getErrorMsg(){
				return ele.getTextWait(errorMsgField, AppConstants.MED_WAIT).toLowerCase();
		}
 public PatientCreationPage navigateToPatientCreation(int waitTime) {
		getUserDetail();
	 ele.clickWithPresenceWithStaticWait(newPatientBtn,waitTime);
//	 ele.actionClickElement(newPatientBtn);
	 return new PatientCreationPage(driver);
 }
}
