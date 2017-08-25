import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Map;

public class JConverter {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    public static JMap from(Path path, boolean isExpceted) throws IOException {
        Map map = mapper.readValue(path.toFile(), Map.class);
        return new JMap(map, isExpceted);
    }

    public static JMap from(URL url, boolean isExpceted) throws IOException {
        Map map = mapper.readValue(url, Map.class);
        return new JMap(map, isExpceted);
    }

    public static JMap from(String content, boolean isExpceted) throws IOException {
        Map map = mapper.readValue(content, Map.class);
        return new JMap(map, isExpceted);
    }

}
