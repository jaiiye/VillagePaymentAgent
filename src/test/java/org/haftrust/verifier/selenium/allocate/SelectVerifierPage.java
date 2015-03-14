package org.haftrust.verifier.selenium.allocate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class SelectVerifierPage {

    private final WebDriver driver;

    @FindBy(css = "select[name='idVerifier']")
    private WebElement verifierSelection;

    @FindBy(css = "input[name='_target4']")
    private WebElement cancelButton;

    @FindBy(css = "input[name='_target1']")
    private WebElement backButton;

    @FindBy(css = "input[name='_target3']")
    private WebElement searchButton;

    public SelectVerifierPage(final WebDriver driver) {
        this.driver = driver;

        if (!driver.getCurrentUrl().endsWith("/allocateDevice.htm")
                || !driver.getTitle().equals("HafTrust Allocate Mobile Device - Select Verifier Page")) {
            throw new IllegalStateException("Incorrect page " + driver.getCurrentUrl());
        }
    }

    public SelectVerifierPage selectVerifier(final String text) {
        new Select(verifierSelection).selectByVisibleText(text);
        return this;
    }

    public Set<String> verifiers() {
        return new Select(verifierSelection).getOptions().stream().map(WebElement::getText).collect(toSet());
    }

    public CancelPage cancel() {
        cancelButton.click();
        return PageFactory.initElements(driver, CancelPage.class);
    }

    public SelectRegionPage back() {
        cancelButton.click();
        return PageFactory.initElements(driver, SelectRegionPage.class);
    }

    public SelectMobileDevicePage next() {
        searchButton.click();
        return PageFactory.initElements(driver, SelectMobileDevicePage.class);
    }

}
