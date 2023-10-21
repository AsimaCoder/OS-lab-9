import java.util.Scanner;
import java.util.Arrays;

class Process1 {
    int id;
    int arrivalTime;
    int burstTime;

    public Process1(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class SJN_Scheduling {
    public static void simulate() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = input.nextInt();

        // Create an array to store the processes
        Process1[] processes = new Process1[numProcesses];

        // Input process information
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            int arrivalTime = input.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = input.nextInt();

            processes[i] = new Process1(i + 1, arrivalTime, burstTime);
        }

        // Sort processes by arrival time
        Arrays.sort(processes, (a, b) -> a.arrivalTime - b.arrivalTime);

        // SJN Scheduling
        int currentTime = 0;
        double totalWaitingTime = 0;

        System.out.println("\nProcess execution order:");
        for (int i = 0; i < numProcesses; i++) {
            Process1 shortestJob = null;
            int shortestTime = Integer.MAX_VALUE;

            for (Process1 p : processes) {
                if (p.arrivalTime <= currentTime && p.burstTime < shortestTime) {
                    shortestJob = p;
                    shortestTime = p.burstTime;
                }
            }

            if (shortestJob != null) {
                System.out.println("Executing process " + shortestJob.id + " from time " + currentTime + " to " + (currentTime + shortestJob.burstTime));
                currentTime += shortestJob.burstTime;

                // Calculate waiting time
                int waitingTime = currentTime - shortestJob.arrivalTime - shortestJob.burstTime;
                totalWaitingTime += waitingTime;

                // Mark the process as completed
                shortestJob.burstTime = Integer.MAX_VALUE;
            } else {
                currentTime++;
            }
        }

        double averageWaitingTime = totalWaitingTime / numProcesses;
        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);

        input.close();
    }
}

