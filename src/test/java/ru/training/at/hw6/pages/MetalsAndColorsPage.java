package ru.training.at.hw6.pages;

import com.epam.jdi.light.elements.composite.WebPage;
import org.openqa.selenium.WebElement;
import ru.training.at.hw6.entities.MetalsAndColorsData;
import ru.training.at.hw6.forms.MetalsAndColorsForm;

import java.util.LinkedList;
import java.util.List;

public class MetalsAndColorsPage extends WebPage {


    MetalsAndColorsForm metalsAndColorsForm;

    public void fill(Object allData) {
        uncheckVegetables();
        MetalsAndColorsData newData = (MetalsAndColorsData) allData;
        MetalsAndColorsForm.colors.select(newData.getColor());

        for (Object f : newData.getVegetables()) {
            MetalsAndColorsForm.vegetables.select(f.toString());
        }

        MetalsAndColorsForm.metals.select(newData.getMetals());
        for (Object f : newData.getElements()) {
            MetalsAndColorsForm.elements.select(f.toString());
        }

        for (Object f : newData.evenDigitsOfSummary()) {
            MetalsAndColorsForm.oddButtons.select((int) f);
        }

        for (Object f : newData.oddDigitsOfSummary()) {
            MetalsAndColorsForm.evenButtons.select((int) f);
        }

        MetalsAndColorsForm.submitButton.click();
        MetalsAndColorsForm.metals.selected();
        MetalsAndColorsForm.elements.selected();
        MetalsAndColorsForm.colors.selected();

        for (Object f : newData.getElements()) {
            MetalsAndColorsForm.elements.select(f.toString());
        }

        for (Object f : newData.getVegetables()) {
            MetalsAndColorsForm.vegetables.select(f.toString());
        }

    }

    public void uncheckVegetables() {
        MetalsAndColorsForm.vegetables.select("Vegetables");
    }

    public List<String> getActualResults() {
        List<String> actualResults = new LinkedList<>();
        for (WebElement element : MetalsAndColorsForm.textResults) {
            actualResults.add(element.getText());
        }
        return actualResults;
    }

    public List<String> getExpectedResults(Object allData) {
        MetalsAndColorsData newData = (MetalsAndColorsData) allData;
        List<String> expectedResultsList = new LinkedList<>();
        expectedResultsList.add(String.format("Summary: %d", newData.sumOfSummary()));
        expectedResultsList.add(String.format("Elements: %s", newData.getElements().toString()
                .replaceAll("[\\[\\]]", "")));
        expectedResultsList.add(String.format("Color: %s", newData.getColor()));
        expectedResultsList.add(String.format("Metal: %s", newData.getMetals()));
        expectedResultsList.add(String.format("Vegetables: %s", newData.getVegetables().toString()
                .replaceAll("[\\[\\]]", "")));
        return expectedResultsList;
    }


}
