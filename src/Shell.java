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
                if (command == "cr") {
                    String processName = scanner.next();
                    int priority = Integer.parseInt(scanner.next());
                } else if (command == "de") {
                    String processName = scanner.next();
                } else if (command == "req") {
                    String resourceName = scanner.next();
                    int unitCount = Integer.parseInt(scanner.next());
                } else if (command == "rel") {
                    String resourceName = scanner.next();
                    int unitCount = Integer.parseInt(scanner.next());
                } else if (command == "to") {

                } else {
                    
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
