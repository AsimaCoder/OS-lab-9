import java.util.Scanner;
import java.util.Arrays;

class Process3 {
    int id;
    int arrivalTime;
    int burstTime;
    int priority;

    public Process3(int id, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class PriorityScheduling {
    public static void simulate() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = input.nextInt();

        // Create an array to store the processes
        Process3[] processes = new Process3[numProcesses];

        // Input process information
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            int arrivalTime = input.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = input.nextInt();
            System.out.print("Enter priority for process " + (i + 1) + ": ");
            int priority = input.nextInt();

            processes[i] = new Process3(i + 1, arrivalTime, burstTime, priority);
        }

        // Sort processes by priority (lower values have higher priority)
        Arrays.sort(processes, (a, b) -> a.priority - b.priority);

        int currentTime = 0;
        double totalWaitingTime = 0;

        System.out.println("\nProcess execution order:");
        for (Process3 p : processes) {
            // Check if the process has arrived
            if (p.arrivalTime > currentTime) {
                currentTime = p.arrivalTime;
            }

            // Execute the process
            System.out.println("Executing process " + p.id + " from time " + currentTime + " to " + (currentTime + p.burstTime));
            currentTime += p.burstTime;

            // Calculate waiting time
            int waitingTime = currentTime - p.arrivalTime - p.burstTime;
            totalWaitingTime += waitingTime;
        }

        double averageWaitingTime = totalWaitingTime / numProcesses;
        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);

        input.close();
    }
}

