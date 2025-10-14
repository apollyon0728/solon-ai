package demo.ai.mcp.server;

import org.noear.solon.ai.chat.tool.MethodToolProvider;
import org.noear.solon.ai.mcp.server.McpServerEndpointProvider;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

/**
 * @author noear 2025/4/17 created
 */
@Configuration
public class McpServerConfig {

    /**
     * 用配置构建
     * 配置并返回一个McP服务器端点提供者实例
     * 该方法主要用于配置和初始化一个特定的McP服务器端点提供者（McpServerEndpointProvider）
     * 通过注入配置和添加工具提供者，来为服务器端点提供额外的功能和配置
     *
     * @param serverEndpoint 通过注入配置创建的McP服务器端点提供者实例该实例预配置了服务器的相关信息
     * @param serverTool     一个McP服务器工具实例，用于为服务器端点提供额外的功能和工具
     * @return 返回配置好的McP服务器端点提供者实例
     */
    @Bean
    public McpServerEndpointProvider demo1(@Inject("${solon.ai.mcp.server.demo1}") McpServerEndpointProvider serverEndpoint,
                                           McpServerTool serverTool) {
        // 为服务器端点提供者添加一个新的方法工具提供者，以扩展其功能
        serverEndpoint.addTool(new MethodToolProvider(serverTool));

        // 返回配置好的服务器端点提供者实例
        return serverEndpoint;
    }


    //用构建器构建
    //@Bean
    /**
     * 创建并配置一个名为demo2的服务器端点提供者
     * <p>
     * 此方法通过构建一个McpServerEndpointProvider实例来设置服务器端点提供者的名称和sseEndpoint，
     * 然后将其与一个McpServerTool2实例关联，以提供额外的功能或配置
     *
     * @param serverTool 用于增强服务器端点功能的工具提供者实例
     * @return 返回一个配置完备的McpServerEndpointProvider实例
     */
    public McpServerEndpointProvider demo2(McpServerTool2 serverTool) {
        // 构建一个McpServerEndpointProvider实例，指定名称为demo2，并设置其sseEndpoint为/demo2/sse
        McpServerEndpointProvider serverEndpoint = McpServerEndpointProvider.builder()
                .name("demo2")
                .mcpEndpoint("/demo2/sse")
                .build();

        // 为serverEndpoint实例添加一个新的MethodToolProvider实例，以扩展其功能
        serverEndpoint.addTool(new MethodToolProvider(serverTool));

        // 返回配置完毕的serverEndpoint实例
        return serverEndpoint;
    }

}
