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

import LooluServer.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private Thread thread;
    private int flag;
    private LogPanel panel = new LogPanel();

    public ControlPanel() {
        //构造按钮
        makeButton("启动", 1);
        makeButton("停止", 0);
        makeButton("重启", -1);
    }

    /**
     * 构造按钮
     * @param name
     * @param status
     */
    public void makeButton(String name, int status) {
        JButton button = new JButton(name);
        add(button);
        flag = 0;
        //监听事件
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Server Loolu = new Server();
                if (status == 1) {
                    //TODO START
                    if (flag != 1) {
                        thread = new Thread(){
                            public void run(){
                                Loolu.startServer();
                            }
                        };
                        thread.start();
                        flag = 1;
                        JOptionPane.showMessageDialog(null, "服务器启动成功", "服务器",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "服务器处于打开状态", "服务器",JOptionPane.WARNING_MESSAGE);
                    }
                } else if (status == 0) {
                    //TODO STOP
                    if (flag == 1) {
                        Loolu.stopServer();
                        thread.stop();
                        flag = 0;
                        JOptionPane.showMessageDialog(null, "服务器关闭成功", "服务器",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "服务器未启动", "服务器",JOptionPane.WARNING_MESSAGE);
                    }
                } else if (status == -1) {
                    //TODO RESTART
                    if (flag == 1) {
                        thread.stop();
                        thread = new Thread(){
                            public void run(){
                                Loolu.startServer();
                            }
                        };
                        thread.start();
                        flag = 1;
                        JOptionPane.showMessageDialog(null, "服务器重新启动成功", "服务器",JOptionPane.INFORMATION_MESSAGE);
                    } else if (flag == 0) {
                        thread = new Thread(){
                            public void run(){
                                Loolu.startServer();
                            }
                        };
                        thread.start();
                        flag = 1;
                        JOptionPane.showMessageDialog(null, "服务器重新启动成功", "服务器",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "服务器启动异常", "服务器",JOptionPane.WARNING_MESSAGE);
                        flag = 999;
                    }
                } else {
                    //ERROR
                    JOptionPane.showMessageDialog(null, "服务器启动异常", "服务器",JOptionPane.WARNING_MESSAGE);
                    flag = 999;
                }
            }
        });
    }
}
