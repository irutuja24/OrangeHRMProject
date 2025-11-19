package OrangeHRMProject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTC {

	WebDriver driver;
	String Website = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	LoginPage login;

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
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();

	}

	@Test
	public void TC01LoginWithValidCredentials() {
		login.enterUsername("Admin");
		login.enterPassword("admin123");
		login.clickLoginButton();
		Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"), "user is not redirected to dashboard");
	}

	@Test
	public void TC02LoginWithInvalidCredentials() {
		login.enterUsername("Admi");
		login.enterPassword("admin12");
		login.clickLoginButton();
		Assert.assertTrue(login.getErrorMessage().contains("Invalid credentials"),
				"Error message for invalid credentials not displayed");
	}

	@Test
	public void TC03InvalidUsername() {
		login.enterUsername("asdf");
		login.enterPassword("admin123");
		login.clickLoginButton();
		Assert.assertTrue(login.getErrorMessage().contains("Invalid credentials"),
				"Error message for invalid username not displayed");
	}

	@Test
	public void TC04InvalidPassword() {
		login.enterUsername("Admin");
		login.enterPassword("123");
		login.clickLoginButton();
		Assert.assertTrue(login.getErrorMessage().contains("Invalid credentials"),
				"Error message for invalid password not displayed");
	}

	@Test
	public void TC05EmptyUsername() {
		login.enterUsername("");
		login.enterPassword("admin123");
		login.clickLoginButton();
		Assert.assertTrue(driver.getPageSource().contains("Required"), "Required message not shown for empty username");
	}

	@Test
	public void TC06EmptyPassword() {
		login.enterUsername("Admin");
		login.enterPassword("");
		login.clickLoginButton();
		Assert.assertTrue(driver.getPageSource().contains("Required"), "Required message not shown for empty password");
	}

	@Test
	public void TC07BothFieldsEmpty() {
		login.enterUsername("");
		login.enterPassword("");
		login.clickLoginButton();
		Assert.assertTrue(driver.getPageSource().contains("Required"), "Required message not shown for empty fields");
	}

	@Test
	public void TC08CheckForgotPasswordLink() {
		login.clickForgotPassword();
		Assert.assertTrue(driver.getCurrentUrl().contains("/requestPasswordResetCode"),
				"Forgot Password link is not working");
	}

	@Test
	public void TC09VerifyURL() {
		String actURL = driver.getCurrentUrl();
		String expURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		Assert.assertEquals(actURL, expURL, "Login Page URL mismatch!");
	}

	@Test
	public void TC10PasswordMasking() {
		String type = login.getPasswordMasKedText();
		Assert.assertEquals(type, "password", "Password field is not masked!");
	}

	@Test
	public void TC11LoginPageTitle() {
		String actTitle = driver.getTitle();
		String expTitle = "OrangeHRM";
		Assert.assertEquals(actTitle, expTitle, "Login Page title mismatch!");
	}

	@Test
	public void TC12UIElementsVisibility() {
		Assert.assertTrue(login.isUsernameFieldVisible(), "Username field is not visible");
		Assert.assertTrue(login.isPasswordFieldVisible(), "Password field is not visible");
		Assert.assertTrue(login.isLoginButtonVisible(), "Login button is not visible");
	}
}
