import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        invokeList();

//        singleThread();
    }

    //RecursiveAction - write 100 files
    public static void invokeList() throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool().commonPool();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        List<NumberGeneratorRA> listRA = new ArrayList<>();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++){
            listRA.add(new NumberGeneratorRA());
        }
        listRA.forEach(forkJoinPool::invoke);
    }

    //Single Thread - write 100 files
    public static void singleThread() {
        new NumberGeneratorST().makeRegionNumber();
    }
}
