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

public class CommonFrame extends JFrame {

    public CommonFrame() {
        //设置图标
        Image ico = new ImageIcon("./img/ico.jpg").getImage();
        setIconImage(ico);
        //设置框架名
        setTitle("LooluServer");
        //设置默认的关闭方式
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //是否可以改变大小
        setResizable(false);
    }

    /**
     * 设置框架的位置在屏幕中间
     */
    public void setFrameLocation(int width, int height) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screen = kit.getScreenSize();
        int screenWidth = screen.width;
        int screenHeight = screen.height;
        setLocation((screenWidth - width) / 2, (screenHeight - height) / 2);
    }
}
