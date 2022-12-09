/*
 * Copyright (c) 2022 Anh Tester
 * Automation Framework Selenium
 */

package ducmin.com.projects.website.crm.testcases;

import ducmin.com.common.BaseTest;
import ducmin.com.dataprovider.DataProviderManager;
import ducmin.com.keyword.WebUI;
import ducmin.com.projects.website.crm.pages.SignIn.SignInPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

@Epic("Regression Test CRM")
@Feature("Sign In Test")
public class SignInTest extends BaseTest {

    public SignInTest() {
        signInPage = new SignInPage();
    }

    //Using library DataProvider with read Hashtable
    @Test(priority = 1, dataProvider = "getSignInDataHashTable", dataProviderClass = DataProviderManager.class)
    @Step("signInTestDataProvider")
    public void signInTestDataProvider(Hashtable<String, String> data) {
        signInPage.signIn(data);

    }

    @Test(priority = 2)
    @Step("signInTestAdminRole")
    public void signInTestAdminRole() {
        signInPage.signInWithAdminRole();
        WebUI.verifyElementPresent(getDashboardPage().menuDashboard, 5, "The menu Dashboard does not exist.");

    }

    @Test(priority = 3)
    @Step("signInTestClientRole")
    public void signInTestClientRole() {
        signInPage.signInWithClientRole();
        Assert.assertEquals(WebUI.getPageTitle(), "Anh Tester");
    }

}
