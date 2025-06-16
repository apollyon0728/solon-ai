package org.noear.solon.ai.mcp.client;

/**
 * 简单测试类，用于测试McpClientProvider的创建
 */
public class McpClientTest {
    public static void main(String[] args) {
        try {
            // 直接使用builder构建McpClientProvider实例
            McpClientProvider provider = McpClientProvider.builder()
                    .apiUrl("http://localhost:8081/sse")
                    .build();
            
            System.out.println("成功创建McpClientProvider实例！");
            System.out.println("Provider: " + provider);
        } catch (Exception e) {
            System.err.println("创建McpClientProvider失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
