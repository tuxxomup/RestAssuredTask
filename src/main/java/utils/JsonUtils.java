package utils;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JsonUtils {

    static public void assertEquals(String expected, String actual) throws JSONException {
          JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
    }
}
