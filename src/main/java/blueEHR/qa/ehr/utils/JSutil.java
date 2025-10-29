package blueEHR.qa.ehr.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSutil {

	private WebDriver driver;
	private JavascriptExecutor js;
	
	public JSutil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor)this.driver;
	}
	
	public String getTitleByJs() {
		return js.executeScript("return document.title").toString();
	}
	
	public String getURLByJs() {
		return js.executeScript("return document.URL").toString();
	}
	
	
	public void generateJSAlert(String mesg) {
		js.executeScript("alert('"+mesg+"')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
	}
	
	public void generateJSConfirm(String mesg) {
		js.executeScript("confirm('"+mesg+"')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.switchTo().alert().accept();
	}
	
	
	public void generateJSPrompt(String mesg, String value) {
		js.executeScript("prompt('"+mesg+"')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
		alert.accept();
	}
	
	
	public void goBackWithJS() {
		js.executeScript("history.go(-1)");
	}
	
	public void goForwardWithJS() {
		js.executeScript("history.go(1)");
	}
	
	public void pageRefreshWithJS() {
		js.executeScript("history.go(0)");
	}
	
	public String getPageInnerText() {
		return js.executeScript("return document.documentElement.innerText;").toString();
	}
	
	public void scrollMiddlePageDown() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight/2);");
	}
	
	public void scrollPageDown() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	
	public void scrollPage200() {	
		System.out.println("scroll done");
		js.executeScript("window.scrollTo(0, 200);");
	}
	
	public void scrollPageUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0);");
	}
	
	public void scrollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	/**
	 * example: "document.body.style.zoom = '400.0%'"
	 * @param zoomPercentage
	 */
	public void zoomChromeEdgeSafari(String zoomPercentage) {
		String zoom = "document.body.style.zoom = '"+zoomPercentage+"%'";
		js.executeScript(zoom);
	}
	
	/**
	 * example: "document.body.style.MozTransform = 'scale(0.5)'; ";
	 * @param zoomPercentage
	 */
	public void zoomFirefox(String zoomPercentage) {
		String zoom = "document.body.style.MozTransform = 'scale("+zoomPercentage+")'";
		js.executeScript(zoom);
	}
	
	
	public void drawBorder(WebElement element) {
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
	
	public void flash(WebElement element) { 
		for (int i = 0; i < 4; i++) {
			changeColor("rgb(0,200,0)", element);// Green
			changeColor("rgb(200,200,200)", element);// Purple
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
//		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
		js.executeScript("arguments[0].style.backgroundColor = arguments[1]", element, color);
		//G->P->G->P

		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
		}
	}
	
	
	public void clickElementByJS(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

	public void sendKeysUsingWithId(String id, String value) {
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
						  //document.getElementById('input-email').value ='tom@gmail.com'
	}

	public void waitTillPageLoad() {
		
		 String status=js.executeScript("return document.readyState;").toString();
		 System.out.println(status);
		 while(!status.equals("complete")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
