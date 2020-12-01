package CallCenter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Call {

    private String timeStamp;

    public Call() {
        timeStamp = new SimpleDateFormat("HH.mm.ss.SSS").format(new Date());
    }

    @Override
    public String toString() {
        return "Call{" + timeStamp + '\'' + '}';
    }
}
