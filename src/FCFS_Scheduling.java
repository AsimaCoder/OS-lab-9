import java.util.Scanner;

class Process {
    int id;
    int arrivalTime;
    int burstTime;

    public Process(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class FCFS_Scheduling {
    public static void simulate() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = input.nextInt();

        // Create an array to store the processes
        Process[] processes = new Process[numProcesses];

        // Input process information
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            int arrivalTime = input.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = input.nextInt();

            processes[i] = new Process(i + 1, arrivalTime, burstTime);
        }

        // Sort processes by arrival time
        java.util.Arrays.sort(processes, (a, b) -> a.arrivalTime - b.arrivalTime);

        // FCFS Scheduling
        int currentTime = 0;
        double totalWaitingTime = 0;

        System.out.println("\nProcess execution order:");
        for (Process p : processes) {
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
