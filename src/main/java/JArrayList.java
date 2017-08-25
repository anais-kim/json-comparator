import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JArrayList extends ArrayList{

    private final boolean isExpected;

    public JArrayList(ArrayList list, boolean isExpected) {
        this.isExpected = isExpected;
        List<Object> newList = new ArrayList();
        for(Object value : list){
            if(value instanceof Map) {
                newList.add(new JMap((Map)value, isExpected));
            }else if(value instanceof ArrayList){
                newList.add(new JArrayList((ArrayList)value, isExpected));
            }else{
                newList.add(value);
            }
        }
        super.addAll(newList);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof JArrayList)){
            return false;
        }
        JArrayList expected = this;
        JArrayList actual = (JArrayList)o;
        if(actual.isExpected){
            expected = (JArrayList)o;
            actual = this;
        }
        return actual.containsAll(expected);
    }
}
