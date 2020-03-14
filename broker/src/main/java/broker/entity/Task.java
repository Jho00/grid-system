package broker.entity;


import broker.common.constants.MessageStatuses;
import broker.common.constants.RequestActions;
import broker.config.AppConfig;
import broker.config.ConfigModel;
import broker.entity.netinteraction.Action;
import broker.entity.netinteraction.Payload;
import broker.services.TCPService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.*;

import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSubTypes({
        @JsonSubTypes.Type(value = Payload.class, name = "payload")
})
public class Task implements Runnable {
    @Setter
    private ChannelHandlerContext ctx;
    @Setter
    private ConfigModel configModel = AppConfig.getConfig();


    @JsonProperty("action")
    private String action;
    @JsonProperty("payload")
    public Payload payload;

    @Override
    public void run() {
        ObjectMapper objectMapper = new ObjectMapper();
        TCPService service = new TCPService(configModel.getPeerHost(), Integer.parseInt(configModel.getPeerPort()));
        Response response;
        try {
            Action action = new Action(RequestActions.TASK, payload);
            String result = service.sendCommand(action);

            try {
                response = objectMapper.readValue(result, Response.class);
                this.sendResponse(response.toString());
            } catch (IOException e) {
                response =  new Response(MessageStatuses.FAIL, "Cannot parse response from client.");
                this.sendResponse(response.toString());
            }

//            TODO:  Add to queue Again
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            response =  new Response(MessageStatuses.FAIL, "Error: " + e.getMessage());
            this.sendResponse(response.toString());
        }
    }

    private void sendResponse(String response) {
        ctx.write(Unpooled.copiedBuffer(response, CharsetUtil.UTF_8));
    }
}
