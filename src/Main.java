import com.google.gson.Gson;
import config.*;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws InterruptedException, IOException {

        Model model = new ReadConfig().ParceConfig();

        System.out.println(model.getSystemName());

        for (Server temp : model.Servers) {
            System.out.println(temp.getName());
        }
    }
}
