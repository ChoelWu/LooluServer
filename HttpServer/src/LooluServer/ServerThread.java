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

import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket clientSocket;
    private Request request;
    private Response response;
    public String size;

    //重载方法
    public ServerThread() {
        super();
    }

    //重载方法
    public ServerThread(Socket clientSocket) {
        super();
        this.clientSocket = clientSocket;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void run(){
        super.run();
        try {
            this.setRequest(new Request(clientSocket));
            this.response = new Response(request);
            this.setResponse(response);
            size = response.getFileSize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}