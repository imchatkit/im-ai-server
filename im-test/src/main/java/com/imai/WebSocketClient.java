package com.imai;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import java.io.File;
import java.util.concurrent.CountDownLatch;

public class WebSocketClient {
    private final CountDownLatch closeLatch = new CountDownLatch(1);
    private volatile boolean running = true;

    public interface WSLibrary extends Library {
        String DLL_PATH = "D:" + File.separator + "code" + File.separator + "github" +
            File.separator + "go-ws-client" + File.separator + "libwsclient.dll";

        WSLibrary INSTANCE = Native.load(DLL_PATH, WSLibrary.class);

        // 定义回调接口
        interface MessageCallback extends Callback {
            void invoke(String message);
        }

        int InitWebSocket(String url, String token, String deviceType);
        int SendMessage(String message);
        int CloseWebSocket();
        void SetMessageCallback(MessageCallback callback);
    }

    private final WSLibrary lib = WSLibrary.INSTANCE;
    private WSLibrary.MessageCallback callback;

    // 设置消息处理回调
    public void setMessageHandler(MessageHandler handler) {
        callback = message -> {
            if (handler != null) {
                handler.onMessage(message);
            }
        };
        lib.SetMessageCallback(callback);
    }

    public boolean connect(String url, String token, String deviceType) {
        return lib.InitWebSocket(url, token, deviceType) == 1;
    }

    public boolean send(String message) {
        return lib.SendMessage(message) == 1;
    }

    public boolean close() {
        return lib.CloseWebSocket() == 1;
    }

    // 消息处理接口
    public interface MessageHandler {
        void onMessage(String message);
    }

    public void waitForClose() {
        try {
            closeLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        running = false;
        close();
        closeLatch.countDown();
    }

    // 添加运行状态检查
    public boolean isRunning() {
        return running;
    }

    // main
    public static void main(String[] args) {
        WebSocketClient client = new WebSocketClient();

        // 添加关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("正在关闭WebSocket连接...");
            client.shutdown();
        }));

        // 设置消息处理器
        client.setMessageHandler(message -> {
            System.out.println("收到消息: " + message);
        });

        boolean connect = client.connect("ws://localhost:9688/ws",
            "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpblR5cGUiOiJsb2dpbiIsImxvZ2luSWQiOiJpbV91c2VyOjM5ODEyMDM2NDM0MDE3OCIsInJuU3RyIjoiaUFqQkhQM09RV21ZQklmS0xYeDBBYTZjdmdHb2ZISngiLCJ1c2VySWQiOjM5ODEyMDM2NDM0MDE3OCwidXNlck5hbWUiOiLoqIDpnZnmgKEifQ.jXStT5wSzYvcCHy4PKLGeskhNxNd3Nx-xrVmh0Yaz98",
            "deviceType");

        if (connect) {
            System.out.println("连接成功");
            client.send("Hello, World!");

            // 等待连接关闭
            client.waitForClose();
        } else {
            System.out.println("连接失败");
        }
    }
}
