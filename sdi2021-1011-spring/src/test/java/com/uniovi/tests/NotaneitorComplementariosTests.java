package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_AddUserView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

public class NotaneitorComplementariosTests {

	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\Moises\\Desktop\\UNIVERSIDAD\\TERCERO\\"
			+ "2 CUATRIMESTRE\\SDI\\Laboratorio\\PL5\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	// En MACOSX (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas):
	// static String PathFirefox65 =
	// "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	// static String Geckdriver024 = "/Users/delacal/selenium/geckodriver024mac";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// PR01. Añadimos un profesor de forma normal
	@Test
	public void PR01() {

		// Nos logeamos como administrador
		PO_NavView.loginAs(driver, "99999988F", "123456");

		// Vamos al formulario para agregar a un usuario
		List<WebElement> elementos = PO_View.checkElement(driver, "id", "users-menu");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "@href", "/user/add");
		elementos.get(0).click();

		// Agregamos un usuario con rol Profesor.
		PO_AddUserView.fillForm(driver, 1, "prueba1", "prueba2", "prueba3", "prueba4");

		// Volvemos pagina principal
		elementos = PO_View.checkElement(driver, "free", "//*[@id=\"myNavbar\"]/ul[1]/li[1]/a");
		elementos.get(0).click();

		// Vamos a la lista de usuarios
		elementos = PO_View.checkElement(driver, "id", "users-menu");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//*[@id=\"users-menu\"]/ul/li[2]/a");
		elementos.get(0).click();

		// Paginacion
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Nos vamos a la última página
		elementos.get(3).click();
		elementos = PO_View.checkElement(driver, "text", "prueba1");

		// Nos desconectamos
		elementos = PO_View.checkElement(driver, "free", "//*[@id=\"myNavbar\"]/ul[2]/li[2]/a");
		elementos.get(0).click();
	}

	// PR02. Añadimos un profesor con errores
	@Test
	public void PR02() {

		// Nos logeamos como administrador
		PO_NavView.loginAs(driver, "99999988F", "123456");

		// Vamos al formulario para agregar a un usuario
		List<WebElement> elementos = PO_View.checkElement(driver, "id", "users-menu");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "@href", "/user/add");
		elementos.get(0).click();

		// Agregamos un usuario con rol Profesor.
		PO_AddUserView.fillForm(driver, 1, "1", "1", "1", "1");
		PO_RegisterView.checkElement(driver, "text", "El dni debe de tener una longitu mínima de 23 carácteres.");
		PO_RegisterView.checkElement(driver, "text", "El nombre debe de tener una longitu mínima de 23 carácteres.");
		PO_RegisterView.checkElement(driver, "text", "El apellido debe de tener una longitu mínima de 23 carácteres.");
		PO_RegisterView.checkElement(driver, "text",
				"La contraseña debe de tener una longitu mínima de 23 carácteres.");

		// Nos desconectamos
		elementos = PO_View.checkElement(driver, "free", "//*[@id=\"myNavbar\"]/ul[2]/li[2]/a");
		elementos.get(0).click();
	}

	// PR03. Intentamos acceder con un rol equivocado
	@Test
	public void PR03() {

		// Nos logeamos como administrador
		PO_NavView.loginAs(driver, "99999990A", "123456");

		// Vamos al formulario para agregar a un usuario
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Gestión de usuarios", PO_View.getTimeout());

		// Nos desconectamos
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//*[@id=\"myNavbar\"]/ul[2]/li[2]/a");
		elementos.get(0).click();
	}

	// PR04. Comprobamos que al listar los profesores, se muestren todos los
	// de nuestro sistema
	@Test
	public void PR04() {

		// Nos logeamos como administrador
		PO_NavView.loginAs(driver, "99999988F", "123456");

		// Accedenmos al listado de profesores
		List<WebElement> elementos = PO_View.checkElement(driver, "id", "teachers-menu");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "@href", "/teacher/list");
		elementos.get(0).click();

		// Comprobamos que estan los 6 profesores del sistema
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 6);

		// Nos desconectamos
		elementos = PO_View.checkElement(driver, "free", "//*[@id=\"myNavbar\"]/ul[2]/li[2]/a");
		elementos.get(0).click();
	}

}
