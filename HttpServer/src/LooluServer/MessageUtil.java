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

import java.util.HashMap;
import java.util.Map;

public class MessageUtil {

    /**
     * 判断出时GET请求还是POST请求
     * @param requestMsg  请求头信息
     * @return requestMsg 请求方式
     */
    public String getSchema(String requestMsg) {
        //初始化一个长度为1的数组
        String[] result = new String[1];
        try {
            if (requestMsg.contains(" ")) {
                //从空格处奖请求分割，获得请求方式
                result = requestMsg.split(" ");
                requestMsg = result[0];
            }
        } catch(Exception e) {
            e.getStackTrace();
        }
        //返回请求方式
        return requestMsg;
    }

    /**
     * 得到请求内容
     * @param firstLineInData
     * @return
     */
    public String getRequestData(String firstLineInData) {
        String[] result = new String[10];
        result = firstLineInData.split(" ");
        firstLineInData = result[1].substring(1);
        return firstLineInData;
    }


    /**
     * 得到请求的文件的地址
     * @param requestData
     * @return
     */
    public String getFileName(String requestData) {
        String[] result = new String[10];
        result = requestData.split("[?]");
        return result[0];
    }

    /**
     * 获得请求中的参数
     * @param requestData
     * @return
     */
    public Map<String, String> getValues(String requestData) {
        //参数Map集合
        Map<String, String> values = new HashMap<String, String>();
        String[] result = new String[10];
        String regex = "[&=]";
        if (requestData.contains("?")) {
            result = requestData.split("[?]");
            String data_List = result[1];
            result = data_List.split(regex);
            //将参数依次压入集合中
            for (int i = 0; i < result.length - 1; i += 2) {
                values.put(result[i], result[i + 1]);
            }
            return values;
        } else {
            result = requestData.split(regex);
            for (int i = 0; i < result.length - 1; i += 2) {
                values.put(result[i], result[i + 1]);
            }
            return values;
        }
    }
}