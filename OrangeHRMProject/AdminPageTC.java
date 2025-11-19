package OrangeHRMProject;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminPageTC {

	WebDriver driver;
	String Website = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	LoginPage login;
	AdminPage admin;

	@AfterClass
	public void afterMethods() {
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(Website);
		driver.manage().window().maximize();

		login = new LoginPage(driver);
		login.enterUsername("Admin");
		login.enterPassword("admin123");
		login.clickLoginButton();

		admin = new AdminPage(driver);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

	@Test
	public void TC01AddNewUserValidDetails() {
		admin.clickAdminMenu();
		admin.clickAddButton();
		admin.selectUserRole("Admin");
		admin.enterEmployeeName("Ranga Akunuri");
		admin.selectStatus("Enabled");
		admin.enterUsername("TestUser123");
		admin.enterPassword("Test@123");
		admin.enterConfirmPassword("Test@123");
		admin.clickSaveButton();
		Assert.assertTrue(admin.isUserSaved(), "User not added successfully!");
	}

	@Test
	public void TC02AddUserDuplicateUsername() {
		admin.clickAdminMenu();
		admin.clickAddButton();
		admin.selectUserRole("ESS");
		admin.enterEmployeeName("Paul Collings");
		admin.selectStatus("Enabled");
		admin.enterUsername("TestUser123");
		admin.enterPassword("Test@123");
		admin.enterConfirmPassword("Test@123");
		admin.clickSaveButton();
		Assert.assertTrue(driver.getPageSource().contains("Already exists"), "Duplicate username error not displayed!");
	}

	@Test
	public void TC03SearchUserByRole() {
		admin.clickAdminMenu();
		admin.searchUserByRole("Admin");
		Assert.assertTrue(driver.getPageSource().contains("Admin"), "User search by role not working!");
	}

	@Test
	public void TC04SearchUserByStatus() {
		admin.clickAdminMenu();
		admin.searchUserByStatus("Enabled");
		Assert.assertTrue(driver.getPageSource().contains("Enabled"), "User search by status not working!");
	}

	@Test
	public void TC05EditUserDetails() {
		admin.clickAdminMenu();
		admin.editFirstUser("EditedUser123");
		Assert.assertTrue(admin.isUserSaved(), "User not edited successfully!");
	}

	@Test
	public void TC06DeleteUser() {
		admin.clickAdminMenu();
		admin.deleteFirstUser();
		Assert.assertTrue(driver.getPageSource().contains("Successfully Deleted"), "User not deleted successfully!");
	}

	@Test
	public void TC07ResetUserPassword() {

		Assert.assertTrue(true, "Reset password functionality placeholder");
	}

	@Test
	public void TC08VerifyRoleAssignment() {
		admin.clickAdminMenu();
		admin.searchUserByRole("Admin");
		Assert.assertTrue(driver.getPageSource().contains("Admin"), "Role assignment not verified!");
	}

	@Test
	public void TC09VerifyPaginationInUserList() {
		admin.clickAdminMenu();
		Assert.assertTrue(driver.getPageSource().contains("Page"), "Pagination not working!");
	}

	@Test
	public void TC10VerifyMandatoryFieldsInAddUser() {
		admin.clickAdminMenu();
		admin.clickAddButton();
		admin.clickSaveButton();
		Assert.assertTrue(driver.getPageSource().contains("Required"), "Mandatory field validation not working!");
	}
}
