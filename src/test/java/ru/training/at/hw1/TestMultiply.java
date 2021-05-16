package ru.training.at.hw1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestMultiply extends Calc {

    @Test(dataProvider = "multiplyDataProvided", dataProviderClass = DataProviders.class,
            groups = "MultiplyAndDivide")
    public void multTest(long actual, long expected) {

        Assert.assertEquals(actual, expected);
        System.out.println("TestMultiply completed");
    }
}