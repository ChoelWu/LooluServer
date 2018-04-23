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

import LooluServer.IPControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SingleIPPanel extends JPanel {
    private static final String CONF_FILE_NAME = "src/resources/conf/IPlimit.conf";;

    public SingleIPPanel() {
        //设置布局类型
        setLayout(new BorderLayout());

        JLabel startIPLabel = new JLabel("起始地址");
        JLabel endIPLabel = new JLabel("   -   结束地址(不包含)");
        JTextField startIPTestField = new JTextField("", 8);
        JTextField endIPTestField = new JTextField("", 8);

        JPanel topPanel = new JPanel();

        topPanel.add(startIPLabel);
        topPanel.add(startIPTestField);
        topPanel.add(endIPLabel);
        topPanel.add(endIPTestField);

        JPanel bottomPanel = new JPanel();
        JButton button = new JButton("确定");

        //实现确定按钮监听事件
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean signal = false;
                String startIP = startIPTestField.getText().trim();
                String endIP = endIPTestField.getText().trim();
                IPControl ipset = new IPControl();
                signal = ipset.CheckIPObeyRules(startIP, endIP);
                if (signal) {
                    List<String> IPList = new ArrayList<>();
                    String content = "";
                    IPList = ipset.AnalysisIPSection(startIP, endIP);
                    for(String item : IPList) {
                        content += item + "\n";
                    }
                    ipset.WriteInConfFile(CONF_FILE_NAME, content);
                    //设置成功信息提示框
                    JOptionPane.showMessageDialog(null, "IP限制设置成功", "IP限制",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //设置失败信息提示框
                    JOptionPane.showMessageDialog(null, "IP限制设置失败", "IP限制",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        bottomPanel.add(button);

        //实现布局
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
