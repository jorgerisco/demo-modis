package com.demo.modis.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
	plugin = {"pretty", "html:target/surefire-reports/get-prices.html"})
public class CucumberIntegrationTest {

}