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

public class StatisticPanel extends JPanel {

    public StatisticPanel() {
        //标签组布局
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //构造标签组
        makeCouple("数据发送量： ", "16.3 MB", 6);
        makeCouple("数据接收量： ", "0.3MB", 6);
    }

    /**
     * 构造标签组
     * @param labelName
     * @param unit
     * @param columns
     */
    public void makeCouple(String labelName, String unit, int columns) {
        JPanel panel = new JPanel();
        //标签文字右对齐
        JLabel label = new JLabel(labelName, SwingConstants.RIGHT);
        JTextField textField = new JTextField("" + unit, columns);
        //将标签文本框组件加入面板
        panel.add(label);
        panel.add(textField);
        //文本框不可编辑
        textField.setEditable(false);
        //将面板加入父级面板
        add(panel);
    }
}
