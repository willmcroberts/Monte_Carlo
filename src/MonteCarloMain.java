import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MonteCarloMain {
    private static final long totalPoints = 10_000_000_000L;
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        Instant start = Instant.now();

        List<Future<Double>> resultList = new LinkedList<>();

        for (long i = 0; i < 4; i++) {
            Future<Double> result = executor.submit(new MonteCarloTask(totalPoints / 4));
            resultList.add(result);
        }

        long pointsInCircle = 0;
        for (Future<Double> result : resultList) {
            pointsInCircle += result.get();
        }

        double pi = pointsInCircle / (double)totalPoints * 4;
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();

        executor.shutdown();

        System.out.println("pi = " + pi);
        System.out.println("runtime = " + timeElapsed);
    }
}
