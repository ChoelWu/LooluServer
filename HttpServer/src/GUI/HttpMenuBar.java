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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpMenuBar extends JMenuBar {
    public HttpMenuBar() {
        //创建菜单
        JMenu menu = new JMenu("      菜单       ");

        //创建菜单项目   端口设置
        JMenuItem portItem = new JMenuItem("端口设置");
        //监听器设置
        portItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //打开新的Frame
                PortFrame portFrame = new PortFrame();
                portFrame.setTitle("端口设置");
                portFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            }
        });
        menu.add(portItem);

        //创建二级菜单   IP限制
        JMenu IPMenu = new JMenu("IP限制");
        //创建菜单项目   连续/单个IP地址操作
        JMenuItem SingleIPItem = new JMenuItem("连续/单个IP地址操作");
        SingleIPItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SingleIPFrame singleIPFrame = new SingleIPFrame();
                singleIPFrame.setSize(400, 200);
                singleIPFrame.setTitle("连续/单个IP地址操作");
                singleIPFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            }
        });
        IPMenu.add(SingleIPItem);
        //创建菜单项目   批量IP地址操作
        JMenuItem PlentyIPItem = new JMenuItem("批量IP地址操作");
        PlentyIPItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("explorer /select, " + "E:\\HttpServer\\src\\resources\\conf\\IPlimit.conf");
                } catch(Exception e1) {

                }
            }
        });
        IPMenu.add(PlentyIPItem);
        //添加分割线
        menu.addSeparator();
        menu.add(IPMenu);


        //创建二级菜单   日志设置
        JMenu logMenu = new JMenu("日志设置");
        //创建菜单项目   打开日志文件
        JMenuItem openLogItem = new JMenuItem("打开日志文件");
        openLogItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("explorer /select, " + "E:\\HttpServer\\src\\resources\\log\\log.txt");
                } catch(Exception e1) {

                }
            }
        });
        logMenu.add(openLogItem);
        //创建菜单项目   设置日志文件位置
        JMenuItem menuLocation = new JMenuItem("设置日志文件位置");
        menuLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileChoose flc = new FileChoose();
                System.out.println(flc.getPath());
                try {
                    String startPath = "E:/HttpServer/src/resources/log/log.txt";
                    String endPath = flc.getPath() + "/log.txt";
                    Path startFile = FileSystems.getDefault().getPath(startPath);
                    Path tmpFile = FileSystems.getDefault().getPath(endPath);//获取文件夹路径
                    Path path = Files.move(startFile, tmpFile);
                } catch(Exception e1) {

                }

            }
        });
        logMenu.add(menuLocation);
        //添加分割线
        menu.addSeparator();
        menu.add(logMenu);
        //添加分割线
        menu.addSeparator();

        //创建菜单项目   配置文件
        JMenuItem confItem = new JMenuItem("配置文件");
        confItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("explorer /select, " + "E:\\HttpServer\\src\\resources\\conf\\server.conf");
                } catch(Exception e1) {

                }

            }
        });
        menu.add(confItem);

        //创建菜单项目   允许/不允许远程访问
        JMenuItem on_off_LineItem = new JMenuItem("允许/不允许远程访问");
        on_off_LineItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "已经切换至XX状态", "远程访问设置",JOptionPane.WARNING_MESSAGE);
            }
        });
        menu.add(on_off_LineItem);

        add(menu);
    }
}
