package com.cloudinfinity.pokemonapi;

import javax.annotation.concurrent.NotThreadSafe;

import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
@SpringBootTest
@NotThreadSafe
@TestInstance(value = Lifecycle.PER_CLASS)
class PokemonapiApplicationTests {
	 private WebDriver driver;
	
	 @BeforeAll
	 public void setUp() {
	 	System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
	 	 ebDriverManager.chromedriver().setup();
	 	ChromeOptions options = new ChromeOptions();
	 	options.addArguments("--no-sandbox");
	 	options.addArguments("--disable-dev-shm-usage");
	 	options.addArguments("--headless");
	 	driver = new ChromeDriver(options);
	 	// driver = new FirefoxDriver();
	 }
	
	 @Test
	 void checkSmeargle() {
	 	driver.get("http://pokedex-bkt.s3-website-us-east-1.amazonaws.com/");
	 	WebElement submit = driver.findElement(By.id("searchBar"));
	 	WebElement searchBar = driver.findElement(By.id("searchText"));
	 	searchBar.sendKeys("235");
	 	submit.submit();
	 	WebElement test = driver.findElement(By.className("pokemon-name"));
	 	String tester = test.getText();
	 	assert(tester.equals("Smeargle"));

	 }
	
	 @Test
	 void checkOutofBounds() {
	 	driver.get("http://pokedex-bkt.s3-website-us-east-1.amazonaws.com/");
	 	WebElement submit = driver.findElement(By.id("searchBar"));
	 	WebElement searchBar = driver.findElement(By.id("searchText"));
	 	searchBar.sendKeys("930");
	 	submit.submit();
	 	String tester = driver.switchTo().alert().getText();
	 	assert(tester.equals("This pokemon does not exist"));
		driver.switchTo().alert().dismiss();
		

	 }

}
