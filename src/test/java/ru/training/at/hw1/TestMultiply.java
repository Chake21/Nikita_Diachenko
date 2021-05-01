package ru.training.at.hw1;
import com.epam.tat.module4.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class TestMultiply {
    Calculator calculator = new Calculator();
    @DataProvider
    public Object[][] multiplyDataProvided() {
        return new Object[][]{
                {6, calculator.mult(2, 3)},
                {8, calculator.mult(2, 4)},
                {36, calculator.mult(6, 6)},
                {42, calculator.mult(6, 7)},
                {18, calculator.mult(3, 6)}
        };
    }

    @Test(dataProvider = "multiplyDataProvided",groups = "MultiplyAndDivide")
    public void mult(long actual, long expected) {

        Assert.assertEquals(actual, expected);
        System.out.println("TestMultiply completed");
    }
}