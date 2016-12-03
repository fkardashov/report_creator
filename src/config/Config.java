package config;

import com.fasterxml.jackson.databind.util.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Config {
    private String systemName;
    private String testName;
    private String grafanaURL;
    private String startDate;
    private String endDate;
    private String width;
    private String height;

    public String getSystemName() {return systemName;}
    public String getTestName() {return testName;}
    public String getGrafanaURL() {return grafanaURL;}
    public String getStartDate() {return startDate;    }
    public String getEndDate() {return endDate;}
    public String getWidth() {return width;}
    public String getHeight() {return height;}
    public List<Image> getImages() {return Images;}
    private List<Image> Images = new ArrayList<Image>();

    public void setEndDate(String endDate) {this.endDate = endDate;}
    public void setStartDate(String startDate) { this.startDate = startDate;}

    public void setTestName(String testName) { this.testName = testName; }

    public String getStartDateMills() throws ParseException { return getMills(startDate);}
    public String getEndDateMills() throws ParseException { return getMills(endDate);}

    private String getMills( String jdate) throws ParseException {
        ISO8601DateFormat df = new ISO8601DateFormat();
        return Long.toString(df.parse(jdate).getTime());
    }

    public static String getImageUrl(Config config, Image image) {
        try {
            return config.getGrafanaURL() + image.getRenderPath() + "?from="
                    + config.getStartDateMills() + "&to=" + config.getEndDateMills()
                    + "&panelId=" + image.getPanelId() + "&width=" + config.getWidth() + "&height=" + config.getHeight();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFileName(){
        return getSystemName() + "_" + getTestName() + "_"
                + getStartDate().substring(0, 19).replace(':', '_') + ".docx";
    }

    public String getFileFolder() {
        if (System.getProperty("os.name").contains("Windows")) return "C:\\Users\\sbt-kardashov-fm\\";
        if (System.getProperty("os.name").contains("unix")) return "/home/wasadmin/reports/";

        return "";
    }
}



