package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SaveImage {
    private final String renderUrl;
    private final String ioPath;

    public SaveImage(String url) {
        renderUrl = url;
        ioPath = "/tmp";
    }

    public SaveImage(String url, String path) {
        renderUrl = url;
        ioPath = path;
    }

    public boolean GetImage(){
        BufferedImage image =null;
        try{

            URL url =new URL(renderUrl);
            image = ImageIO.read(url);
            ImageIO.write(image, "png",new File(ioPath));

        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
