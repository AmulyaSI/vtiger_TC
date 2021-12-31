package vtiger_TC_script;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base_Test.BaseTestClass;
import vtiger_pomRepository.Create_Invoice_Page;
import vtiger_pomRepository.Home_Page;
import vtiger_pomRepository.Invoice_Page;
import vtiger_pomRepository.Login_Page;

public class TC_15 extends BaseTestClass {
	@Test
	public void login() {

		//step1: Navigate to CRM Application
		String parentwinID= driver.getWindowHandle();

		String expectedTitle= "vtiger CRM 5 - Commercial Open Source CRM";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "login page is not displayed");
		Reporter.log("login page is displayed",true);


		//step2: login to app
		loginPage = new Login_Page(driver);
		String expectedUsername ="admin";
		loginPage.getUserNameTextfield().clear();
		loginPage.getUserNameTextfield().sendKeys("admin");
		String actualUserName = loginPage.getUserNameTextfield().getAttribute("value");
		Assert.assertEquals(actualUserName, expectedUsername, "username is invalid");
		Reporter.log("username is valid",true);

		String expectedpassword = "root";
		loginPage.getPasswordTextfield().clear();
		loginPage.getPasswordTextfield().sendKeys("root");
		String actualPassword = loginPage.getPasswordTextfield().getAttribute("value");
		Assert.assertEquals(actualPassword, expectedpassword, "password is invalid");
		Reporter.log("password is valid",true);


		String expectedHomePage = "Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM";

		loginPage.getLoginButton().click();
		String actualHomePage = driver.getTitle();
		Assert.assertEquals(actualHomePage, expectedHomePage, "Home page is not displayed");
		Reporter.log("home page is displayed",true);


		//step3: Go to "More" DropDown menu & click on "Invoice" sub menu
		homePage = new Home_Page(driver);
		homePage.getMoreDropdown().click();
		String expinvoicePage= "Administrator - Invoice - vtiger CRM 5 - Commercial Open Source CRM";
		homePage.getInvoiceSubMenu().click();

		//step4: click on lookup image of "Create Invoice" Page
		String actualInvoicePage = driver.getTitle();
		Assert.assertEquals(actualInvoicePage, expinvoicePage, "invoice page is not displayed");
		Reporter.log("invoice page is displayed",true);

		invoicePage= new Invoice_Page(driver);
		invoicePage.getCreateInvoiceLookUpIcon().click();

		createinvoice = new Create_Invoice_Page(driver);
		createinvoice.getCancelButton().click();
		String backToInvoicePage = driver.getTitle();
		System.out.println(backToInvoicePage);
		Assert.assertEquals(backToInvoicePage, expinvoicePage, "invoice page is not displayed");
		Reporter.log("\"creating New Invoice\" page should be closed and \"Invoice\" Page should be display. ",true);

	}
}
