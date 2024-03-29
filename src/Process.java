import java.util.ArrayList;

public class Process {
    String processID;
    ArrayList<String> otherResources;
    int priority;
    Process creationTreeParent;
    ArrayList<Process> creationTreeChildren;

    public Process(String pid, Process parent, int priority, int index) {
        this.processID = pid;
        this.otherResources = new ArrayList<>();
        this.priority = priority;
        if (parent == null) {
            this.creationTreeParent = this;
        } else {
            this.creationTreeParent = parent;
        }
        this.creationTreeChildren = new ArrayList<>();
    }

    public ArrayList<Process> getCreationTreeChildren() {
        return this.creationTreeChildren;
    }

    public void addChild(Process process) {
        this.creationTreeChildren.add(process);
    }

    public Process removeChild(Process process) {
        if (process == null) {
            return null;
        } else {
            if (this.creationTreeChildren.remove(process) == true) {
                return process;
            } else {
                ArrayList<Process> newChildren = new ArrayList<>();
                newChildren.addAll(this.creationTreeChildren);
                for (int i = 0; i < newChildren.size(); i++) {
                    newChildren.addAll(newChildren.get(i).getCreationTreeChildren());
                }
                // kept throwing ConcurrentModificationException
                // for (Process child : newChildren) {
                //     newChildren.addAll(child.getCreationTreeChildren());
                // }
                if (newChildren.contains(process)) {
                    return process;
                }
            }
        }
        return null;
    }

    public void addResource(String resourceID) {
        this.otherResources.add(resourceID);
    }

    public void removeResource(String resourceID) {
        this.otherResources.remove(resourceID);
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
}
