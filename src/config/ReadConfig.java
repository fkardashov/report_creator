package config;

import com.google.gson.Gson;

import java.io.*;

public class ReadConfig {
    public ReadConfig() {
    }

    private  String readFile(String fileName) throws IOException {

        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))){
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        }
    }

    public Config ParceConfig(String fileName) throws IOException {
        Gson gson = new Gson();

        return gson.fromJson(readFile(fileName), Config.class);
    }
}
