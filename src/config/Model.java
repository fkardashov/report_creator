package config;

import java.util.ArrayList;
import java.util.List;

public class Model {
    public String getGrafanaURL() {
        return grafanaURL;
    }

    public String getSystemName() {
        return systemName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDb() {
        return db;
    }

    public List<Server> getServers() {
        return Servers;
    }

    private String grafanaURL;
    private String systemName;
    private String startDate;
    private String endDate;
    private String db;

    public List<Server> Servers = new ArrayList<Server>();
}


