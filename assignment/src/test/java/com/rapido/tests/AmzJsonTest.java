package com.rapido.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rapido.pageobjects.AmazonPage;
import com.rapido.pageobjects.BaseClass;
import com.rapido.utilities.ReadPropertyFile;

public class AmzJsonTest extends BaseClass {

	Logger log = Logger.getLogger(AmzJsonTest.class.getName());
	JSONObject json = new JSONObject();
	ReadPropertyFile read;
	HashMap<String, String> desc = new HashMap<String, String>();

	public AmzJsonTest() {
		super();
	}

	@BeforeTest
	public void before() throws IOException {
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		read = new ReadPropertyFile();
		driver = BaseClass(read);

	}

	@Test
	public void AmazonSearchTest() throws IOException {
		AmazonPage aloginPage = new AmazonPage();
		getUrl(driver, read.returnPropertyValue("amzon_url"));
		// Search Amazon site and get price
		log.info("AmazonFlipkartPage Object Created");
		desc = aloginPage.AmazonSearchItemAndGetPriceAndDesc(driver, "iphone xr 64gb - yellow");
		GsonBuilder gbuilder = new GsonBuilder();
		Gson gson = gbuilder.create();
		String jsonResult = gson.toJson(desc);
		System.out.println(jsonResult);
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		log.info("Item searched in Amazon and got the price." + prettyGson.toJson(jsonResult));
		System.out.println("Item searched in Amazon and got the price." + prettyGson.toJson(jsonResult));
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

	@AfterTest
	public void afterTest() {
	}

}
