package com.foundation.stepDefinitions.website;

import com.foundation.utils.StepUtils;
import com.foundation.utils.Utils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.ArrayList;

public class HomePageSteps extends StepUtils{

    @Given("^I navigated to home page$")
    public void i_navigated_to_home_page() throws Throwable {
        log.info("Navigate to Home Page");
    }

    @When("^I visit link in data source$")
    public void i_visit_link_in_data_source() throws Throwable {
       ArrayList<String> urlsInHomePage = Utils.getCellValueInRow("Links","HomePage", "URL");
       log.info("Urls founrs are "+ urlsInHomePage +" are");
    }

    @And("^I read url from data source$")
    public void i_read_url_from_data_source() throws Throwable {

    }

    @And("^I write link details to data source on page$")
    public void i_write_link_details_to_data_source_on_page() throws Throwable {

    }

    @Given("^I am page$")
    public void i_am_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
       System.out.println("I also Ran");
    }


}
