import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Select a scheduling algorithm or memory allocation method:");
            System.out.println("1. First-Come, First-Served (FCFS) Scheduling");
            System.out.println("2. Shortest-Job-Next (SJN) Scheduling");
            System.out.println("3. Priority Scheduling");
            System.out.println("4. Shortest Remaining Time");
            System.out.println("5. Multiple-Level Queues Scheduling");
            System.out.println("6. Best Fit Memory Allocation");
            System.out.println("7. First Fit Memory Allocation");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    FCFS_Scheduling.simulate();
                    break;
                case 2:
                    SJN_Scheduling.simulate();
                    break;
                case 3:
                    PriorityScheduling.simulate();
                    break;
                case 4:
                    ShortestRemainingTime.simulate();
                    break;
                case 5:
                    MultipleLevelQueuesScheduling.simulate();
                    break;
                case 6:
                    BestFitMemoryAllocation.simulate();
                    break;
                case 7:
                    FirstFitMemoryAllocation.simulate();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        } while (choice != 0);

        input.close();
    }
}


