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
                if (command.equals("cr")) {
                    String processName = scanner.next();
                    int priority = Integer.parseInt(scanner.next());
                } else if (command.equals("de")) {
                    String processName = scanner.next();
                } else if (command.equals("req")) {
                    String resourceName = scanner.next();
                    int unitCount = Integer.parseInt(scanner.next());
                } else if (command.equals("rel")) {
                    String resourceName = scanner.next();
                    int unitCount = Integer.parseInt(scanner.next());
                } else if (command.equals("to")) {

                } else {
                    
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
