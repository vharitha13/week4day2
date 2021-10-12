package week4day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Carwale {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.carwale.com/");
		driver.findElement(By.xpath("//span[text()='Used']")).click();
		WebElement place = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
		place.sendKeys("Chennai");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(place));
		WebElement locate = driver.findElement(By.xpath("//li[contains(@label,'Chennai')]//div//span//mark"));
		Actions builder = new Actions(driver);
		builder.moveToElement(locate).click().perform();
		driver.findElement(By.xpath("//div//div[text()='Choose your Budget']")).click();
		WebElement firstSlider = driver.findElement(By.xpath("(//button[@type='button']//div)[1]"));
		WebElement secondSlider = driver.findElement(By.xpath("(//button[@type='button']//div)[2]"));
		int minValue = 4;
		int maxValue = 12;
		builder.clickAndHold(firstSlider).perform();
		builder.moveByOffset(minValue, 0).build().perform();
		builder.release(firstSlider).perform();
		builder.clickAndHold(secondSlider).build().perform();
		builder.moveByOffset(0, maxValue);
		builder.release(secondSlider).perform();
	}

}
