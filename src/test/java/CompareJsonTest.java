import org.junit.Test;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class CompareJsonTest {

    @Test
    public void compareJson() throws Exception {
        JMap expected = JConverter.from(getResource("expected.json"), true);
        JMap actual = JConverter.from(getResource("expected.json"), false);

        assertTrue(actual.entrySet().containsAll(expected.entrySet()));
    }

    static URL getResource(String path) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResource(path);
    }
}
