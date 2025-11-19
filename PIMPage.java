package OrangeHRMProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PIMPage {

	WebDriver driver;

	// Menu & Buttons
	@FindBy(xpath = "//span[text()='PIM']")
	WebElement pimMenu;

	@FindBy(xpath = "//button[contains(.,' Add ')]")
	WebElement addEmployeeButton;

	@FindBy(name = "firstName")
	WebElement firstName;

	@FindBy(name = "middleName")
	WebElement middleName;

	@FindBy(name = "lastName")
	WebElement lastName;

	@FindBy(name = "employeeId")
	WebElement employeeId;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveButton;

	// Locators for validation
	@FindBy(xpath = "//h6[text()='Personal Details']")
	WebElement personalDetailsHeader;

	@FindBy(xpath = "//span[contains(text(),'Required')]")
	WebElement requiredError;

	@FindBy(xpath = "//input[@type='file']")
	WebElement photoUploadInput;

	@FindBy(xpath = "//button[contains(.,'Search')]")
	WebElement searchButton;

	@FindBy(xpath = "//div[@class='oxd-table-card']//div[2]")
	WebElement searchResultRow;

	@FindBy(xpath = "//button[contains(.,'Edit')]")
	WebElement editButton;

	@FindBy(xpath = "//button[contains(.,'Delete')]")
	WebElement deleteButton;

	@FindBy(xpath = "//button[contains(.,'Yes, Delete')]")
	WebElement confirmDeleteButton;

	@FindBy(xpath = "//ul[@class='oxd-pagination__ul']")
	WebElement paginationControl;

	@FindBy(xpath = "//span[contains(.,'Enabled')]")
	WebElement statusEnabled;

	@FindBy(xpath = "//span[contains(.,'Disabled')]")
	WebElement statusDisabled;

	public PIMPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void clickPIMMenu() {
		pimMenu.click();
	}

	public void clickAddEmployeeButton() {
		addEmployeeButton.click();
	}

	public void enterFirstName(String fname) {
		firstName.sendKeys(fname);
	}

	public void enterMiddleName(String mname) {
		middleName.sendKeys(mname);
	}

	public void enterLastName(String lname) {
		lastName.sendKeys(lname);
	}

	public void enterEmployeeId(String empId) {
		employeeId.clear();
		employeeId.sendKeys(empId);
	}

	public void clickSaveButton() {
		saveButton.click();
	}

	// Validations
	public boolean isEmployeeAdded() {
		try {
			return personalDetailsHeader.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isErrorDisplayed() {
		try {
			return requiredError.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void uploadPhoto(String path) {
		photoUploadInput.sendKeys(path);
	}

	public void searchEmployee(String keyword) {
		employeeId.clear();
		employeeId.sendKeys(keyword);
		searchButton.click();
	}

	public boolean isSearchResultDisplayed() {
		try {
			return searchResultRow.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void editEmployeeDetails(String newName) {
		editButton.click();
		firstName.clear();
		firstName.sendKeys(newName);
		saveButton.click();
	}

	public void deleteEmployee() {
		deleteButton.click();
		confirmDeleteButton.click();
	}

	public boolean isPaginationDisplayed() {
		try {
			return paginationControl.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void changeEmployeeStatus() {
		if (statusEnabled.isDisplayed()) {
			statusEnabled.click();
		} else {
			statusDisabled.click();
		}
	}
}
