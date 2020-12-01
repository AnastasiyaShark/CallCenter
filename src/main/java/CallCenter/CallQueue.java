package CallCenter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CallQueue {

    public interface ICallQueueVisitor {
        void visit(Queue<Call> queue);
    }

    private static CallQueue instance = null;
    private volatile boolean isFinished;
    private final Queue<Call> incomingCalls;
    private final Queue<Call> processedCalls;


    public CallQueue() {
        incomingCalls = new ConcurrentLinkedQueue<>();
        processedCalls = new ConcurrentLinkedQueue<>();
        isFinished = false;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished() {
        isFinished = true;
    }

    public static synchronized CallQueue getInstance() {
        if (instance == null) {
            instance = new CallQueue();
        }
        return instance;
    }

    public Call getIncomingCall() {
        return incomingCalls.poll();
    }

    public void addProcessedCall(Call call) {
        processedCalls.add(call);
    }

    public void addIncomingCall(Call call) {
        incomingCalls.add(call);
    }

    public boolean isIncommingQueueEmpty() {
        return incomingCalls.size() == 0;
    }

    public void visitProcessedCalls(ICallQueueVisitor visitor) {
        visitor.visit(processedCalls);
    }


}
