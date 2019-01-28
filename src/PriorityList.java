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
}
