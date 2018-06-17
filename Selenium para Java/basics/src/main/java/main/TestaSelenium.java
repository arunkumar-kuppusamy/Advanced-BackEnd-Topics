package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestaSelenium {

	private static WebDriver driver;

	public static void inicializa() {
		//URL nao eh do navegador nativo e sim de um exe driver que roda o navegar num servidor localhost
		//Baixe a versao de acordo com o seu navegador em http://chromedriver.chromium.org/
		System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver_65a68.exe");
		driver = new ChromeDriver();//Versão 67.0.3396.87 (Versão oficial) 64 bits
	}
	
	public static void fecha() {
		if(driver != null)
			driver.close();
		//fecha sem pedir
	}

	public static void main(String[] args) {
		inicializa();
		
		driver.get("https://www.google.com.br/");
		WebElement campoQ = driver.findElement(By.name("q"));
		campoQ.sendKeys("Rodrigo Cirino de Andrade");
		campoQ.submit();

	}

}
