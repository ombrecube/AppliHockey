package prog.teampoule.applitest.classAdapter;

/**
 * Created by Julien on 13/04/2017.
 */

public class Message {
    private String message;
    private String Date;
    boolean other;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public boolean isOther() {
        return other;
    }

    public void setOther(boolean other) {
        this.other = other;
    }

    public Message() {
    }


}
