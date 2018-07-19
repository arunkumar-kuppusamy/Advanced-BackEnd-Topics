import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestaFormulario {

	private WebDriver driver;
	private WebElement inputZip;
	
	public TestaFormulario(WebDriver driver, String URL) {
		this.driver = driver;
		this.driver.get(URL);
		inputZip = driver.findElement(By.id("inputZip"));
	}

	public boolean testeRadioButton() {
		Random randomNum = new Random();
		int showMe = 0 + randomNum.nextInt(9);
		List<WebElement> radioButton = driver.findElements(By.name("inlineRadioOptions"));
		radioButton.get(showMe).click();
		return true;
	}
	
	public boolean testaCepComZeroAEsquerda() {
		inputZip.sendKeys("81130220");
		//boolean display = inputZip.isDisplayed();
		boolean cepnaoencontrado = driver.getPageSource().contains("CEP Não encontrado.");
		System.out.println("cepnaoencontrado: "+cepnaoencontrado);
		
		return cepnaoencontrado;
	}
	public boolean testaCepSemZeroAEsquerda() {
		
		return false;
	}
	public boolean testaCepSemPreenchimento() {
		
		return false;
	}
	public boolean testaCepInteracaoUsuario() {
		
		return false;
	}
	
}
