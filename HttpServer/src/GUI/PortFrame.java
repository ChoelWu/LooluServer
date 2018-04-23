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

public class PortFrame extends CommonFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 150;

    public PortFrame() {
        PortPanel PortPanel = new PortPanel();
        add(PortPanel);
        pack();
        //设置Frame大小
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //设置frame位置
        setFrameLocation(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //设置框架是否可见
        setVisible(true);
    }
}
