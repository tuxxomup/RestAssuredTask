package utils;

import java.io.File;

public class FileUtils {

    public static File getJsonSchema(String schemaString){
        return new File(System.getProperty("user.dir") + "/src/main/resources/json-schema/" + schemaString);
    }

}
