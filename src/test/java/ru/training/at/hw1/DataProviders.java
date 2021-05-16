package ru.training.at.hw1;

import org.testng.annotations.DataProvider;

public class DataProviders extends Calc {

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
}
