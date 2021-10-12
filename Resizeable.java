package week4day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Resizeable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://jqueryui.com/resizable/");
		WebElement frame = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(frame);
		Actions builder=new Actions(driver);		
		WebElement resize = driver.findElement(By.xpath("//h3[text()='Resizable']/following-sibling::div[3]"));
		Point location = resize.getLocation();
		System.out.println(location);
		builder.clickAndHold(resize).perform();
		builder.moveByOffset(50, 50).perform();
		builder.release();
		driver.switchTo().defaultContent();
	}

}
