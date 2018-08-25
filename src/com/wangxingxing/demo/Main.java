package com.wangxingxing.demo;

import org.java_websocket.server.WebSocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        InetSocketAddress inetSocketAddress = new InetSocketAddress(2018);
        WebSocketServer socketServer = new WebSocketService(inetSocketAddress);
        socketServer.start();
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            int port = socketServer.getPort();
            System.out.println(String.format("服务已启动：%s:%d", ip, port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(in);
        while (true) {
            try {
                String msg = reader.readLine();
                ((WebSocketService) socketServer).sendToAll(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
