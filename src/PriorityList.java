import java.util.ArrayList;

public class PriorityList {
    // 0 = init, 1 = user, 2 = system
    Process initProcess;
    ArrayList<Process> userProcesses;
    ArrayList<Process> systemProcesses;

    public PriorityList() {
        this.initProcess = new Process("init", null, 0, -1);
        this.userProcesses = new ArrayList<>();
        this.systemProcesses = new ArrayList<>();
    }

    public void createProcess(Process process) {
        if (process.getPriority() == 0) {
            this.initProcess = process;
        } else if (process.getPriority() == 1) {
            this.userProcesses.add(process);
        } else if (process.getPriority() == 2) {
            this.systemProcesses.add(process);
        }
    }

    public void removeProcess(Process process) {
        if (process.getPriority() == 1) {
            this.userProcesses.remove(process);
        } else if (process.getPriority() == 2) {
            this.systemProcesses.remove(process);
        }
    }

    public Process nextProcess() {
        if (this.systemProcesses.size() > 0) {
            Process systemProcess = this.systemProcesses.get(0);
            this.systemProcesses.remove(0);
            return systemProcess;
        } else if (this.userProcesses.size() > 0) {
            Process userProcess = this.userProcesses.get(0);
            this.userProcesses.remove(0);
            return userProcess;
        } else {
            return initProcess;
        }
    }

    public int getSize(int priority) {
        if (priority == 1) {
            return this.userProcesses.size();
        } else if (priority == 2) {
            return this.systemProcesses.size();
        } else {
            return -1;
        }
    }

    public int getCurrentPriority() {
        if (this.systemProcesses.isEmpty() == true) {
            if (this.userProcesses.isEmpty() == true) {
                return 0;
            }
            return 1;
        }
        return 2;
    }

    public Process getInitProcess() {
        return this.initProcess;
    }
}
