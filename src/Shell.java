import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Shell {
    public static void main(String[] args) {
        File inputFile = new File("input.txt");
        PriorityList priorityList = new PriorityList();
        ResourceList resourceList = new ResourceList();
        Process currentProcess = priorityList.nextProcess();
        Resource currentResource;
        HashMap<String, Process> processMap = new HashMap<String, Process>();
        processMap.put(currentProcess.getPID(), currentProcess);
        try {
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                if (priorityList.getSize(1) == 0) {
                    if (priorityList.getSize(2) == 0) {
                        break;
                    }
                }

                String command = scanner.next();
                /*
                INIT COMMAND
                 */
                if (command.equals("init")) {
                    currentProcess = priorityList.nextProcess();
                    priorityList = new PriorityList();
                    resourceList = new ResourceList();
                }

                /*
                CR (CREATE) COMMAND
                 */
                else if (command.equals("cr")) {
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
                            System.out.println("error"); // error
                        }
                    } else {
                        System.out.println("error"); // error
                    }
                }
                /*
                DE (DESTROY) COMMAND
                 */
                else if (command.equals("de")) {
                    String processName = scanner.next();
                }

                /*
                REQ (REQUEST) COMMAND
                 */
                else if (command.equals("req")) {
                    String resourceName = scanner.next();
                    int unitCount = Integer.parseInt(scanner.next());
                    currentResource = resourceList.getResource(resourceName);
                    boolean requestSuccess = false;
                    if (currentResource != null) {
                        if (unitCount > 0) {
                            if (unitCount <= currentResource.getFreeUnits()) {
                                if (!currentProcess.equals(priorityList.getInitProcess())) {
                                    boolean success = currentResource.request(currentProcess, unitCount);
                                    if (success == false) {
                                        priorityList.removeProcess(currentProcess);
                                        currentProcess = priorityList.nextProcess();
                                    } else {
                                        requestSuccess = true;
                                    }
                                }
                            }
                        }
                    }
                    if (requestSuccess == false) {
                        System.out.println("error");
                    }
                }
                /*
                REL (RELEASE) COMMAND
                 */
                else if (command.equals("rel")) {
                    String resourceName = scanner.next();
                    int unitCount = Integer.parseInt(scanner.next());
                }

                /*
                TO (TIMEOUT) COMMAND
                 */
                else if (command.equals("to")) {
                    priorityList.createProcess(currentProcess);
                    currentProcess = priorityList.nextProcess();
                }

                /*
                OTHER ERRORS
                 */
                else {

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
