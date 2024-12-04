package com.selenium.common;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonDataReader {
    private JsonObject jsonData;

    public JsonDataReader(String filePath) throws IOException {
        FileReader reader = new FileReader(filePath);
        jsonData = JsonParser.parseReader(reader).getAsJsonObject();
        reader.close();
    }

    public String getData(String category, String key) {
        return jsonData.getAsJsonObject(category).get(key).getAsString();
    }
}
