/*
    +----------------------------------------------------------------------
    + LooluServer
    +----------------------------------------------------------------------
    + Create Info: 2018-02-28 By Charles Wu <775669127@qq.com>
    +----------------------------------------------------------------------
    + Update Info 1:
    + Update Info 2:
    +----------------------------------------------------------------------
*/

package GUI;

import javax.swing.*;
import java.awt.*;

public class RunGUI {
    public static void main(String[] args) {
        //创建UI线程
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //创建对象
                JFrame frame = new HttpFrame();
            }
        });
    }
}
