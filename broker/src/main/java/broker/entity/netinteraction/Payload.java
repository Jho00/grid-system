package broker.entity.netinteraction;

public class Payload {
    private String toExecute;

    public String getToExecute() {
        return toExecute;
    }

    public void setToExecute(String toExecute) {
        this.toExecute = toExecute;
    }

    @Override
    public String toString() {
        return "{" +
                "\"toExecute\":\"" + toExecute + "\"" +
                '}';
    }
}
