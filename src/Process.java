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

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPID(String pid) {
        this.processID = pid;
    }

    public String getPID() {
        return this.processID;
    }

    public ArrayList<Process> getCreationTreeChildren() {
        return creationTreeChildren;
    }

    public void addChild(Process process) {
        creationTreeChildren.add(process);
    }

    public Process removeChild(Process process) {
        if (process == null) {
            return null;
        } else {
            if (creationTreeChildren.remove(process) == true) {
                return process;
            } else {
                ArrayList<Process> newChildren = new ArrayList<Process>();
                newChildren.addAll(creationTreeChildren);
                for (Process child : newChildren) {
                    newChildren.addAll(child.getCreationTreeChildren());
                }
                if (newChildren.contains(process)) {
                    return process;
                }
            }
        }
        return null;
    }

    public void addResource(String resourceID) {
        otherResources.add(resourceID);
    }

    public void removeResource(String resourceID) {
        otherResources.remove(resourceID);
    }
}
