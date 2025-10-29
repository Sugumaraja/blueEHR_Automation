package blueEHR.qa.ehr.stefDefs;

import blueEHR.qa.ehr.constants.AppConstants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class PatientCreateStepDef {
    private BaseTest context;
    public PatientCreateStepDef(BaseTest context){
        this.context=context;
    }
    @When("While click on the New Patient Icon")
    public void whileClickOnTheNewPatientIcon() {
        System.out.println("inside the patient create");
      context.pc= context.lp.navigateToPatientCreation(AppConstants.MED_WAIT);
    }

    @And("Enter the patient details {},{},{},{},{},{},{},{}")
    public void enterThePatientDetails(String pType, String fName, String mName, String gender, String DOB, String langg,
                                       String email, String Phone) {
    context.pc.createPatient(pType, fName, mName, gender, DOB, langg, email, Phone, AppConstants.MED_WAIT);
    }

    @Then("The demographic page should have patient {} & {} in Demographics with wait {int} sec")
    public void theDemographicPageShouldHavePatientInDemographicsWithWaitSec(String fName, String mName, int waitTime) {
        System.out.println(fName+" "+mName);
        String patientNameFromDemographic =context.pc.getPatientName(waitTime);
        context.pc.navigateToPatientCreation(waitTime);
        String expected=(fName+" "+mName).toUpperCase();
        Assert.assertEquals(patientNameFromDemographic,expected);}
}
