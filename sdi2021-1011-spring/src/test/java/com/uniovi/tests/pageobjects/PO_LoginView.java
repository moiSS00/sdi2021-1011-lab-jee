package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_View {
	
	static public void fillForm(WebDriver driver, String dnip, String passwordp) {
		
		//Rellenamos el campo del DNI
		WebElement dni = driver.findElement(By.name("username"));
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		
		//Rellenamos el campo de la contraseña
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		
		//Pulsamos el boton para enviar los datos
		By boton = By.className("btn");
		driver.findElement(boton).click();
		
	}
	
}
