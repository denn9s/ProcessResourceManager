import java.util.ArrayList;

public class PriorityList {
    // 0 = init, 1 = user, 2 = system
    Process initProcess;
    ArrayList<Process> userProcesses;
    ArrayList<Process> systemProcesses;

    public PriorityList() {
        initProcess = new Process("init", null, 0, -1);
        userProcesses = new ArrayList<Process>();
        systemProcesses = new ArrayList<Process>();
    }

    public void createProcess(Process process) {
        if (process.getPriority() == 0) {
            initProcess = process;
        } else if (process.getPriority() == 1) {
            userProcesses.add(process);
        } else if (process.getPriority() == 2) {
            systemProcesses.add(process);
        }
    }

    public void removeProcess(Process process) {
        if (process.getPriority() == 1) {
            userProcesses.remove(process);
        } else if (process.getPriority() == 2) {
            systemProcesses.remove(process);
        }
    }

    public Process nextProcess() {
        if (systemProcesses.size() > 0) {
            Process systemProcess = systemProcesses.get(0);
            systemProcesses.remove(0);
            return systemProcess;
        } else if (userProcesses.size() > 0) {
            Process userProcess = userProcesses.get(0);
            userProcesses.remove(0);
            return userProcess;
        } else {
            return initProcess;
        }
    }
}
