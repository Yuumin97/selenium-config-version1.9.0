/*
 * Copyright (c) 2022 Anh Tester
 * Automation Framework Selenium
 */

package ducmin.com.projects.website.crm.testcases;

import ducmin.com.annotations.FrameworkAnnotation;
import ducmin.com.common.BaseTest;
import ducmin.com.dataprovider.DataProviderManager;
import ducmin.com.enums.AuthorType;
import ducmin.com.enums.CategoryType;
import ducmin.com.keyword.WebUI;
import ducmin.com.projects.website.crm.pages.SignIn.SignInPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import java.util.Hashtable;

@Epic("Regression Test CRM")
@Feature("Clients Test")
public class ClientTest extends BaseTest {

    public ClientTest() {
        signInPage = new SignInPage();
    }

    @FrameworkAnnotation(author = {AuthorType.AnhTester, AuthorType.Robert}, category = {CategoryType.REGRESSION})
    @Test(priority = 1, dataProvider = "getClientDataHashTable", dataProviderClass = DataProviderManager.class)
    @Step("Add new Client")
    public void testAddClient(Hashtable<String, String> data) {
        dashboardPage = signInPage.signInWithAdminRole();
        clientPage = dashboardPage.openClientPage();
        clientPage.openClientTabPage();
        clientPage.addClient(data);
    }

    @FrameworkAnnotation(author = {AuthorType.James}, category = {CategoryType.SANITY, CategoryType.REGRESSION})
    @Test(priority = 2)
    @Step("Search Client")
    public void testSearchClient() {
        dashboardPage = signInPage.signInWithAdminRole();
        clientPage = dashboardPage.openClientPage();
        clientPage.openClientTabPage();
        // Search the first
        clientPage.enterDataSearchClient("Schamberger Inc");
        WebUI.checkContainsValueOnTableByColumn(2, "Schamberger Inc");
        // Search the second
        clientPage.enterDataSearchClient("Kassulke LLC");
        WebUI.checkContainsValueOnTableByColumn(2, "Kassulke LLC");

    }

}