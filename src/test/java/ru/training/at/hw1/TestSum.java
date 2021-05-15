package ru.training.at.hw1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSum extends Calc {


    @Test(dataProvider = "sumDataProvided", dataProviderClass = DataProviders.class,
            groups = "SubtractAndAddTests.xml")
    public void sumTest(long actual, long expected) {
        Assert.assertEquals(actual, expected);
        System.out.println("TestSum completed");
    }
}

