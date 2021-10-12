package common;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class BaseURIBuilder {

    private static final List<String> envList = List.of("qaEnv", "autoEnv");

    public static String buildBaseEnvUrl() throws IOException {
        Properties props = new Properties();
        props.load(BaseURIBuilder.class.getClassLoader().getResourceAsStream("config.properties"));

        return props.getProperty(String.format("api.%s", checkTestRunProperty()));
    }

    private static String checkTestRunProperty() {

        String env = System.getProperty("env");
        if(env == null){
            throw new RuntimeException("Invalid entry for environment. Environment property can't be NULL");
        }
        else if (!envList.contains(env)){
            throw new RuntimeException("Invalid entry for environment. Set jUnit run configuration env property. Ex. '-Denv=qaEnv'/'-Denv=autoEnv'");
        }
        return env;
    }

}
