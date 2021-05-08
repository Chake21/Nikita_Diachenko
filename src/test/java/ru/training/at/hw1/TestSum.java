package ru.training.at.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSum extends Calc {

    @BeforeMethod
    public void beforeMethod() {
        Calculator calculator = new Calculator();
    }

    @Test(dataProvider = "sumDataProvided", dataProviderClass = DataProviders.class,
            groups = "SubtractAndAddTests.xml")
    public void sumTest(long actual, long expected) {
        Assert.assertEquals(actual, expected);
        System.out.println("TestSum completed");
    }
}

