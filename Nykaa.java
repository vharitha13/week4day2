package week4day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.nykaa.com/");
		driver.findElement(By.name("search-suggestions-nykaa")).sendKeys("L'Oreal Paris", Keys.ENTER);
		String text = driver.findElement(By.xpath("//div[@id='title-listing']")).getText();
		if (text.contains("L'Oreal Paris")) {
			System.out.println("The title of the page has 'L'oreal Paris'");
		} else {
			System.out.println("The title does not have L'Oreal Paris");
		}
		driver.findElement(By.xpath("//div//button//span[@class='sort-name']")).click();
		driver.findElement(By.xpath("(//label[@class='control control-radio'])[4]")).click();
		driver.findElement(By.xpath("//div//span[text()='Category']")).click();
		driver.findElement(By.xpath("//div//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//div//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//label//div//span[text()='Shampoo']")).click();
		driver.findElement(By.xpath("//div//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//label//div//span[text()='Color Protection']")).click();
		List<WebElement> filters = driver.findElements(By.xpath("(//div[@id='filters-listing']//div)[1]"));
		for (WebElement webElement : filters) {
			if (webElement.getText().contains("Shampoo")) {
				System.out.println("Shampoo is applied in the filters");
			} else {
				System.out.println("Shampoo is not applied in the filters");
			}
		}
		driver.findElement(By.xpath("//img[contains(@alt,'Colour Protect')]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(1));
		driver.findElement(By.xpath("(//li//label)[2]//span[@class='size-pallets']")).click();
		String price = driver.findElement(By.xpath("(//div[@class='price-info'])[1]")).getText();
		System.out.println("The price of the item is:" + price);
		driver.findElement(By.xpath("(//div[@class='pull-left'])[1]//div//button")).click();
		driver.findElement(By.xpath("//div//div//div[@class='AddBagIcon']")).click();
		String grandTotal = driver.findElement(By.xpath("(//div[@class='payment-tbl-data']//div)[10]")).getText();
		System.out.println("The grand total of the product is:" + grandTotal);
		driver.findElement(By.xpath("//button//span[text()='Proceed']")).click();
		driver.findElement(By.xpath("//div//button[text()='CONTINUE AS GUEST']")).click();
		String finalTotal = driver.findElement(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']"))
				.getText();
		System.out.println("Final price is:" + finalTotal);
		if (finalTotal.contains("352") == grandTotal.contains("352")) {
			System.out.println("The total matches");
		} else
			System.out.println("Total does not match");
		driver.quit();
	}
}
