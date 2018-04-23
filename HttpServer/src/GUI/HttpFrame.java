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

public class HttpFrame extends CommonFrame {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;

    public HttpFrame() {
        JPanel panel = new HttpPanel();
        add(panel);
        pack();
        //设置菜单栏
        JMenuBar menuBar = new HttpMenuBar();
        this.setJMenuBar(menuBar);
        //设置Frame大小
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //设置frame位置
        setFrameLocation(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //设置框架是否可见
        setVisible(true);
    }
}
