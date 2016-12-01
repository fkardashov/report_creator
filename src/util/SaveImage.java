package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class SaveImage {
    private final String renderUrl;
    private final String ioPath;

    public SaveImage(String url) {
        renderUrl = url;
        ioPath = "/tmp";
    }

    public SaveImage(String url, String path) {
        renderUrl = url;
        ioPath =  "/home/wasadmin/tmp/" + path + ".png";
    }

    public void GetImage() throws IOException {

        URL url = new URL(renderUrl);
        URLConnection uc = (HttpURLConnection) url.openConnection();
        uc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");
        uc.addRequestProperty("Cookie", "grafana_sess=f24439ac975f4bae; grafana_user=admin; grafana_remember=0e85e349e60a13dc5c48c02c016749173b548fb38e7be5d4");
        uc.connect();
        InputStream is = uc.getInputStream();
        OutputStream out = new FileOutputStream(ioPath);

        byte[] b = new byte[10000];
        int length;

        while ( (length = is.read(b)) != -1){
            out.write(b, 0, length);
        }

        is.close();
        out.close();
    }

    public InputStream GetImageInputStream() throws IOException {

        URL url = new URL(renderUrl);
        URLConnection uc = (HttpURLConnection) url.openConnection();
        uc.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36");
        uc.addRequestProperty("Cookie", "grafana_sess=f24439ac975f4bae; grafana_user=admin; grafana_remember=0e85e349e60a13dc5c48c02c016749173b548fb38e7be5d4");
        uc.connect();
        return uc.getInputStream();
    }

}
