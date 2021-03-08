package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_AddUserView {

	static public void fillForm(WebDriver driver, int role, String dnip, String namep, String lastnamep, String passwordp) {
		
		// Seleccionamos el alumnos userOrder
		new Select(driver.findElement(By.id("role"))).selectByIndex(role);
		
		WebElement dni = driver.findElement(By.name("dni"));
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		WebElement name = driver.findElement(By.name("name"));
		name.click();
		name.clear();
		name.sendKeys(namep);
		WebElement lastname = driver.findElement(By.name("lastName"));
		lastname.click();
		lastname.clear();
		lastname.sendKeys(lastnamep);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		
		// Pulsar el boton de Alta.
		By boton = By.className("btn");
		driver.findElement(boton).click();

	}
}
