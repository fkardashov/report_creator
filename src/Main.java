import config.*;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import util.SaveImage;

import java.io.IOException;
import java.text.ParseException;

public class Main {


    public static void main(String[] args) throws InterruptedException, IOException, InvalidFormatException, ParseException {

        Model model = new ReadConfig().ParceConfig();

        String url;
        for (Image image : model.getImages()) {
            url = model.getGrafanaURL() + image.getRenderPath() + "?from="
                    + model.getStartDateMills() + "&to=" + model.getEndDateMills()
                    + "&panelId=" + image.getPanelId() + "&width=" + model.getWidth() + "&height=" + model.getHeight();
            System.out.println(url);
            new SaveImage(url,image.getServerName()).GetImage();
        }

        //docProcess doc = new docProcess();
    }
}
