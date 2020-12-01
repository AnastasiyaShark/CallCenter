package CallCenter;

public class CallSpecialist implements Runnable {

    @Override
    public void run() {
        try {
            int timeToThink = 2000;
            CallQueue callQueue = CallQueue.getInstance();
            Call incimingCall = null;
            while (Thread.currentThread().isAlive() && !Thread.currentThread().isInterrupted()) {

                while ((incimingCall = callQueue.getIncomingCall()) == null) {
                    if (Thread.currentThread().isInterrupted() || callQueue.isFinished()) {
                        return;
                    }
                }
                Thread.sleep(timeToThink);
                callQueue.addProcessedCall(incimingCall);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
