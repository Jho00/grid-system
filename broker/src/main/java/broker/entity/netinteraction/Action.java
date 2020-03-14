package broker.entity.netinteraction;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Action {
    private String actionName;
    private Payload payload;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "{" +
                "\"action\":\"" + actionName + "\"," +
                "\"payload\":" + payload.toString() +
                '}';
    }
}
