package OrangeHRMProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(name = "username")
    WebElement usernameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-alert-content-text']")
    WebElement errorMessage;

    @FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")
    WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public boolean isUsernameFieldVisible() {
        return usernameField.isDisplayed();
    }

    public boolean isPasswordFieldVisible() {
        return passwordField.isDisplayed();
    }

    public boolean isLoginButtonVisible() {
        return loginButton.isDisplayed();
    }

    public void clickForgotPassword() {
        forgotPasswordLink.click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPasswordMasKedText() {
        return passwordField.getAttribute("type");
    }
}

