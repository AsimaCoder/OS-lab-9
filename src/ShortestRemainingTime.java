import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Process4 {
    int id;
    int arrivalTime;
    int burstTime;

    public Process4(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class ShortestRemainingTime {
    public static void simulate() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = input.nextInt();

        // Create an array to store the processes
        Process4[] processes = new Process4[numProcesses];

        // Input process information
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            int arrivalTime = input.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = input.nextInt();

            processes[i] = new Process4(i + 1, arrivalTime, burstTime);
        }

        // Sort processes by arrival time
        ArrayList<Process4> processList = new ArrayList<>(Arrays.asList(processes));
        processList.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        double totalWaitingTime = 0;
        PriorityQueue<Process4> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.burstTime));

        System.out.println("\nProcess execution order:");
        while (!processList.isEmpty() || !queue.isEmpty()) {
            // Add arrived processes to the queue
            while (!processList.isEmpty() && processList.get(0).arrivalTime <= currentTime) {
                queue.add(processList.remove(0));
            }

            if (queue.isEmpty()) {
                currentTime++;
                continue;
            }

            Process4 shortestJob = queue.poll();

            System.out.println("Executing process " + shortestJob.id + " from time " + currentTime + " to " + (currentTime + 1));
            currentTime++;
            shortestJob.burstTime--;

            if (shortestJob.burstTime > 0) {
                queue.add(shortestJob);
            } else {
                int waitingTime = currentTime - shortestJob.arrivalTime - 1;
                totalWaitingTime += waitingTime;
            }
        }

        double averageWaitingTime = totalWaitingTime / numProcesses;
        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);

        input.close();
    }
}
