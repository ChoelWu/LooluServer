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

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.Date;
import java.util.Map;
import java.math.BigDecimal;

import static java.lang.Math.round;

public class Response {
    private String fileName;
    private Map<String, String> userValues;
    private PrintStream ps;
    private Request request;
    private Socket clientSocket;
    protected FileUtil fileUtil = new FileUtil();
//    protected LoginUtil loginUtil = new LoginUtil();

    public String getFileName() {
        return this.fileName;
    }

    public String getFileSize() {
        File file = new File("src/resources/" + this.fileName);
        String FileSize =  String.format("%.2f", (file.length() / 1024.0));
        Server a = new Server();
//        a.FileSize =
        return FileSize;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, String> getUserValues() {
        return this.userValues;
    }

    public void setUserValues(Map<String, String> userValues) {
        this.userValues = userValues;
    }

    public PrintStream getPs() {
        return this.ps;
    }

    public void setPs(PrintStream ps) {
        this.ps = ps;
    }

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Socket getClientSocket() {
        return this.clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * 服务器响应
     * @param request
     */
    public Response(Request request) {
        this.setRequest(request);
        //获得客户端端口
        this.setClientSocket(request.getSocket());
        //获得客户端请求的文件
        this.setFileName(request.getRequestFileName());
        //获得客户端请求的参数
        this.userValues = this.request.getSocketValues();
        try {
            this.init();
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    /**
     * 文件输出
     * @param fileName
     * @throws IOException
     */
    public void display(String fileName) throws IOException {
        //获得输出流
        this.ps = this.request.getPrint();
        if (!fileName.equals("") && !fileName.equals("error.html")) { // 请求成功
            this.ps.println("HTTP/1.1 200 OK");
            this.ps.println();
            //将本地文件读入输出流
            this.fileUtil.readFile(fileName, this.ps);
        } else if (fileName.equals("error.html")) { // 404请求文件不存在
            this.ps.println("HTTP/1.1 404 fileNotFound");
            this.ps.println();
            //将本地文件读入输出流
            this.fileUtil.readFile("error.html", this.ps);
        } else {
            this.ps.println((new Date()).toString());
        }
        if (this.ps != null) {
            //关闭输出流
            this.ps.close();
        }
    }

    public void init() throws IOException {
        if (this.userValues.isEmpty()) { //无参数情况下
            if (this.fileName != "") { //有文件请求路径
                this.display(this.fileName);
            } else { // 无文件路径，使用默认输出
                // 获得输出流
                this.ps = this.request.getPrint();
                // 将时间存入输出流
                this.ps.println((new Date()).toString());
                if (this.ps != null) {
                    this.ps.close();
                }
                if (this.clientSocket != null) {
                    //关闭Socket连接
                    this.clientSocket.close();
                }
            }
        } else { //有参数情况下

        }

    }
}
