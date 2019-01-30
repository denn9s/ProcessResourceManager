import java.util.ArrayList;
import java.util.HashMap;

public class Resource {
    String resourceID;
    int freeUnits; // u
    int maxUnits; // k
    ArrayList<Process> processList;
    ArrayList<Process> waitingList;
    HashMap<Process, Integer> processUnitMap;
    HashMap<Process, Integer> processRequestMap;

    public Resource(String rid, Integer units) {
        this.resourceID = rid;
        this.freeUnits = units;
        this.maxUnits = units;
        this.processList = new ArrayList<Process>;
        this.waitingList = new ArrayList<Process>;
        this.processUnitMap = new HashMap<Process, Integer>();
        this.processRequestMap = new HashMap<Process, Integer>();
    }

    public int getFreeUnits() {
        return this.freeUnits;
    }

    public int getMaxUnits() {
        return this.maxUnits;
    }
}
