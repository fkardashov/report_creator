import config.Model;
import config.ReadConfig;
import doc.docProcess;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException,
                ParseException, org.docx4j.openpackaging.exceptions.InvalidFormatException {

        Model model = new ReadConfig().ParceConfig("sochl_payments.json");

        docProcess doc = new docProcess();
        doc.createDoc(model);
    }
}
