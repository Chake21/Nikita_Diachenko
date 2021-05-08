package ru.training.at.hw1;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestDivide extends Calc {

    @BeforeMethod
    public void beforeMethod() {
        Calculator calculator = new Calculator();
    }

    @Test(dataProvider = "divideDataProvided", dataProviderClass = DataProviders.class,
            groups = "MultiplyAndDivide")

    public void divTest(long actual, long expected) {
        Assert.assertEquals(actual, expected);
        System.out.println("TestDivide completed");
    }
}