package blueEHR.qa.ehr.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import blueEHR.qa.ehr.constants.AppConstants;
import blueEHR.qa.ehr.utils.ElementUtil;
import blueEHR.qa.ehr.utils.JSutil;

public class PatientCreationPage {
	private ElementUtil ele;
	private WebDriver driver;
	private JSutil js;
	
	
	public PatientCreationPage(WebDriver driver) {
		this.driver=driver;
		ele=new ElementUtil(this.driver);
		js=new JSutil(this.driver);
	}

	private By pformTitle = By.cssSelector("h3");
private By pType=By.id("patient_type");
private By fName=By.id("fname");
//private By dob=By.id("dob");
private By dob=By.cssSelector("input#dob");
private By langg=By.id("language");
private By gender=By.id("sex");
private By midName=By.id("mname");
private By phone=By.id("mobile_phone");
private By saveBtn=By.linkText("SAVE");
private By email=By.id("email_id");
private By popup=By.id("enc-sm-sendto");
private By popupYes=By.linkText("YES");
private By srchField=By.cssSelector("div.search-box input#patient_search");
private By resultList=By.cssSelector(".pat_search_div li span.name-text");
private By frame =By.xpath("//iframe[@id='form_data-2']");
private By newPatientBtn=By.cssSelector("button.add-patient-btn.partial-new-hp");
private By year=By.cssSelector("div#ui-datepicker-div select[aria-label=\"Select year\"]");
private By yearList=By.cssSelector("div#ui-datepicker-div select[aria-label=\"Select year\"]>option");
private By month=By.cssSelector("div#ui-datepicker-div select[aria-label=\"Select month\"]");
private By monthList=By.cssSelector("div#ui-datepicker-div select[aria-label=\"Select month\"]>option");
private By dateList =By.cssSelector("div#ui-datepicker-div tbody td>a");
	private By pName = By.xpath("(//input[@id=\"is_demographics\"]/following-sibling::div/center/span)[1]");
	private By fullpage = By.cssSelector(".dm-dm-dm-contents");
	private By demogFrame = By.xpath("//iframe[contains(@src,\"reload\")]");
	
	public void createPatient(String pType, String fName, String mName, String gender, String dobValue,
			String langg, String email, String Phone,int waitTime) {
		
		ele.switchToiframeByElement(frame, waitTime);
		ele.waitforVisibleofEle(pformTitle, waitTime);
		ele.doSelectByVisibleWithWait(this.pType, pType,waitTime);
		ele.sendTextwithWait(this.fName,fName,waitTime);
		ele.sendText(midName, mName);
		ele.sendText(this.gender, gender);
		ele.doSelectByVisible(this.langg, langg);
//		ele.actionClick(dob);
		ele.clearField(dob);
		ele.selectDate(dob, year, yearList, month, monthList, dateList, dobValue,waitTime);
		ele.sendText(this.email, email);
		ele.sendText(this.phone, Phone);
		ele.clickElement(saveBtn);
		ele.patientCreatePopuphandle(popup,popupYes,waitTime);
		System.out.println("patient saved successfully");
//		driver.switchTo().defaultContent();
	}
	public String getPatientName(int waitTime) {
		String patientName = ele.getTextVisibleWait(pName, waitTime);
		System.out.println("The new patient created "+patientName);
		driver.switchTo().defaultContent();
		return patientName;
	}
	public void navigateToPatientCreation(int timeout) {
		ele.actionDoubleClickElement(newPatientBtn);
	}
	


}

