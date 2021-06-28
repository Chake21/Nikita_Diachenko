package ru.training.at.hw6;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;
import ru.training.at.hw6.entities.MetalsAndColorsData;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class DataProviderClass {

    private final String jsonPath = "src/test/resources/JDI_ex8_metalsColorsDataSet.json";
    File json = new File(jsonPath);

    @DataProvider(name = "jsonData")
    public Object[][] getData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<HashMap<String, MetalsAndColorsData>> tr = new
                TypeReference<HashMap<String, MetalsAndColorsData>>() {
                };
        HashMap<String, MetalsAndColorsData> map = objectMapper.readValue(json, tr);
        int i = 0;
        Object[][] allData = new Object[map.size()][1];
        for (MetalsAndColorsData data : map.values()) {
            allData[i][0] = data;
            i++;
        }
        return allData;
    }
}

