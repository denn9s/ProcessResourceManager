import java.util.ArrayList;

public class Process {
    String processID;
    ArrayList<String> otherResources;
    int priority;
    Process creationTreeParent;
    ArrayList<Process> creationTreeChildren;

    public Process(String pid, Process parent, int priority, int index) {
        this.processID = pid;
        this.otherResources = new ArrayList<String>();
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

    public String getPID() {
        return this.processID;
    }

    public void addChild(Process process) {
        creationTreeChildren.add(process);
    }

    public void addResource(String resourceID) {
        otherResources.add(resourceID);
    }

    public void removeResource(String resourceID) {
        otherResources.remove(resourceID);
    }
}
