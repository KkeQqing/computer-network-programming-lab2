package com.mycompany.lab2gui;

import javax.swing.SwingWorker;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户端线程类，用于与服务器进行 Echo 通信。
 * 使用 SwingWorker 实现异步操作，避免阻塞 UI。
 */
public class ClientThread extends SwingWorker<List<String>, String> {

    private final Socket socket; // Socket 对象
    private final int messageCount; // 消息数量
    private final String message; // 消息

    public ClientThread(Socket socket, int messageCount, String message) {
        this.socket = socket;
        this.messageCount = messageCount;
        this.message = message;
    }

    /**
     * 执行 Echo 通信任务。
     * 发送指定数量的消息，并接收回显消息。
     * 发送消息和接收消息之间有 10ms 的延时，模拟网络延迟。
     *
     * @return 所有回显消息列表
     * @throws Exception 任务执行过程中发生的异常
     */
    @Override
    protected List<String> doInBackground() throws Exception {
        List<String> responses = new ArrayList<>();
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            out = new PrintWriter(socket.getOutputStream(), true); // 创建 PrintWriter 对象, 用于发送消息
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 创建 BufferedReader 对象, 用于接收消息

            // 批量发送消息，每条之间延时 10ms
            for (int i = 0; i < messageCount; i++) {
                out.println(message);

                // 模拟延迟以测试性能
                Thread.sleep(10); // 每条消息间隔 10ms

                // 读取回显消息
                String echo = in.readLine();
                if (echo != null) {
                    publish(echo); // 发布到 UI 线程
                    responses.add(echo);
                }
            }

        } catch (IOException e) {
            System.err.println("通信异常：" + e.getMessage());
            throw e;
        } finally {
//            try {
//                if (in != null) in.close();
//                if (out != null) out.close();
//                if (socket != null && !socket.isClosed()) {
//                    socket.close();
//                }
//            } catch (IOException e) {
//                System.err.println("关闭连接失败：" + e.getMessage());
//            }
        }

        return responses;
    }

    /**
     * 处理从 doInBackground() 方法中发布的数据。
     * 在 EDT 中运行。
     *
     * @param chunks 从 doInBackground() 方法中发布的数据
     */
    @Override
    protected void process(java.util.List<String> chunks) {
        // 每次 publish 的数据都会进入这个方法，在 EDT 中运行
        for (String msg : chunks) {
            MainFrame.getInstance().appendEchoMessage(msg);
        }
    }

    /**
     * 任务执行完成后的回调方法。
     * 在 EDT 中运行。
     */
    @Override
    protected void done() {
        try {
            List<String> result = get(); // 获取最终结果
            System.out.println("总共收到 " + result.size() + " 条响应");
        } catch (Exception e) {
            System.err.println("任务执行出错：" + e.getMessage());
        }
    }
}