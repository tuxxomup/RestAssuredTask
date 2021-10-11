package common;

import java.io.IOException;
import java.util.Properties;

public class BaseURIBuilder {

    private static String testExecutionEnv;

    public static String buildBaseEnvUrl(String env) throws IOException {
        Properties props = new Properties();
        props.load(BaseURIBuilder.class.getClassLoader().getResourceAsStream("config.properties"));

        switch (env) {
            case "qaEnv":
                testExecutionEnv = "testEnvUri";
                break;
            case "autoEnv":
                testExecutionEnv = "automationTestEnvUri";
                break;
            default:
                throw new RuntimeException(
                    "Invalid entry for environment. Set jUnit run configuration env property. Ex. '-Denv=qaEnv'/'-Denv=autoEnv'");
        }

        return props.getProperty(String.format("api.%s", testExecutionEnv));
    }
}
