import java.util.Map;
import java.util.HashMap;

public class Schedule{
    private Map<String, int[]> listOfDays;
    
    public Schedule(){
        listOfDays = new HashMap<String, int[]>();
    }
    
    public void addTime(String day, int st, int et){
        int[] times = {st,et};
        listOfDays.put(day, times);
    }
}