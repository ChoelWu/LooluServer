/*
    +----------------------------------------------------------------------
    + LooluServer
    +----------------------------------------------------------------------
    + Create Info: 2018-02-28 By Cheol Wu <775669127@qq.com>
    +----------------------------------------------------------------------
    + Update Info 1:
    + Update Info 2:
    +----------------------------------------------------------------------
*/

package LooluServer;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Server {
    private static ServerSocket server;
    private static Properties properties;
    private int port;
    private boolean flag;
    private MyFile log = new MyFile();
    private static final String LOG_FILE_NAME = "src/resources/log/log.txt";

    //配置文件
    static {
        //创建properties对象
        properties = new Properties();
        try {
            //加载配置文件中的配置
            properties.load(new FileInputStream(new File("src/resources/conf/server.conf")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //初始化服务器
    public void init() {
        try {
            //读取配置文件中的端口号
            //——————————————————————配置文件端口号读取失败操作———————————————单独写方法来检查所必需的配置信息—————————
            port = Integer.parseInt(properties.getProperty("port"));
            server = new ServerSocket(port);
        } catch (IOException e) {
            //——————————————————————服务器启动失败操作————————————————————————
            log.appendContent(LOG_FILE_NAME, true, e.getMessage());
        }
    }

    //监听来自客户端浏览器的请求
    public void receive() {
        //与客户端建立套接字
        Socket clientSocket = new Socket();
        try {
            clientSocket = server.accept();
            String[] clientAddr = clientSocket.getRemoteSocketAddress().toString().split(":");
            String addrIP = clientAddr[0].split("/")[1];
            String addrPort = clientAddr[1];
            String content = addrIP + "  request server.      PORT:  " + addrPort;
            log.appendContent(LOG_FILE_NAME, true, content);
        } catch (IOException e) {
            log.appendContent(LOG_FILE_NAME, true, e.getMessage());
        }
        //创建子线程
        ServerThread thread = new ServerThread(clientSocket);
        //启动子线程
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 运行服务器
     */
    public int startServer () {
        flag = true;
        int returnCode = 1;
        try  {
            IPControl a = new IPControl();
            Server Loolu = new Server();
            Loolu.init();
            String content = "------------Server startup successful------------";
            log.appendContent(LOG_FILE_NAME, true, content);
            while(flag){
                Loolu.receive();
            }
        } catch(Exception e) {
            returnCode = 0;
            String content = "ERROR:     Server startup failed.";
            log.appendContent(LOG_FILE_NAME, true, content);
        }
        return returnCode;
    }

    /**
     * 关闭服务器
     */
    public int stopServer() {
        int returnCode = 1;
        try {
            server.close();
            flag = false;
            String content = "------------Server shutdown successful------------";
            log.appendContent(LOG_FILE_NAME, true, content);
        } catch(Exception e) {
            returnCode = 0;
            String content = "ERROR:    Server shutdown failed.";
            log.appendContent(LOG_FILE_NAME, true, content);
        }
        return returnCode;
    }
}