package config;

import com.google.gson.Gson;

import java.io.*;

public class ReadConfig {
    public ReadConfig() {
    }

    private  String readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName)));
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

    public Config ParceConfig(String fileName) throws IOException {
        Gson gson = new Gson();

        return gson.fromJson(readFile(fileName), Config.class);
    }
}
