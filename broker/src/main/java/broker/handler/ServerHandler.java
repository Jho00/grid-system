package broker.handler;

import broker.common.constants.MessageStatuses;
import broker.common.constants.RequestActions;
import broker.entity.Response;
import broker.entity.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf inBuffer = (ByteBuf) msg;

        String received = inBuffer.toString(CharsetUtil.UTF_8);
        System.out.println("Server received: " + received);

        Task task;

        try {
            task = objectMapper.readValue(received, Task.class);
        } catch (UnrecognizedPropertyException exception) {
            Response response = new Response(MessageStatuses.FAIL, "Cannot parse request");
            ctx.write(Unpooled.copiedBuffer(response.toString(), CharsetUtil.UTF_8));
            return;
        }

        if (task.getAction().equals(RequestActions.TASK)) {

            return;
        }

        Response response = new Response(MessageStatuses.FAIL, "Unknow action: " + task.getAction());
        ctx.write(Unpooled.copiedBuffer(response.toString(), CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}