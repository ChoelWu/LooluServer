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
import java.util.Map;
import java.util.Properties;

public class LoginUtil {
    protected boolean flag = false;
    protected Map<String, String> values;
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src/resources/conf/server.conf")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    // 验证用户信息的合法性（应用JDBC桥，连接数据库）
    public boolean isValid(Map<String, String> values) {

        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        if (values.get("username").equals(username)) {
            if (values.get("password").equals(password)) {
                flag = true;
                System.out.println("The user " + values.get("username") + " was log the server.");
                return flag;
            }
        } else {
            System.out.println("Forbide the " + values.get("username") + " log the server");
            return flag;
        }
        return false;
    }
}