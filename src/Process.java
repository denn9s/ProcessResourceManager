import java.util.ArrayList;

public class Process {
    String processID;
    ArrayList<Integer> otherResources;
    int priority;

    public Process(String pid, Process parent, int priority, int index) {
        this.processID = pid;
        this.otherResources = new ArrayList<Integer>();
        this.priority = priority;
    }
}
