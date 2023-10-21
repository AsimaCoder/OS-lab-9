import java.util.Scanner;
import java.util.ArrayList;

class MemoryBlock {
    int id;
    int size;
    boolean allocated;

    public MemoryBlock(int id, int size) {
        this.id = id;
        this.size = size;
        this.allocated = false;
    }
}

public class FirstFitMemoryAllocation {
    public static void simulate() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the total memory size: ");
        int totalMemorySize = input.nextInt();

        System.out.print("Enter the number of memory blocks: ");
        int numBlocks = input.nextInt();

        ArrayList<MemoryBlock> memoryBlocks = new ArrayList<>();

        for (int i = 0; i < numBlocks; i++) {
            System.out.print("Enter size for memory block " + (i + 1) + ": ");
            int blockSize = input.nextInt();
            memoryBlocks.add(new MemoryBlock(i + 1, blockSize));
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

        firstFitMemoryAllocation(memoryBlocks, processSizes);

        System.out.println("\nMemory Allocation:");
        for (int i = 0; i < memoryBlocks.size(); i++) {
            MemoryBlock block = memoryBlocks.get(i);
            if (block.allocated) {
                System.out.println("Memory Block " + block.id + " is allocated to a process.");
            } else {
                System.out.println("Memory Block " + block.id + " is not allocated.");
            }
        }

        input.close();
    }

    public static void firstFitMemoryAllocation(ArrayList<MemoryBlock> memoryBlocks, ArrayList<Integer> processSizes) {
        for (int i = 0; i < processSizes.size(); i++) {
            for (int j = 0; j < memoryBlocks.size(); j++) {
                MemoryBlock block = memoryBlocks.get(j);
                if (!block.allocated && block.size >= processSizes.get(i)) {
                    block.allocated = true;
                    break;
                }
            }
        }
    }
}

