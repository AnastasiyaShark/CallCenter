package CallCenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        int sleepOneSec = 1000;

        List<CallSpecialist> callSpecialists = new ArrayList<>(Arrays.asList(
                new CallSpecialist(),
                new CallSpecialist(),
                new CallSpecialist(),
                new CallSpecialist(),
                new CallSpecialist()
        ));

        AutomaticTelephoneExchange automaticTelephoneExchange = new AutomaticTelephoneExchange();
        try {
            final ExecutorService threadPool = Executors.newFixedThreadPool(6);

            for (CallSpecialist specialist : callSpecialists) {
                threadPool.submit(specialist);
            }
            Future<?> result = threadPool.submit(automaticTelephoneExchange);
            CallQueue callQueue = CallQueue.getInstance();
            //wait until automaticTelephoneExchange will finish its job
            result.get();
            while (!callQueue.isIncommingQueueEmpty()) {
                Thread.sleep(sleepOneSec);
            }
            callQueue.setFinished();
            threadPool.shutdown();
            System.out.println(" Должно завершится");
            threadPool.awaitTermination(5, TimeUnit.MINUTES);
            CallQueue.getInstance().visitProcessedCalls(queue -> {
                System.out.println(" Total " + queue.size());
                for (Call call : queue) {
                    System.out.println(call);
                }
            });
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
