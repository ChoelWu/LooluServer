package LooluServer;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyFile {
    /**
     * 文件追加内容
     * @param FileName
     * @param Append
     * @param Content
     */
    public void appendContent(String FileName, boolean Append, String Content) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FileName, Append), true);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
            out.println(date + "    " + Content);
            out.close();
        } catch(Exception e) {

        }
    }

    /**
     * 获取日志文件的部分内容
     * @param FileName
     * @return
     */
    public String[] getFileContent(String FileName) {
            List<String> logContent = new ArrayList<>();
        try {
            Scanner in = new Scanner(new FileInputStream(FileName), "UTF-8");
            for (int i = 0; i < 10000 ; i ++) {
                logContent.add(in.nextLine());
                if (!in.hasNext()) {
                    break;
                }
            }
        } catch(Exception e) {

        }
        String[] retuenRawText = logContent.toArray(new String[logContent.size()]);
        if(retuenRawText.length < 15) {
            return retuenRawText;
        } else {
            String[] retuenText = new String[15];
            for(int i = retuenRawText.length - 15; i < retuenRawText.length; i ++) {
                retuenText[i + 15 - retuenRawText.length] = retuenRawText[i];
            }
            return retuenRawText;
        }
    }
}