package com.mycompany.lab2gui;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        int port = 7;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Echo 服务器启动，监听端口：" + port);

            while (true) {
                Socket clientSocket = serverSocket.accept(); // 等待客户端连接
                System.out.println("客户端连接：" + clientSocket.getRemoteSocketAddress());

                new Thread(() -> {
                    try (
                            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));// 创建 BufferedReader, 用于接收消息
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true) // 创建 PrintWriter, 用于发送消息
                    ) {
                        String input;
                        while ((input = in.readLine()) != null) {
                            System.out.println("收到：" + input);
                            out.println(input); // 回显
                        }
                    } catch (IOException e) {
                        System.err.println("处理客户端异常：" + e.getMessage());
                    }
                }).start();
            }
        } catch (IOException e) {
            System.err.println("服务器启动失败：" + e.getMessage());
        }
    }
}