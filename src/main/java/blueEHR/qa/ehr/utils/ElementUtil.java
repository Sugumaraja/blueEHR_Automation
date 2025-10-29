package blueEHR.qa.ehr.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import blueEHR.qa.ehr.constants.AppConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import blueEHR.qa.ehr.appexception.AppException;

public class ElementUtil {
	private JSutil js;
	private WebDriver driver;
	private WebDriverWait wait;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		js = new JSutil(this.driver);
	}

	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		js.flash(ele);
		return ele;

	}

	public void doWindowMaximize() {
		driver.manage().window().maximize();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void clickElement(By locator) {
		getElement(locator).click();
	}

	public void actionClickElement(By locator) {
		WebElement target = getElement(locator);
		Actions act = new Actions(driver);
		act.scrollToElement(target).click().build().perform();
	}
	public void actionSctollToElement(By locator) {
		WebElement target = getElement(locator);
		Actions act = new Actions(driver);
		act.scrollByAmount(1366, 597).build().perform();
	}

	public void actionDoubleClickElement(By locator) {
		WebElement target = getElement(locator);
		Actions act = new Actions(driver);
		act.moveToElement(target).doubleClick(target).build().perform();
	}
	public void actionClick(By locator) {
		WebElement target = getElement(locator);
		Actions act = new Actions(driver);
		act.click(target).perform();
	}

	public WebDriverWait waitMethod(int time) {
		return new WebDriverWait(driver, Duration.ofSeconds(time));
	}
	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public void sendText(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void dropCount(By locator) {
		int listSize = getElements(locator).size();
		System.out.println(listSize);
	}

	public void getDropListText(By locator) {
		List<WebElement> drpEleList = getElements(locator);
		dropCount(locator);
		for (WebElement e : drpEleList) {
			String drpListText = e.getText();
			System.out.println(drpListText);
		}
	}

	public List<String> getDropListTextWithWait(By locator, int time) {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<WebElement> drpEleList;
		try {
			drpEleList = waitforPresenceofElements(locator, time);
		} catch (StaleElementReferenceException e) {
			drpEleList = waitforPresenceofElements(locator, time);
			e.printStackTrace();
		}
		
		List<String> text = new ArrayList<>();
		for (WebElement e : drpEleList) {
			String drpListText = e.getText();
			text.add(drpListText);
		}
		
		return text;
	}

	public boolean checkElePresent(By locator) {

		return driver.findElements(locator).size() > 0 ? true : false;
	}

	public boolean checkMultiElePresent(By locator) {

		return driver.findElements(locator).size() >= 1 ? true : false;
	}

	public boolean checkOneElePresent(By locator) {
		return getElements(locator).size() == 1 ? true : false;

	}

	public boolean elementPresence() {

		return true;

	}

	public Select selectClass(By locator) {
		Select sel = new Select(getElement(locator));
		return sel;

	}

	public Select selectClass(By locator, int time) {
		Select sel = new Select(waitforVisibleofEle(locator, time));
		return sel;

	}

	public void doSelectByIndex(By locator, int index) {

//	WebElement eleDropdwn = getElement(locator);
//	Select listDrp = new Select(eleDropdwn);
		selectClass(locator).selectByIndex(index);
	}

	public void doSelectByVisible(By locator, String value) {

//	WebElement eleDropdwn = getElement(locator);
//	Select listDrp = new Select(eleDropdwn);
		selectClass(locator).selectByVisibleText(value);
	}

	public void doSelectByVisibleWithWait(By locator, String value, int time) {

//		WebElement eleDropdwn = getElement(locator);
//		Select listDrp = new Select(eleDropdwn);
		selectClass(locator, time).selectByVisibleText(value);
	}

	public void doSelectByValue(By locator, String value) {
//	WebElement eleDropdwn = getElement(locator);
//	Select listDrp = new Select(eleDropdwn);
		selectClass(locator).selectByValue(value);
	}

	public int getListCount(By locator) {
//	Select listDrp = selectClass(locator);
//	Select listDrp=new Select(getElement(locator));
		return selectClass(locator).getOptions().size();

	}

	public void getListAndSelectOne(By locator, String value) {
		Select listDrp = new Select(getElement(locator));
		List<WebElement> allDrp = listDrp.getOptions();
		System.out.println(allDrp.size());
		for (WebElement e : allDrp) {
			String drpList = e.getText().toString();
			System.out.println(drpList);
			if (drpList.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void drpSelectXpath(By locator, String value) {
		List<WebElement> eleDrp = driver.findElements(locator);
		for (WebElement e : eleDrp) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}

		}
	}

	public void drpSelectByAction(By locator, String value) {
		List<WebElement> eleDrp = getElements(locator);
		System.out.println("****************" + eleDrp.size() + "****************");
		for (WebElement e : eleDrp) {
			String text = e.getText();
			System.out.println(text);
			Actions act = new Actions(driver);
			if (text.equals(value)) {
				act.moveToElement(e).click().perform();
				break;
			}
		}
	}

	public void drpSelectByXpathWithWait(By locator, String value, int timeOut) {
		List<WebElement> eleDrp = waitforPresenceofElements(locator, timeOut);
		System.out.println("****************" + eleDrp.size() + "****************");
		for (WebElement e : eleDrp) {
			String text = e.getText();
			System.out.println(text);
			Actions act = new Actions(driver);
			if (text.equals(value)) {
				act.moveToElement(e).click().perform();
//			break;
			}
		}
	}

	public boolean checkMultiple(By locator) {
//	Select sel = new Select(getElement(locator));
		return selectClass(locator).isMultiple() ? true : false;
	}

	/**
	 * it is to handle the multi selection with single multi and all selection for
	 * all selection pass "all"
	 * 
	 * @param locator
	 * @param values
	 */
	public void doMultiselect(By locator, String... values) {
//  String... values are the String dynamic array another format we can add or remove any value of string with this keyword.
		Select sel = new Select(getElement(locator));
		if (checkMultiple(locator)) {
			for (String value : values) {
				if (values[0].equalsIgnoreCase("all")) {
					List<WebElement> list = sel.getOptions();
					for (WebElement e : list) {
						String listText = e.getText();
						sel.selectByVisibleText(listText);
					}
				} else {
					selectClass(locator).selectByVisibleText(value);
				}
			}
		}
	}

//we can handle single dropdown selection and some limited the drop data we can pass and handle but select all by passing
//we cant pass all string with comma seperated is not possible.
//to check all
	public void doMultiselect(By locator, By optionLocator, String... values) {
		// String... values are the String dynamic array another format we can add or
		// remove any value of string with this keyword.
//		Select sel = new Select(getElement(locator));
		if (checkMultiple(locator)) {
			if (values[0].equalsIgnoreCase("all")) {
				List<WebElement> list = getElements(optionLocator);
				for (WebElement e : list) {
					e.click();
				}
			} else {
				for (String value : values) {
					selectClass(locator).selectByVisibleText(value);
				}
			}
		}
	}

	public WebElement retryElement(By locator, int retry, int intervalTime) {
		WebElement ele = null;
		int attempts = 0;
		while (attempts < retry) {
			try {
				ele = getElement(locator);
				System.out.println("ele is found" + locator);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found" + locator);
				try {
					Thread.sleep(intervalTime);// polling time

				} catch (InterruptedException e1) {
					e1.printStackTrace();// TODO: handle exception
				}
			}
			attempts++;
		}
		if (ele == null) {
			System.out.println("ele is not found tried with element for " + retry + "times with the timeout of 500 ms");
			throw new AppException("no such ele");
		}
		return ele;
	}

	public boolean isPageLoaded(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyStat==='complete'"))
				.toString();
		return Boolean.parseBoolean(flag);
	}

	public WebElement waitforVisibleofEle(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		js.flash(ele);
		return ele;
	}

	public List<WebElement> waitforVisibleofElements(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return list;
	}

	public List<WebElement> waitforVisibleofElements(WebElement ele, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElements(ele));
		return list;
	}

	public void clickWithVisibleWait(By locator, int time) {
		waitforVisibleofEle(locator, time).click();
	}
	public WebElement waitforClickableofEle(By locator, int time) {
		WebElement ele = waitMethod(time).until(ExpectedConditions.elementToBeClickable(locator));
		js.flash(ele);
		return ele;
	}
	public void clickWithPresenceWait(By locator, int time) {
		waitforPresenceofEle(locator, time).click();
	}
	public void clickWithClickableWait(By locator, int time) {
		waitforClickableofEle(locator, time).click();
	}

	public void clickWithPresenceWithStaticWait(By locator, int time) {
		try {
			Thread.sleep(4500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickWithClickableWait(locator, time);
//		waitforClickableofEle(locator, time).click();

	}

	public void clickWithPresenceRegen(By locator, int time) {
		try {
			waitforPresenceofEle(locator, time).click();
		} catch (Exception e) {
			waitforPresenceofEle(locator, time).click();
		}
	}

	public String getTextWait(By locator, int time) {
		return waitforVisibleofEle(locator, time).getText();
	}

	public void sendTextwithWait(By locator, String value, int time) {
//		clickElement(locator);
		waitforVisibleofEle(locator, time).sendKeys(value);
	}
	public void sendTextwithPresenceWait(By locator, String value, int time) {
//		clickElement(locator);
		waitforPresenceofEle(locator, time).sendKeys(value);
	}

	public WebElement waitforPresenceofEle(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		js.flash(ele);
		return ele;
	}

	public List<WebElement> waitforPresenceofElements(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public List<WebElement> waitforPresenceofElements(WebElement ele, int time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.visibilityOfAllElements(ele));
	}

	public void doMultiselected(By locator, String... values) {
		// String... values are the String dynamic array another format we can add or
		// remove any value of string with this keyword.
		Select sel = new Select(getElement(locator));
		if (checkMultiple(locator)) {
			for (String value : values) {
				if (values[0].equalsIgnoreCase("all")) {
					List<WebElement> list = driver.findElements(By.xpath("//select/option"));
					for (WebElement e : list) {
						e.click();
						break;
//	we have also handled the all selection in multi selection
					}
					sel.selectByVisibleText(value);
				}
			}
		}
	}

	public void doRightClickAndSelect(By locator, By RightList, String selectText) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(locator)).build().perform();
		List<WebElement> list = getElements(RightList);
		for (WebElement e : list) {
			String text = e.getText();
			if (text.equals(selectText)) {
				e.click();
				break;
			}
		}
	}

	public void sessionSwitch() {
		String parent = driver.getWindowHandle();
		wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MED_WAIT));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		Set<String> windows = driver.getWindowHandles();
		for (String e : windows) {
			if (!e.equals(parent)) {
				driver.switchTo().window(e);
				driver.manage().window().maximize();
				System.out.println("window switch success");
			}
		}
	}
	@Step("i am waiting to complete the dashboard page loading untill getting the {0} URL")
	public void waitForDashboardPageLoad(String url) {
		String actual = getCurrentURL();
		int sec = 0;
		while (!actual.contains((url))) {
			++sec;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			actual = getCurrentURL();
		}
		System.out.println("Login page get loaded successfully after " + sec + " sec's");
	}

	public void switchToiframeByElement(By locator, int time) {
		WebElement ele = waitforVisibleofEle(locator, time);
		driver.switchTo().frame(ele);
		System.out.println("switched to Frame");
	}

	public void switchToiframeByIndex(int index, int time) {
		driver.switchTo().frame(index);
	}

	public void patientCreatePopuphandle(By popup, By popupYes, int waitTime) {
		try {
			if ((waitforVisibleofElements(popup, waitTime)).size() > 0) {
				clickElement(popupYes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectDate(By dob, By year, By yearList, By month, By monthList, By dateList, String DateValue,
			int waitTime) {
		String[] split = DateValue.split("/");
		String dateV = split[0];
		int inputDateInt = Integer.parseInt(dateV);
		String monthV = split[1].toLowerCase();
		String yearV = split[2];
		actionClick(dob);
		List<WebElement> years = waitforVisibleofElements(yearList, waitTime);
		for (WebElement e : years) {
			String val = e.getText();
			if (val.equals(yearV)) {
				e.click();
				break;
			}
		}
		List<WebElement> months = waitforVisibleofElements(monthList, waitTime);
		for (WebElement e : months) {
			String val = e.getText().toLowerCase();
			if (val.equals(monthV)) {
				e.click();
				break;
			}
		}
		List<WebElement> dates = waitforVisibleofElements(dateList, waitTime);
		int activeDays = dates.size();
		if (activeDays < inputDateInt) {
			System.out.println("input date" + inputDateInt + " activedays " + activeDays
					+ " you are selecting the date which unavailable in the selected month " + monthV);
		}
		for (WebElement e : dates) {
			String val = e.getText();
			if (val.equals(dateV)) {
				e.click();
				break;
			}
		}
//		clickElement(dob);
//		String[] split = DateValue.split("/");
//		String dateV = split[0];
//		int inputDateInt = Integer.parseInt(dateV);
//		String monthV = split[1].toLowerCase();
//		String yearV = split[2];
//		System.out.println(inputDateInt + monthV + yearV);//
//		clickElement(year);
//		List<WebElement> years = waitforVisibleofElements(yearList, waitTime);
//		for (WebElement e : years) {
//			String val = e.getText();
////			System.out.println("actual "+yearV +" getting"+val);
//			if (val.equals(yearV)) {
//				e.click();
////				System.out.println("year selected "+yearV);
//				break;
//			}
//		}
//		List<WebElement> months = waitforVisibleofElements(monthList, waitTime);
//		for (WebElement e : months) {
//			String val = e.getText().toLowerCase();
//			if (val.equals(monthV)) {
//				e.click();
//				System.out.println("month selected" + monthV);
//				break;
//			}
//		}
//		List<WebElement> dates = waitforVisibleofElements(dateList, waitTime);
//		int activeDays = dates.size();
//		System.out.println(activeDays);
//		if (activeDays < inputDateInt) {
//			System.out.println("input date" + inputDateInt + " activedays " + activeDays
//					+ " you are selecting the date which unavailable in the selected month " + monthV);
//		}
//		for (WebElement e : dates) {
//			System.out.println("entering loop");
//			String val = e.getText();
////			System.out.println("getting dates "+val+"actual input "+dateV);
//			if (val.equals(dateV)) {
//				e.click();
////				System.out.println("Date Selected "+dateV);
//				break;
//			}
//		}
	}

	public void clearField(By srchField) {
getElement(srchField).clear();
	}

    public String getTextVisibleWait(By locator, int time) {
		return waitforVisibleofEle(locator, time).getText();
    }
}
