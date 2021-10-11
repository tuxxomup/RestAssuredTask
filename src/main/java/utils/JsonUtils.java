package utils;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

public class JsonUtils {

    static public boolean assertEquals(String expected, String actual) throws JSONException {
        return JsonUtils.assertEquals(expected, actual,
            new CustomComparator(JSONCompareMode.LENIENT));
    }

    static boolean assertEquals(String expected, String actual, CustomComparator customComparator)
        throws JSONException {
        try {
            JSONAssert.assertEquals(expected,
                actual,
                customComparator);
        } catch (AssertionError e) {
            throw e;
        } catch (JSONException e) {
            throw e;
        }
        return true;
    }
}
