package ru.training.at.hw6;

import com.epam.jdi.light.driver.WebDriverUtils;
import com.epam.jdi.light.elements.init.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ru.training.at.hw6.interfaces.TestsInit;
import ru.training.at.hw6.pages.JdiSite;

import static ru.training.at.hw6.entities.User.ROMAN;
import static ru.training.at.hw6.pages.JdiHomePage.*;

public class JdiSiteTest implements TestsInit {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        PageFactory.initSite(JdiSite.class);
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        WebDriverUtils.killAllSeleniumDrivers();
    }


    @Test(dataProvider = "jsonData", dataProviderClass = DataProviderClass.class)
    public void jdiSiteTesting(Object allData) {
        JdiSite.open();
        JdiSite.jdiHomePage.login(ROMAN);
        userName.is().text(ROMAN.getFullName());
        JdiSite.openHeaderMenu();
        JdiSite.openMetalsPage();
        JdiSite.checkMetalsIsOpened();
        JdiSite.fillMetalsAndColors(allData);
        Assert.assertEquals(JdiSite.getActualResults(), JdiSite.getExpectedResults(allData));
        JdiSite.exit();


    }
}
