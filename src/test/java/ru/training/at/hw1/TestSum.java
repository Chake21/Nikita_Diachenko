package ru.training.at.hw1;
import com.epam.tat.module4.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class TestSum {
    Calculator calculator = new Calculator();
    @DataProvider
    public Object[][] sumDataProvided() {
        return new Object[][]{
                {6, calculator.sum(2, 4)},
                {18, calculator.sum(-5, 23)},
                {228, calculator.sum(200, 28)},
                {42, calculator.sum(15, 27)},
                {18, calculator.sum(12, 6)}
        };
    }

    @Test(dataProvider = "sumDataProvided", groups = "AddAndSubtractTests")
    public void sum(long actual, long expected) {
        Assert.assertEquals(actual, expected);
        System.out.println("TestSum completed");
    }
}

