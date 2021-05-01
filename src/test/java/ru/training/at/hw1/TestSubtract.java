package ru.training.at.hw1;
import com.epam.tat.module4.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class TestSubtract {
    Calculator calculator = new Calculator();
    @DataProvider
    public Object[][] subtractDataProvided() {
        return new Object[][]{
                {6, calculator.sub(9, 3)},
                {10, calculator.sub(12, 2)},
                {128, calculator.sub(257, 129)},
                {-15, calculator.sub(5, 20)},
                {0, calculator.sub(5, 5)}
        };
    }

    @Test(dataProvider = "subtractDataProvided",groups = "AddAndSubtractTests")
    public void sub(long actual, long expected) {
        Assert.assertEquals(actual, expected);
        System.out.println("TestSubtract completed");
    }
}
