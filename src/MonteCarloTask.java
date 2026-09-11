import java.time.Instant;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloTask implements Callable<Double> {
    private final long totalPoints;
    public MonteCarloTask(long totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Double call() throws Exception {
        Instant start = Instant.now();
        long pointsInCircle = 0;
        for(long i = 0; i < totalPoints; i++){
            double x = ThreadLocalRandom.current().nextDouble(0,2);
            double y = ThreadLocalRandom.current().nextDouble(0,2);
            double distance = Math.sqrt((x-1)*(x-1)+(y-1)*(y-1));
            if(distance <= 1){
                pointsInCircle++;
            }
        }
        return (double) pointsInCircle;
    }
}
