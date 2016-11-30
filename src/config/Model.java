package config;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Model {
    private String systemName;
    private String testName;
    private String grafanaURL;
    private String startDate;
    private String endDate;
    private String width;
    private String height;

    public String getSystemName() {        return systemName;   }
    public String getTestName() {        return testName;    }
    public String getGrafanaURL() {        return grafanaURL;    }
    public String getStartDate() {        return startDate;    }
    public String getEndDate() {        return endDate;    }
    public String getWidth() {        return width;    }
    public String getHeight() {        return height;    }
    public List<Image> getImages() {        return Images;    }
    private List<Image> Images = new ArrayList<Image>();

    public String getStartDateMills() throws ParseException {        return Long.toString(getMills(startDate));   }
    public String getEndDateMills() throws ParseException {        return Long.toString(getMills(endDate));   }

    private long getMills( String jdate) throws ParseException {
        ISO8601DateFormat df = new ISO8601DateFormat();
        return df.parse(jdate).getTime();
        //long millisSinceEpoch = new DateTime( "2014-10-23T00:35:14.800Z" ).getMillis();
    }

}


