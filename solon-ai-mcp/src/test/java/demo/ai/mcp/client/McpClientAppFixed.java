package demo.ai.mcp.client;

import org.noear.solon.Solon;
import org.noear.solon.ai.mcp.client.McpClientProvider;
import org.noear.solon.annotation.Import;

/**
 * 修复版本的McpClientApp
 * @author noear 2025/4/12 created
 */
@Import(profiles = "app-client.yml")
public class McpClientAppFixed {
    public static void main(String[] args) {
        try {
            // 直接使用builder构建McpClientProvider实例
            McpClientProvider provider = McpClientProvider.builder()
                    .apiUrl("http://localhost:8081/sse")
                    .build();
            
            System.out.println("成功创建McpClientProvider实例！");
            
            // 启动Solon应用
            Solon.start(McpClientAppFixed.class, args);
        } catch (Exception e) {
            System.err.println("创建McpClientProvider失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
