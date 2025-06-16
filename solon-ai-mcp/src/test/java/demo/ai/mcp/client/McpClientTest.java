package demo.ai.mcp.client;

import java.util.Properties;

/**
 * 简单测试类，用于测试配置问题
 */
public class McpClientTest {
    public static void main(String[] args) {
        try {
            // 创建一个Properties对象，模拟配置文件
            Properties props = new Properties();
            props.setProperty("apiUrl", "http://localhost:8081/sse");
            
            System.out.println("配置属性：" + props.getProperty("apiUrl"));
            System.out.println("测试成功！");
        } catch (Exception e) {
            System.err.println("测试失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
