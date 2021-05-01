package ru.training.at.hw1;
import com.epam.tat.module4.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class TestDivide {
    Calculator calculator = new Calculator();
    @DataProvider
    public Object[][] divideDataProvided() {
        return new Object[][]{
                {6, calculator.div(42, 7)},
                {8, calculator.div(16, 2)},
                {36, calculator.div(180, 5)},
                {42, calculator.div(168, 4)},
                {18, calculator.div(1800, 100)}
        };
    }

    @Test(dataProvider = "divideDataProvided",groups = "MultiplyAndDivide")
    public void div(long actual, long expected) {

        Assert.assertEquals(actual, expected);
        System.out.println("TestDivide completed");
    }
}