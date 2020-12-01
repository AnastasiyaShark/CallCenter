package CallCenter;

public class AutomaticTelephoneExchange implements Runnable {

    @Override
    public void run() {
        try {
            int callsNumber = 20;
            int timeToThink = 2000;
            int times = 3;
            CallQueue callQueue = CallQueue.getInstance();
            for (int v = 0; v < times; v++) {
                for (int i = 0; i < callsNumber; i++) {
                    Call call = new Call();
                    callQueue.addIncomingCall(call);
                }
                System.out.println(" Прошёл " + (v + 1) + " круг ");
                Thread.sleep(timeToThink);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
