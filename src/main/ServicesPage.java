package main;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ServicesPage {

	WebDriver driver;

	@FindBy(how = How.XPATH, using = "//*[@id=\'name\']")
	WebElement input_name;

	@FindBy(how = How.XPATH, using = "//*[@id=\'container\']/div/main/div/section[7]/div[2]/div/form/button")
	WebElement nextButton;

	@FindBy(how = How.XPATH, using = "//*[@id=\'description\']")
	WebElement input_description;

	@FindBy(how = How.XPATH, using = "//*[@id=\'contact\']")
	WebElement input_contact;

	@FindBy(how = How.XPATH, using = "//*[@id=\'container\']/div/main/div/section[7]/div[2]/div/button")
	WebElement previousButton;

	@FindBy(how = How.XPATH, using = "//*[@id=\'container\']/div/main/div/section[7]/div[2]/div/header/h1")
	WebElement sectionLabel;

	@FindBy(how = How.XPATH, using = "//*[@id=\'container\']/div/main/div/section[7]/div[2]/div/form/div/label")
	WebElement input_field_label;

	public ServicesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void inputName(String name) {
		input_name.sendKeys(name);
	}

	public void nextButtonClick() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
		nextButton.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void previousButtonClick() {
		previousButton.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void inputDescription(String description) {
		input_description.sendKeys(description);
	}

	public void inputContact(String contact) {
		input_contact.sendKeys(contact);
	}

	public String sectionLabelText() {
		return sectionLabel.getText();
	}

	public String fieldLabelText() {
		return input_field_label.getText();
	}

}
