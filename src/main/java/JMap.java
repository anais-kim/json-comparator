import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JMap extends HashMap {

    public boolean isExpected() {
        return isExpected;
    }

    private final boolean isExpected;

    public JMap(){
        super();
        this.isExpected = true;
    }
    public JMap(Map map, boolean isExpected) {
        this.isExpected = isExpected;
        Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
        while(iter.hasNext()){
            Entry<String, Object> e = iter.next();
            String key = e.getKey();
            Object value = e.getValue();
            if(value instanceof Map) {
                this.put(key, new JMap((Map)value, isExpected));
            }else if(value instanceof ArrayList){
                this.put(key, new JArrayList((ArrayList)value, isExpected));
            }else{
                this.put(key, value);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof JMap)){
            return false;
        }
        JMap expected = this;
        JMap actual = (JMap)o;
        if(actual.isExpected){
            expected = (JMap)o;
            actual = this;
        }
        return actual.entrySet().containsAll(expected.entrySet());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, Object>> iter = this.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, Object> entry = iter.next();
            sb.append(entry.getKey());
            sb.append('=').append('"');
            sb.append(entry.getValue());
            sb.append('"');
            if (iter.hasNext()) {
                sb.append(',').append(' ');
            }
        }
        return sb.toString();
    }
}
