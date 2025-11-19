package OrangeHRMProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage {

	WebDriver driver;

	@FindBy(xpath = "//span[text()='Admin']")
	WebElement adminMenu;

	@FindBy(xpath = "//button[normalize-space()='Add']")
	WebElement addButton;

	@FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement searchButton;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement saveButton;

	@FindBy(xpath = "//button[normalize-space()='Cancel']")
	WebElement cancelButton;

	@FindBy(xpath = "(//div[contains(@class,'oxd-select-text--active')])[1]")
	WebElement userRoleDropdown;

	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	WebElement employeeNameField;

	@FindBy(xpath = "(//div[contains(@class,'oxd-select-text--active')])[2]")
	WebElement statusDropdown;

	@FindBy(xpath = "//input[@name='username']")
	WebElement usernameField;

	@FindBy(xpath = "//input[@name='password']")
	WebElement passwordField;

	@FindBy(xpath = "//input[@name='confirmPassword']")
	WebElement confirmPasswordField;

	@FindBy(xpath = "//div[@role='alert']")
	WebElement successMessage;

	@FindBy(xpath = "(//label[text()='User Role']/../following-sibling::div//div[contains(@class,'oxd-select-text-input')])[1]")
	WebElement searchUserRoleDropdown;

	@FindBy(xpath = "(//label[text()='Status']/../following-sibling::div//div[contains(@class,'oxd-select-text-input')])[1]")
	WebElement searchStatusDropdown;

	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	WebElement searchEmployeeName;

	@FindBy(xpath = "//input[@name='username']")
	WebElement searchUsernameField;

	@FindBy(xpath = "//div[@class='oxd-table-body']/div[1]")
	WebElement firstRow;

	@FindBy(xpath = "(//button[@title='Edit'])[1]")
	WebElement firstEditButton;

	@FindBy(xpath = "(//button[@title='Delete'])[1]")
	WebElement firstDeleteButton;

	@FindBy(xpath = "//button[normalize-space()='Yes, Delete']")
	WebElement confirmDeleteButton;

	public AdminPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickAdminMenu() {
		adminMenu.click();
	}

	public void clickAddButton() {
		addButton.click();
	}

	public void selectUserRole(String role) {
		userRoleDropdown.click();
		driver.findElement(By.xpath("//div[@role='option']//span[text()='" + role + "']")).click();
	}

	public void enterEmployeeName(String name) {
		employeeNameField.clear();
		employeeNameField.sendKeys(name);
		driver.findElement(By.xpath("//div[@role='option']//span[contains(text(),'" + name + "')]")).click();
	}

	public void selectStatus(String status) {
		statusDropdown.click();
		driver.findElement(By.xpath("//div[@role='option']//span[text()='" + status + "']")).click();
	}

	public void enterUsername(String username) {
		usernameField.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordField.sendKeys(confirmPassword);
	}

	public void clickSaveButton() {
		saveButton.click();
	}

	public boolean isUserSaved() {
		try {
			return successMessage.isDisplayed() && successMessage.getText().contains("Successfully Saved");
		} catch (Exception e) {
			return false;
		}
	}

	public void searchUserByRole(String role) {
		searchUserRoleDropdown.click();
		driver.findElement(By.xpath("//div[@role='option']//span[text()='" + role + "']")).click();
		searchButton.click();
	}

	public void searchUserByStatus(String status) {
		searchStatusDropdown.click();
		driver.findElement(By.xpath("//div[@role='option']//span[text()='" + status + "']")).click();
		searchButton.click();
	}

	public void editFirstUser(String newUsername) {
		firstEditButton.click();
		usernameField.clear();
		usernameField.sendKeys(newUsername);
		clickSaveButton();
	}

	public void deleteFirstUser() {
		firstDeleteButton.click();
		confirmDeleteButton.click();
	}
}
