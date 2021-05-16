package ru.training.at.hw1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSubtract extends Calc {

    @Test(dataProvider = "subtractDataProvided", dataProviderClass = DataProviders.class,
            groups = "SubtractAndAddTests.xml")
    public void subTest(long actual, long expected) {
        Assert.assertEquals(actual, expected);
        System.out.println("TestSubtract completed");
    }
}
