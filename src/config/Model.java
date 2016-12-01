package config;

import com.fasterxml.jackson.databind.util.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Model {
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

    public String getStartDateMills() throws ParseException { return getMills(startDate);}
    public String getEndDateMills() throws ParseException { return getMills(endDate);}

    private String getMills( String jdate) throws ParseException {
        ISO8601DateFormat df = new ISO8601DateFormat();
        return Long.toString(df.parse(jdate).getTime());
    }

    public static String getImageUrl(Model model, Image image) {
        try {
            return model.getGrafanaURL() + image.getRenderPath() + "?from="
                    + model.getStartDateMills() + "&to=" + model.getEndDateMills()
                    + "&panelId=" + image.getPanelId() + "&width=" + model.getWidth() + "&height=" + model.getHeight();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}


