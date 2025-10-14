package demo.ai.mcp.server;

import org.noear.solon.Solon;
import org.noear.solon.annotation.Import;
import org.noear.solon.server.http.HttpServerConfigure;

/**
 * McpServerApp类是服务器应用程序的入口点
 * 它使用Solon框架来启动和配置应用程序
 */
@Import(profiles = "app-server.yml")
public class McpServerApp {
    /**
     * main方法是程序的入口点
     * 它使用Solon框架启动应用程序，并配置HTTP服务器的调试模式
     *
     * @param args 命令行参数，用于在启动时传递给应用程序
     */
    public static void main(String[] args) {
        if (Solon.app() != null) {
            if (Solon.app().source() != McpServerApp.class) {
                Solon.stopBlock();
            }
        }

        // 启动Solon应用程序，第二个参数是应用程序的配置器
        Solon.start(McpServerApp.class, args, app -> {
            // 监听HttpServerConfigure事件，以便在HTTP服务器启动前进行配置
            app.onEvent(HttpServerConfigure.class, e -> {
                // 启用调试模式，这通常包括启用更详细的日志记录和错误信息
                e.enableDebug(true);
            });
        });
    }
}
