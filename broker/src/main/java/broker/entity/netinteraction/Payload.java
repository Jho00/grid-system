package broker.entity.netinteraction;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
public class Payload {
    private String toExecute;
    private String data;

    @SneakyThrows
    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
