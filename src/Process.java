import java.util.ArrayList;

public class Process {
    String processID;
    ArrayList<Integer> otherResources;
    int priority;
    Process creationTreeParent;
    ArrayList<Process> creationTreeChildren;

    public Process(String pid, Process parent, int priority, int index) {
        this.processID = pid;
        this.otherResources = new ArrayList<Integer>();
        this.priority = priority;
        if (parent == null) {
            this.creationTreeParent = this;
        } else {
            this.creationTreeParent = parent;
        }
        this.creationTreeChildren = new ArrayList<Process>();
    }

    public int getPriority() {
        return this.priority;
    }
}
