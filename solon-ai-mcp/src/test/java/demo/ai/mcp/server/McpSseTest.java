package demo.ai.mcp.server;

import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Produces;
import org.noear.solon.core.util.MimeType;
import org.noear.solon.web.sse.SseEvent;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * @author noear 2025/4/14 created
 */
@Slf4j
@Controller
public class McpSseTest {
    /**
     * 提供一个基于服务器发送事件（SSE）的测试接口。
     * 该方法通过 "/test/sse1" 路径进行访问，返回一个用于推送事件的 Flux 流。
     *
     * @return 返回类型为 Flux<SseEvent> 的事件流，用于向客户端推送 SSE 事件
     */
    @Produces(MimeType.TEXT_EVENT_STREAM_VALUE)
    @Mapping("/test/sse1")
    public Flux<SseEvent> sse1() {
        // 生成唯一会话 ID，用于标识当前客户端会话
        String sessionId = UUID.randomUUID().toString();

        // 构建并返回初始事件对象，包含事件名称和带有会话 ID 的数据链接
        return Flux.just(new SseEvent()
                .name("test")
                .data("/message?sessionId=" + sessionId));
    }


    @Produces(MimeType.TEXT_EVENT_STREAM_VALUE)
    @Mapping("/test/sse2")
    public Flux<SseEvent> sse2() {
        return Flux.create(sink -> {
            String sessionId = UUID.randomUUID().toString();

            log.debug("Created new SSE connection for session: {}", sessionId);

            // Send initial endpoint event
            sink.next(new SseEvent()
                    .name("test")
                    .data("/message?sessionId=" + sessionId));
            sink.onCancel(() -> {
                log.debug("Session {} cancelled", sessionId);
            });
        });
    }
}