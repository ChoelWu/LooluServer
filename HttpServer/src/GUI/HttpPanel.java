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

public class HttpPanel extends JPanel {
    public HttpPanel() {
        //设置布局格式为BoxLayout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //上边距为10
        add(Box.createVerticalStrut(10));
        //创建统计面板
        JPanel statisticPanel = new StatisticPanel();
        //创建日志面板
        JPanel logPanel = new LogPanel();
        //创建控制面板
        JPanel controlPanel = new ControlPanel();

        add(statisticPanel);
        add(logPanel);
        add(controlPanel);
    }
}
