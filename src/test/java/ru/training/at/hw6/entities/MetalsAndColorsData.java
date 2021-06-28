package ru.training.at.hw6.entities;

import java.util.ArrayList;

public class MetalsAndColorsData {

    public ArrayList getSummary() {
        ArrayList summaryList = new ArrayList();
        for (int i = 0; i < summary.length; i++) {
            summaryList.add(summary[i]);
        }
        return summaryList;
    }

    public ArrayList getElements() {
        ArrayList elementsList = new ArrayList();
        for (int i = 0; i < elements.length; i++) {
            elementsList.add(elements[i]);
        }
        return elementsList;
    }

    public int sumOfSummary() {
        int result = 0;
        for (int f : summary) {
            result = result + f;
        }
        return result;
    }

    public String getColor() {
        return color;
    }

    public String getMetals() {
        return metals;
    }

    public ArrayList getVegetables() {
        ArrayList vegetablesList = new ArrayList();
        for (int i = 0; i < vegetables.length; i++) {
            vegetablesList.add(vegetables[i]);
        }
        return vegetablesList;
    }

    public ArrayList evenDigitsOfSummary() {
        ArrayList evenDigits = new ArrayList();
        for (int i = 0; i < summary.length; i++) {
            if (summary[i] % 2 == 0) {
                evenDigits.add(summary[i] / 2);
            }
        }
        return evenDigits;
    }

    public ArrayList oddDigitsOfSummary() {
        ArrayList oddDigits = new ArrayList();
        for (int i = 0; i < summary.length; i++) {
            if (summary[i] % 2 != 0) {
                oddDigits.add((summary[i] + 1) / 2);
            }
        }
        return oddDigits;
    }

    private int[] summary;
    private String[] elements;
    private String color;
    private String metals;
    private String[] vegetables;

}
