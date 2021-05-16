package ru.training.at.hw1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDivide extends Calc {

    @Test(dataProvider = "divideDataProvided", dataProviderClass = DataProviders.class,
            groups = "MultiplyAndDivide")

    public void divTest(long actual, long expected) {
        Assert.assertEquals(actual, expected);
        System.out.println("TestDivide completed");
    }
}