package vtiger_TC_script;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base_Test.BaseTestClass;
import vtiger_pomRepository.Create_Invoice_Page;
import vtiger_pomRepository.Home_Page;
import vtiger_pomRepository.Invoice_Page;
import vtiger_pomRepository.Login_Page;
import vtiger_pomRepository.OrganizationIcon_page;

public class TC_14 extends BaseTestClass {
	@Test
	public void login() {

		//step1: Navigate to CRM Application
		String parentwinID= driver.getWindowHandle();

		String expectedTitle= "vtiger CRM 5 - Commercial Open Source CRM";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "login page is not displayed");
		Reporter.log("login page is displayed", true);

		//step2: login to app
		loginPage = new Login_Page(driver);
		String expectedUsername ="admin";
		loginPage.getUserNameTextfield().clear();
		loginPage.getUserNameTextfield().sendKeys("admin");
		String actualUserName = loginPage.getUserNameTextfield().getAttribute("value");
		Assert.assertEquals(actualUserName, expectedUsername, "username is invalid");
		Reporter.log("username is valid", true);

		String expectedpassword = "root";
		loginPage.getPasswordTextfield().clear();
		loginPage.getPasswordTextfield().sendKeys("root");
		String actualPassword = loginPage.getPasswordTextfield().getAttribute("value");
		Assert.assertEquals(actualPassword, expectedpassword, "password is invalid");
		Reporter.log("password is valid", true);


		String expectedHomePage = "Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM";

		loginPage.getLoginButton().click();
		String actualHomePage = driver.getTitle();
		Assert.assertEquals(actualHomePage, expectedHomePage, "Home page is not displayed");
		Reporter.log("home page is displayed", true);


		//step3: Go to "More" DropDown menu & click on "Invoice" sub menu
		homePage = new Home_Page(driver);
		homePage.getMoreDropdown().click();
		String expinvoicePage= "Administrator - Invoice - vtiger CRM 5 - Commercial Open Source CRM";
		homePage.getInvoiceSubMenu().click();

		//step4: click on lookup image of "Create Invoice" Page
		String actualInvoicePage = driver.getTitle();
		Assert.assertEquals(actualInvoicePage, expinvoicePage, "invoice page is not displayed");
		Reporter.log("invoice page is displayed", true);

		invoicePage= new Invoice_Page(driver);
		invoicePage.getCreateInvoiceLookUpIcon().click();

		//step5:Create Invoice with following info:Enter Subject,Billing Address,Shipping ,CustomerNo,Address & click on "Look-UP" image beside the "Organisation" field
		String expectedCreateInvoice= "Administrator - Invoice - vtiger CRM 5 - Commercial Open Source CRM";
		String actualCreateInvoice= driver.getTitle();
		Assert.assertEquals(actualCreateInvoice, expectedCreateInvoice, "create invoice page is not displayed");
		Reporter.log("create invoice page is displayed", true);


		//step6: Click on "Look-UP"image beside the Contact Name and select the contact

		createinvoice = new Create_Invoice_Page(driver);
		createinvoice.getSubjectTextField().sendKeys("motog billing");
		createinvoice.getCustomerNameIcon().click();
		Set<String> allwinIDs = driver.getWindowHandles();
		allwinIDs.remove(parentwinID);
		organizationpage = new OrganizationIcon_page(driver);

		for(String windowID : allwinIDs) { 
			driver.switchTo().window(windowID);

			organizationpage.getDipankarContName().click();
		}
		String expectedUrl= "'http://localhost:8888/index.php?module=Contacts&action=Popup&html=Popup_picker&popuptype=specific&form=EditView'";

		Alert alertPopups = driver.switchTo().alert();
		String alertMessages = alertPopups.getText();
		System.out.println(alertMessages);
		alertPopups.accept();
		//	String actUrl = driver.getCurrentUrl();
		//	Assert.assertEquals(actUrl, expectedUrl, "popup is not displayed");
		//	Reporter.log("Should open Oganisation Page in \"new-Browser-Window\"");

		driver.switchTo().window(parentwinID);

		createinvoice.getCustomerNoTextfield().sendKeys("gdgdgdg");

		createinvoice.getOrganizationIcon().click();
		Set<String> allwinID = driver.getWindowHandles();
		allwinID.remove(parentwinID);


		for(String windowID : allwinID) { 
			driver.switchTo().window(windowID);
			organizationpage.getMotorollaName().click();
		}
		Alert alertPopup = driver.switchTo().alert();
		alertPopup.accept();

		driver.switchTo().window(parentwinID);

		createinvoice.getItemIcon().click();

		Set<String> allwinId = driver.getWindowHandles();
		allwinId.remove(parentwinID);

		for(String windowID : allwinId) { 
			driver.switchTo().window(windowID);
			organizationpage.getItemProduct().click();
		}
		driver.switchTo().window(parentwinID);


		
		createinvoice.getSalesCommTextField().sendKeys("1000");

		
		createinvoice.getQuantity().clear();
		createinvoice.getQuantity().sendKeys("1");

		
	

		createinvoice.getSaveButton().click();

	}
}
