import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Shell {
    public static void main(String[] args) {
        File inputFile = new File("E:\\input.txt");
        File outputFile = new File("E:\\output.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(outputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PriorityList priorityList = new PriorityList();
        ResourceList resourceList = new ResourceList();
        Process currentProcess = priorityList.nextProcess();
        Process availableProcess;
        Resource currentResource;
        HashMap<String, Process> processMap = new HashMap<String, Process>();
        processMap.put(currentProcess.getPID(), currentProcess);
        boolean foundError = false;
        boolean newSet = false;
        boolean firstSet = true;
        try {

            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                foundError = false;
                String command = scanner.next();
                /*
                INIT COMMAND
                 */
                if (command.equals("init")) {
                    currentProcess = priorityList.nextProcess();
                    priorityList = new PriorityList();
                    resourceList = new ResourceList();
                    newSet = true;
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
                            if (priority > currentProcess.getPriority()) {
                                priorityList.createProcess(currentProcess);
                                currentProcess = priorityList.nextProcess();
                            }
                        }
                        else {
                            foundError = true;
                        }
                    } else {
                        foundError = true;
                    }
                }

                /*
                DE (DESTROY) COMMAND
                 */
                else if (command.equals("de")) {
                    String processName = scanner.next();
                    Process process = processMap.get(processName);
                    if (process != null) {
                        if (process.equals(currentProcess) == false) {
                            process = currentProcess.removeChild(process);
                        }
                    }

                    if (process != null) {
                        if (process.equals(priorityList.getInitProcess()) == false) {
                            processMap.remove(processName);
                            process.creationTreeParent.removeChild(process);
                            priorityList.removeProcess(process);
                            ArrayList<Process> children = process.getCreationTreeChildren();
                            children.add(process);
                            resourceList.remove(children);
                            availableProcess = resourceList.unblockProcess();
                            for (Process child : children) {
                                processMap.remove(child.getPID());
                            }
                            while (availableProcess != null) {
                                priorityList.createProcess(availableProcess);
                                availableProcess = resourceList.unblockProcess();
                            }
                            if (processMap.containsKey(currentProcess.getPID()) == false) {
                                currentProcess = priorityList.nextProcess();
                            } else if (currentProcess.getPriority() < priorityList.getCurrentPriority()) {
                                currentProcess = priorityList.nextProcess();
                            }

                        }
                    } else {
                        foundError = true;
                    }
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
                            if (unitCount <= currentResource.getMaxUnits()) {
                                if (!currentProcess.equals(priorityList.getInitProcess())) {
                                    requestSuccess = true;
                                    boolean success = currentResource.request(currentProcess, unitCount);
                                    if (success == false) {
                                        priorityList.removeProcess(currentProcess);
                                        currentProcess = priorityList.nextProcess();
                                    }
                                }
                            }
                        }
                    }
                    if (requestSuccess == false) {
                        foundError = true;
                    }
                }

                /*
                REL (RELEASE) COMMAND
                 */
                else if (command.equals("rel")) {
                    String resourceName = scanner.next();
                    int unitCount = Integer.parseInt(scanner.next());
                    currentResource = resourceList.getResource(resourceName);
                    boolean releaseSuccess = false;
                    if (currentProcess != null) {
                        if (unitCount > 0) {
                            if (unitCount <= currentResource.getMaxUnits()) {
                                if (unitCount <= currentResource.getProcessUnitMap().get(currentProcess)) {
                                    if (!currentProcess.equals(priorityList.getInitProcess())) {
                                        if (currentResource.getProcessUnitMap().containsKey(currentProcess)) {
                                            boolean success = currentResource.release(currentProcess, unitCount);
                                            availableProcess = resourceList.unblockProcess();
                                            while (true) {
                                                if (availableProcess == null) {
                                                    break;
                                                } else {
                                                    priorityList.createProcess(availableProcess);
                                                    availableProcess = resourceList.unblockProcess();
                                                }
                                            }
                                            if (currentProcess.getPriority() < priorityList.getCurrentPriority()) {
                                                priorityList.createProcess(currentProcess);
                                                currentProcess = priorityList.nextProcess();
                                            }
                                            releaseSuccess = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (releaseSuccess == false) {
                    }
                }

                /*
                TO (TIMEOUT) COMMAND
                 */
                else if (command.equals("to")) {
                    if (currentProcess == null) {
                        foundError = true;
                    } else {
                        priorityList.createProcess(currentProcess);
                        currentProcess = priorityList.nextProcess();
                    }
                }

                /*
                OTHER ERRORS
                 */
                else {
                    foundError = true;
                }

                if (firstSet == true) {
                    try {
                        fileOutputStream.write("init ".getBytes());
                        firstSet = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (newSet == true) {
                        try {
                            fileOutputStream.write("\ninit ".getBytes());
                            newSet = false;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (foundError == false) {
                            try {
                                fileOutputStream.write((currentProcess.getPID() + " ").getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                fileOutputStream.write(("error" + " ").getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
