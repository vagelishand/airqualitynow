package devterous.com.aqn;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.devterous.aqn.json.ForecastJsonProvider;
import com.devterous.aqn.model.Forecast;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }



}