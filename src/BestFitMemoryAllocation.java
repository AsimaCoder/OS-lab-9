import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

class MemoryBlock6 {
    int id;
    int size;
    boolean allocated;

    public MemoryBlock6(int id, int size) {
        this.id = id;
        this.size = size;
        this.allocated = false;
    }
}

public class BestFitMemoryAllocation {
    public static void simulate() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the total memory size: ");
        int totalMemorySize = input.nextInt();

        System.out.print("Enter the number of memory blocks: ");
        int numBlocks = input.nextInt();

        ArrayList<MemoryBlock6> memoryBlocks = new ArrayList<>();

        for (int i = 0; i < numBlocks; i++) {
            System.out.print("Enter size for memory block " + (i + 1) + ": ");
            int blockSize = input.nextInt();
            memoryBlocks.add(new MemoryBlock6(i + 1, blockSize));
        }

        System.out.print("Enter the number of processes: ");
        int numProcesses = input.nextInt();

        System.out.println("Enter the memory requirements for each process:");
        ArrayList<Integer> processSizes = new ArrayList<>();
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            int processSize = input.nextInt();
            processSizes.add(processSize);
        }

        ArrayList<Integer> allocations = bestFitMemoryAllocation(memoryBlocks, processSizes);

        System.out.println("\nMemory Allocation:");
        for (int i = 0; i < numProcesses; i++) {
            if (allocations.get(i) != -1) {
                System.out.println("Process " + (i + 1) + " is allocated to Memory Block " + (allocations.get(i)));
                memoryBlocks.get(allocations.get(i)).allocated = true;
            } else {
                System.out.println("Process " + (i + 1) + " could not be allocated.");
            }
        }

        input.close();
    }

    public static ArrayList<Integer> bestFitMemoryAllocation(ArrayList<MemoryBlock6> memoryBlocks, ArrayList<Integer> processSizes) {
        ArrayList<Integer> allocations = new ArrayList<>(Collections.nCopies(processSizes.size(), -1));

        for (int i = 0; i < processSizes.size(); i++) {
            int bestFitIdx = -1;
            int bestFitSize = Integer.MAX_VALUE;

            for (int j = 0; j < memoryBlocks.size(); j++) {
                MemoryBlock6 block = memoryBlocks.get(j);
                if (!block.allocated && block.size >= processSizes.get(i) && block.size < bestFitSize) {
                    bestFitIdx = j;
                    bestFitSize = block.size;
                }
            }

            if (bestFitIdx != -1) {
                allocations.set(i, bestFitIdx);
            }
        }

        return allocations;
    }
}

