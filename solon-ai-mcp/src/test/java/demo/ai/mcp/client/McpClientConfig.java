package demo.ai.mcp.client;

import org.noear.solon.ai.chat.ChatConfig;
import org.noear.solon.ai.chat.ChatModel;
import org.noear.solon.ai.mcp.client.McpClientProvider;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

@Configuration
public class McpClientConfig {

//    @Bean
//    public McpClientProvider clientWrapper(@Inject("${solon.ai.mcp.client.demo}") McpClientProvider client) {
//        return client;
//    }

    @Bean
    public McpClientProvider clientWrapper() {
        // 直接使用builder构建McpClientProvider实例，而不是从配置中注入
        return McpClientProvider.builder()
                .apiUrl("http://localhost:8081/sse")
                .build();
    }

    @Bean
    public ChatModel chatModel(@Inject("${solon.ai.chat.demo}") ChatConfig chatConfig, McpClientProvider toolProvider) {
        return ChatModel.of(chatConfig)
                .defaultToolsAdd(toolProvider)
                .build();
    }
}