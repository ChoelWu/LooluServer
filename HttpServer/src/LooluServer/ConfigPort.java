package LooluServer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class ConfigPort {
    private static final String CONF_FILE_NAME = "src/resources/conf/server.conf";

    /**
     * 端口设置
     * @param port
     */
    public boolean SetPort(String port) {
        try {
            Scanner in = new Scanner(new FileInputStream(CONF_FILE_NAME), "UTF-8");
            List<String> ConfContent = new ArrayList<>();
            for (int i = 0; i < 10000 ; i ++) {
                ConfContent.add(in.nextLine().trim());
                if (!in.hasNext()) {
                    break;
                }
            }
            String outPrintText = "";
            for(int i = 0; i < ConfContent.size();i ++) {
                String[] arr = ConfContent.get(i).split("=");
                if(arr[0].trim().equals("PORT") || arr[0].trim().equals("port")) {
                    ConfContent.set(i, "port = " + port);
                }
                outPrintText += ConfContent.get(i) + "\n";
            }
            PrintWriter out = new PrintWriter(new FileWriter(CONF_FILE_NAME, false), true);
            out.println(outPrintText);
            out.close();
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    /**
     * 检查端口是否可用
     * @param host
     * @param port
     * @return
     * @throws UnknownHostException
     */
    public static boolean isPortUsing(String host,int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        try {
            Socket socket = new Socket(theAddress,port);
            flag = true;
            socket.close();
        } catch (IOException e) {

        }
        return flag;
    }
}
