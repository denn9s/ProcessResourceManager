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
        this.processList = new ArrayList<Process>();
        this.waitingList = new ArrayList<Process>();
        this.processUnitMap = new HashMap<Process, Integer>();
        this.processRequestMap = new HashMap<Process, Integer>();
    }

    public boolean request(Process process, int units) {
        if (units <= this.freeUnits) {
            this.freeUnits = this.freeUnits - units;
            processList.add(process);
            processUnitMap.put(process, units);
            return true;
        } else {
            waitingList.add(process);
            processRequestMap.put(process, units);
            return false;
        }
    }

    public boolean release(Process process, int units) {
        if (processUnitMap.containsKey(process)) {
            int totalUsedUnits = processUnitMap.get(process);
            if (units == totalUsedUnits && processList.remove(process) == true) {
                this.freeUnits = this.freeUnits + processUnitMap.remove(process);
            } else {
                processUnitMap.replace(process, totalUsedUnits - units);
                totalUsedUnits = units;
                freeUnits = freeUnits + totalUsedUnits;
            }
            return true;
        } else {
            return false;
        }
    }

    public Process unblock() {
        if (waitingList.isEmpty() == false) {
            int currentUnits = processUnitMap.get(waitingList.get(0));
            if (currentUnits <= freeUnits) {
                Process process = waitingList.remove(0);
                processList.add(process);
                processUnitMap.put(process, currentUnits);
                processRequestMap.remove(process);
                freeUnits = freeUnits - currentUnits;
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
            processRequestMap.remove(process);
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
