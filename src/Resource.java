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
        this.processList = new ArrayList<>();
        this.waitingList = new ArrayList<>();
        this.processUnitMap = new HashMap<>();
        this.processRequestMap = new HashMap<>();
    }

    public boolean request(Process process, int units) {
        if (units <= this.freeUnits) {
            this.freeUnits = this.freeUnits - units;
            this.processList.add(process);
            this.processUnitMap.put(process, units);
            return true;
        } else {
            this.waitingList.add(process);
            this.processRequestMap.put(process, units);
            return false;
        }
    }

    public boolean release(Process process, int units) {
        if (this.processUnitMap.containsKey(process)) {
            int totalUsedUnits = this.processUnitMap.get(process);
            if (units == totalUsedUnits && this.processList.remove(process) == true) {
                this.freeUnits = this.freeUnits + this.processUnitMap.remove(process);
            } else {
                this.processUnitMap.replace(process, totalUsedUnits - units);
                totalUsedUnits = units;
                this.freeUnits = this.freeUnits + totalUsedUnits;
            }
            return true;
        } else {
            return false;
        }
    }

    public Process unblock() {
        if (this.waitingList.isEmpty() == false) {
            int currentUnits = this.processRequestMap.get(this.waitingList.get(0));
            if (currentUnits <= this.freeUnits) {
                Process process = this.waitingList.remove(0);
                this.processList.add(process);
                this.processUnitMap.put(process, currentUnits);
                this.processRequestMap.remove(process);
                this.freeUnits = this.freeUnits - currentUnits;
                return process;
            }
        }
        return null;
    }

    public void clear(ArrayList<Process> processList) {
        for (Process process : processList) {
            Integer unitCount = this.processUnitMap.remove(process);
            if (unitCount != null) {
                this.freeUnits = this.freeUnits + unitCount;
            }
            this.processRequestMap.remove(process);
        }
        this.processList.removeAll(processList);
        this.waitingList.removeAll(processList);
    }

    public String getResourceID() {
        return this.resourceID;
    }

    public int getFreeUnits() {
        return this.freeUnits;
    }

    public int getMaxUnits() {
        return this.maxUnits;
    }

    public ArrayList<Process> getProcessList() {
        return this.processList;
    }

    public ArrayList<Process> getWaitingList() {
        return this.waitingList;
    }

    public HashMap<Process, Integer> getProcessUnitMap() {
        return this.processUnitMap;
    }

    public HashMap<Process, Integer> getProcessRequestMap() {
        return this.processRequestMap;
    }
}
