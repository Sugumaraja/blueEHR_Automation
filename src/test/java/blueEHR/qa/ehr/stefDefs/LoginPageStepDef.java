package blueEHR.qa.ehr.stefDefs;

import blueEHR.qa.ehr.constants.AppConstants;
import blueEHR.qa.ehr.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginPageStepDef  {
//    private BaseTest bt=new BaseTest();
    private BaseTest context;
    public LoginPageStepDef(BaseTest context){
        this.context=context;
    }
    @Given("I am Launching the Login page in {string} env with {string} browser")
    public void iAmLaunchingTheLoginPageInEnvWithBrowser(String env, String browser) {
        context.driver= context.driverSetup(env,browser);
        context.lp=new LoginPage(context.driver);
    }
    @Given("I have entered valid facility and username and password from propertyFile")
    public void iHaveEnteredValidFacilityAndUsernameAndPasswordFromPropertyFile() {
        String facility =context.prop.getProperty("facility");
        String userName =context.prop.getProperty("userName");
        String password =context.prop.getProperty("password");
        context.lp.enterCredentials(facility,userName,password,AppConstants.MED_WAIT);
    }
    @Given("I have entered the {} and {} and {}")
    public void iHaveEnteredTheAndAnd(String facilityValue, String userName, String pwdValue) {
        context.lp.enterCredentials(facilityValue,userName,pwdValue,AppConstants.MED_WAIT);
        System.out.println("I have entered Invalid facility and username and password");
    }
    @When("While click on the login button")
    public void while_click_on_the_login_button() {
        context.lp.clickLoginBtn();
    }
    @Then("The user should get logged in succesfully")
    public void The_user_should_get_logged_in_succesfully() {
        System.out.println("Verifying user name");
        String value =  context.lp.getUserDetail();
        Assert.assertEquals(value, "zh administrator");
    }

    @Then("The app should contain {}")
    public void theAppShouldContain(String message) {
        System.out.println("Verifying error message");
        String error = context.lp.getErrorMsg();
        Assert.assertEquals(error, message);
    }

    @Then("close the browser")
    public void closeTheBrowser() {context.driver.quit();
    }


}
