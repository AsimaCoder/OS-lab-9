import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

class Process5 {
    int id;
    int arrivalTime;
    int burstTime;

    public Process5(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

class MultipleLevelQueuesScheduling {
    public static void simulate() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = input.nextInt();

        // Create queues for different priority levels
        Queue<Process5> highPriorityQueue = new LinkedList<>();
        Queue<Process5> mediumPriorityQueue = new LinkedList<>();
        Queue<Process5> lowPriorityQueue = new LinkedList<>();

        // Input process information
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            int arrivalTime = input.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = input.nextInt();

            Process5 process = new Process5(i + 1, arrivalTime, burstTime);

            // Assign processes to priority queues based on some criteria
            if (arrivalTime < 5) {
                highPriorityQueue.add(process);
            } else if (arrivalTime < 10) {
                mediumPriorityQueue.add(process);
            } else {
                lowPriorityQueue.add(process);
            }
        }

        int currentTime = 0;
        double totalWaitingTime = 0;

        System.out.println("\nProcess execution order:");
        while (!highPriorityQueue.isEmpty() || !mediumPriorityQueue.isEmpty() || !lowPriorityQueue.isEmpty()) {
            // Execute processes in high-priority queue first
            Queue<Process5> currentQueue = highPriorityQueue.isEmpty() ?
                    (mediumPriorityQueue.isEmpty() ? lowPriorityQueue : mediumPriorityQueue) : highPriorityQueue;

            Process5 currentProcess = currentQueue.poll();
            int remainingBurstTime = currentProcess.burstTime;

            System.out.println("Executing process " + currentProcess.id + " from time " + currentTime + " to " + (currentTime + remainingBurstTime));
            currentTime += remainingBurstTime;

            // Calculate waiting time
            int waitingTime = currentTime - currentProcess.arrivalTime - remainingBurstTime;
            totalWaitingTime += waitingTime;

            // Move the remaining part to the appropriate queue
            if (remainingBurstTime > 0) {
                if (currentProcess.arrivalTime < 5) {
                    highPriorityQueue.add(new Process5(currentProcess.id, currentProcess.arrivalTime, remainingBurstTime));
                } else if (currentProcess.arrivalTime < 10) {
                    mediumPriorityQueue.add(new Process5(currentProcess.id, currentProcess.arrivalTime, remainingBurstTime));
                } else {
                    lowPriorityQueue.add(new Process5(currentProcess.id, currentProcess.arrivalTime, remainingBurstTime));
                }
            }
        }

        double averageWaitingTime = totalWaitingTime / numProcesses;
        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);

        input.close();
    }
}

