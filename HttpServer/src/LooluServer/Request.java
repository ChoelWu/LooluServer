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

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private InputStream input;
    private Socket socket;
    private BufferedReader buffer;
    private String schema;
    private String requestFileName;
    private String requestData;
    private String values_Str;
    private int paramLength;
    private Map<String, String> socketValues;
    private PrintStream print;
    protected MessageUtil messageUtil = new MessageUtil();

    public InputStream getInput() {
        return this.input;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getSchema() {
        return this.schema;
    }

    /**
     * 得到请求头
     * @param schema
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getRequestFileName() {
        return this.requestFileName;
    }

    public void setRequestFileName(String requestFileName) {
        this.requestFileName = requestFileName;
    }

    public String getRequestData() {
        return this.requestData;
    }

    /**
     * 获得了请求内容
     * @param requestData
     */
    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getValues_Str() {
        return this.values_Str;
    }

    public void setValues_Str(String values_Str) {
        this.values_Str = values_Str;
    }

    public int getParamLength() {
        return this.paramLength;
    }

    public void setParamLength(int paramLength) {
        this.paramLength = paramLength;
    }

    public BufferedReader getBuffer() {
        return this.buffer;
    }

    public void setBuffer(BufferedReader buffer) {
        this.buffer = buffer;
    }

    public Map<String, String> getSocketValues() {
        return this.socketValues;
    }

    public void setSocketValues(Map<String, String> socketValues) {
        this.socketValues = socketValues;
    }

    public PrintStream getPrint() {
        return this.print;
    }

    public void setPrint(PrintStream print) {
        this.print = print;
    }

    private void doSchema(String firstLineInData) throws IOException {
        this.socketValues = new HashMap();
        //GET请求的处理
        if (this.schema.equals("GET")) {
            this.setRequestData(
                    this.messageUtil.getRequestData(firstLineInData)
            );
            //判断是否存在参数
            if (this.requestData.contains("?")) {
                //参数存在，获得请求中的文件路径并存储
                this.setRequestFileName(
                        this.messageUtil.getFileName(this.getRequestData())
                );
                //参数存在，获得请求中的参数并存储
                this.setSocketValues(
                        this.messageUtil.getValues(this.getRequestData())
                );
            } else {
                //参数不存在，直接获得请求中的文件路径并存储
                this.setRequestFileName(this.requestData);
            }
        } else { //POST方法
            this.setRequestFileName(
                    this.messageUtil.getRequestData(firstLineInData)
            );
            this.getUserInfo(this.buffer);
        }
    }

    private void getUserInfo(BufferedReader br) throws IOException {
        while(true) {
            String userInfo;
            if (this.buffer.ready()) {
                userInfo = this.buffer.readLine();
                if (!userInfo.contains("Content-Length")) {
                    continue;
                }
                String[] temp = userInfo.split(" ");
                this.setParamLength(Integer.parseInt(temp[1]));
            }
            this.buffer.readLine();
            userInfo = "";
            for(int i = 0; i < this.getParamLength(); ++i) {
                userInfo = userInfo + (char)this.buffer.read();
            }

            this.setValues_Str(userInfo);
            this.setSocketValues(this.messageUtil.getValues(this.getValues_Str()));
            return;
        }
    }

    /**
     * 处理请求中的信息
     * @param clientSocket
     * @throws IOException
     */
    public Request(Socket clientSocket) throws IOException {
        this.setSocket(clientSocket);
        //设置输出流
        this.setPrint(new PrintStream(clientSocket.getOutputStream()));
        //获得请求输入流
        this.setInput(clientSocket.getInputStream());
        //读取输入流信息
        this.setBuffer(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
        //得到请求信息
        String firstLineInData = this.buffer.readLine();
        //得到请求方式
        this.setSchema(this.messageUtil.getSchema(firstLineInData));
        this.doSchema(firstLineInData);
    }
}
