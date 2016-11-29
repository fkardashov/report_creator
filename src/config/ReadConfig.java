package config;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadConfig {
    public ReadConfig() {
    }

    private  String readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("config.json"));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

    public Model ParceConfig() throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(readFile(), Model.class);
    }
}
