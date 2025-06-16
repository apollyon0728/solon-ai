package demo.ai.mcp.client;

import lombok.extern.slf4j.Slf4j;
import org.noear.solon.ai.chat.ChatModel;
import org.noear.solon.ai.chat.message.ChatMessage;
import org.noear.solon.ai.mcp.client.McpClientProvider;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Produces;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.util.MimeType;
import reactor.core.publisher.Flux;

/**
 * @author noear 2025/4/10 created
 */
@Slf4j
@Controller
public class McpClientDemo {
    @Inject
    ChatModel chatModel;

    @Inject
    McpClientProvider mcpClient;

    @Mapping("/")
    public void index(Context ctx) {
        ctx.output("欢迎使用 McpClientDemo！请访问 /mcp/test 来测试 MCP 功能。");
    }

    /**
     * 该方法用于处理特定路径的请求，并生成一个持续更新的聊天消息流。
     * 主要用途是演示如何使用Flux和ChatMessage来创建一个响应式的消息源，
     * 该消息源可以实时或近实时地推送聊天消息。
     *
     * @return 返回一个Flux流，包含ChatMessage对象，表示聊天消息的流。
     */
    @Produces(MimeType.TEXT_EVENT_STREAM_VALUE)
    @Mapping("mcp/test")
    public Flux<ChatMessage> mcpTest() {
        // 使用chatModel的prompt方法来构造一个Flux流，询问杭州今天的天气情况。
        // 通过options配置，增加通过mcpClient获取到的工具集合。
        // 这样做是为了演示如何在聊天模型中集成外部工具或服务。
        return Flux.from(chatModel
                        .prompt("今天杭州的天气情况？")
                        .options(options -> {
                            //转为工具集合用于绑定
                            options.toolsAdd(mcpClient.getTools());
                        })
                        .stream())
                // 从响应中提取消息部分，并将其作为Flux流的一部分返回。
                .map(resp -> resp.getMessage());
    }
}