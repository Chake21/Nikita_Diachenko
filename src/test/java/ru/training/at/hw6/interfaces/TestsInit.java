package ru.training.at.hw6.interfaces;

import org.testng.annotations.BeforeSuite;
import ru.training.at.hw6.pages.JdiSite;

import static com.epam.jdi.light.elements.init.PageFactory.initElements;

public interface TestsInit {
    @BeforeSuite(alwaysRun = true)
    static void setUp() {
        initElements(JdiSite.class);
    }
}