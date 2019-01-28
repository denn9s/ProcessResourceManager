import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Shell {
    public static void main(String[] args) {
        File inputFile = new File("input.txt");
        PriorityList priorityList = new PriorityList();
        Process currentProcess = priorityList.nextProcess();
        HashMap<String, Process> processMap = new HashMap<String, Process>();
        processMap.put(currentProcess.getPID(), currentProcess);
        try {
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String command = scanner.next();
                if (command.equals("init")) {

                } else if (command.equals("cr")) {
                    String processName = scanner.next();
                    int priority = Integer.parseInt(scanner.next());
                    if (priority == 1 || priority == 2) {
                        if (processMap.containsKey(processName) == false) {
                            Process process = new Process(processName, currentProcess, priority, priorityList.getSize(priority));
                            priorityList.createProcess(process);
                            processMap.put(process.getPID(), process);
                            currentProcess.addChild(process);
                            if (priority > currentProcess.priority) {
                                priorityList.createProcess(currentProcess);
                                currentProcess = priorityList.nextProcess();
                            }
                        }
                        else {
                            // error
                        }
                    } else {
                        // error
                    }
                } else if (command.equals("de")) {
                    String processName = scanner.next();
                } else if (command.equals("req")) {
                    String resourceName = scanner.next();
                    int unitCount = Integer.parseInt(scanner.next());
                } else if (command.equals("rel")) {
                    String resourceName = scanner.next();
                    int unitCount = Integer.parseInt(scanner.next());
                } else if (command.equals("to")) {
                    priorityList.createProcess(currentProcess);
                    currentProcess = priorityList.nextProcess();
                } else {
                    
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
