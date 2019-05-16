package e2e.pageobjects;

import java.util.ArrayList;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import e2e.utilities.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class ECSDigitalTestPage {


	public ECSDigitalTestPage open(String url, String val) {
	
		System.out.println("Page Opened");
		DriverManager.getDriver().navigate().to(url);
		return  new ECSDigitalTestPage();
	}

	public void open(String url) {

		System.out.println("Page Opened");
		DriverManager.getDriver().navigate().to(url);
		return ;
	}

	public void clickRenderChallenge() {

		WebElement challenge = DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'Render the Challenge')]"));
		challenge.click();

	}

	public ArrayList<Integer> getArray(int row) {

		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(Integer.parseInt(DriverManager.getDriver().findElement(By.xpath("//tr[" + row + "]//td[1]")).getText()));
		al.add(Integer.parseInt(DriverManager.getDriver().findElement(By.xpath("//tr[" + row + "]//td[2]")).getText()));
		al.add(Integer.parseInt(DriverManager.getDriver().findElement(By.xpath("//tr[" + row + "]//td[3]")).getText()));
		al.add(Integer.parseInt(DriverManager.getDriver().findElement(By.xpath("//tr[" + row + "]//td[4]")).getText()));
		al.add(Integer.parseInt(DriverManager.getDriver().findElement(By.xpath("//tr[" + row + "]//td[5]")).getText()));
		al.add(Integer.parseInt(DriverManager.getDriver().findElement(By.xpath("//tr[" + row + "]//td[6]")).getText()));
		al.add(Integer.parseInt(DriverManager.getDriver().findElement(By.xpath("//tr[" + row + "]//td[7]")).getText()));
		al.add(Integer.parseInt(DriverManager.getDriver().findElement(By.xpath("//tr[" + row + "]//td[8]")).getText()));
		al.add(Integer.parseInt(DriverManager.getDriver().findElement(By.xpath("//tr[" + row + "]//td[9]")).getText()));

		return al;

	}

	public void setSubmitChallange() {

		Integer challenge1 = algorithm(getArray(1));
		Integer challenge2 = algorithm(getArray(2));
		Integer challenge3 = algorithm(getArray(3));

		String challenge1String, challenge2String, challenge3String;
		if (challenge1.equals(-1))
			challenge1String = "null";
		else
			challenge1String = challenge1.toString();

		if (challenge2.equals(-1))
			challenge2String = "null";
		else
			challenge2String = challenge2.toString();

		if (challenge3.equals(-1))
			challenge3String = "null";
		else
			challenge3String = challenge3.toString();

		DriverManager.getDriver().findElement(By.xpath("//input[@data-test-id='submit-1']")).sendKeys(challenge1String);
		DriverManager.getDriver().findElement(By.xpath("//input[@data-test-id='submit-2']")).sendKeys(challenge2String);
		DriverManager.getDriver().findElement(By.xpath("//input[@data-test-id='submit-3']")).sendKeys(challenge3String);
		DriverManager.getDriver().findElement(By.xpath("//input[@data-test-id='submit-4']")).sendKeys("Sevcen Sokmen");
		DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'Submit Answers')]")).click();

	}

	public Integer algorithm(ArrayList<Integer> varray) {

		int lastIndex = varray.size() - 1;
		int firstCount = 0, lastCount = 0;
		int firstIndex = 0;
		boolean OK = false;
		firstCount = firstCount + varray.get(firstIndex);
		lastCount = lastCount + varray.get(lastIndex);
		while ((firstIndex + 1) != lastIndex) {
			if (firstCount > lastCount) {
				lastIndex--;
				lastCount = lastCount + varray.get(lastIndex);
			} else if (lastCount > firstCount) {
				firstIndex++;
				firstCount = firstCount + varray.get(firstIndex);
			} else if (firstCount == lastCount) {
				firstIndex++;
				if ((firstIndex + 1) == lastIndex) {
					OK = true;
				} else {

					firstCount = firstCount + varray.get(firstIndex);
				}
			}
		}
		if (OK) {
			return firstIndex;
		} else
			return -1;

	}

	public boolean takeMessage()throws InterruptedException {
		boolean result = false;
		if(isElementPresent(By.xpath("//div[contains(text(),'It looks like your')]"))){
			result = false;
		}

		if(isElementPresent(By.xpath("//div[contains(text(),'Congratulations you have')]"))){
			result = true;
		}
		return result;
	}


	public boolean isElementPresent(By by) {

		try {
			DriverManager.getDriver().findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}


}
