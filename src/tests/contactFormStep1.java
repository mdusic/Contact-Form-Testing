package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import main.Constants;
import main.ServicesPage;

public class contactFormStep1 {

	WebDriver driver;

	@BeforeTest
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\MATEJ\\QA\\selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(Constants.PAGE_URL);
	}

	@Test(description = "Empty name field test")
	public void emptyStep1() {
		ServicesPage servicesPage = new ServicesPage(driver);
		servicesPage.nextButtonClick();

		Assert.assertEquals(servicesPage.fieldLabelText(), "Please enter your name or company name");
	}

	@Test(description = "Input name test")
	public void step1() {
		ServicesPage servicesPage = new ServicesPage(driver);
		servicesPage.inputName(Constants.USER_NAME);
		servicesPage.nextButtonClick();

		Assert.assertEquals(servicesPage.sectionLabelText(), "A brief introduction");
	}

	@Test(description = "Empty description field test", dependsOnMethods = { "emptyStep1" })
	public void emptyStep2() {
		ServicesPage servicesPage = new ServicesPage(driver);
		servicesPage.nextButtonClick();

		Assert.assertEquals(servicesPage.fieldLabelText(),
				"Please describe your project in a sentence. It's a good conversation starter.");
	}

	@Test(description = "Input description test", dependsOnMethods = { "step1" })
	public void step2() {
		ServicesPage servicesPage = new ServicesPage(driver);
		servicesPage.inputDescription(Constants.USER_DESCRIPTION);
		servicesPage.nextButtonClick();

		Assert.assertEquals(servicesPage.sectionLabelText(), "Getting in touch");
	}

	@Test(description = "Empty contact field test", dependsOnMethods = { "emptyStep2" })
	public void emptyStep3() {
		ServicesPage servicesPage = new ServicesPage(driver);
		servicesPage.nextButtonClick();

		Assert.assertEquals(servicesPage.fieldLabelText(), "We need your mail to contact you.");
	}

	@Test(description = "Previous button test on step 3", dependsOnMethods = { "emptyStep3" })
	public void previousStep3() {
		ServicesPage servicesPage = new ServicesPage(driver);
		servicesPage.previousButtonClick();
		
		Assert.assertEquals(servicesPage.sectionLabelText(), "A brief introduction");
	}

	@Test(description = "Previous button test on step 2", dependsOnMethods = { "previousStep3" })
	public void previousStep2() {
		ServicesPage servicesPage = new ServicesPage(driver);
		servicesPage.previousButtonClick();

		Assert.assertEquals(servicesPage.sectionLabelText(), "Kick-off your project");
	}

	@Test(description = "Input contact test", dependsOnMethods = { "previousStep2" })
	public void step3() {
		ServicesPage servicesPage = new ServicesPage(driver);
		servicesPage.nextButtonClick();
		servicesPage.nextButtonClick();
		servicesPage.inputContact(Constants.USER_CONTATCT);
		servicesPage.nextButtonClick();

		Assert.assertEquals(servicesPage.sectionLabelText(), "We'll contact you soon");
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
