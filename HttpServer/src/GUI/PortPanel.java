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

import LooluServer.ConfigPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PortPanel extends JPanel {
    private static ConfigPort setPort;
    private static String port;
    public PortPanel() {
        //设置布局格式为 BorderLayout
        setLayout(new BorderLayout());

        //创建上部面板
        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("端口：");
        JTextField field = new JTextField("", 8);
        topPanel.add(label);
        topPanel.add(field);

        //创建底部面板
        JPanel bottomPanel = new JPanel();
        JButton checkButton = new JButton("测试");
        JButton submitButton = new JButton("设置");

        //实现测试按钮监听事件
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPort = new ConfigPort();
                port = field.getText().trim();
                boolean signal = true;
                try {
                    signal = setPort.isPortUsing("127.0.0.1", Integer.parseInt(port));
                } catch (Exception ee) {

                }
                if (signal) {
                    JOptionPane.showMessageDialog(null, port + "端口可以使用", "端口测试",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, port + "端口已被占用", "端口测试",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //实现设置按钮监听事件
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPort = new ConfigPort();
                port = field.getText().trim();
                boolean signal = false;
                signal = setPort.SetPort(port);
                if (signal) {
                    JOptionPane.showMessageDialog(null, "端口设置成功", "端口设置",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "端口设置失败", "端口设置",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        bottomPanel.add(checkButton);
        bottomPanel.add(submitButton);

        //实现布局
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
