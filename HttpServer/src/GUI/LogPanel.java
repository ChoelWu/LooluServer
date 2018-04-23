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

import LooluServer.MyFile;

import javax.swing.*;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LogPanel extends JPanel {
    private static final String LOG_FILE_NAME = "src/resources/log/log.txt";
    private JTextArea textArea;

    public LogPanel() {
        JLabel label = new JLabel("服务器详情： ", SwingConstants.LEFT);
        textArea = new JTextArea("", 12, 40);
        //禁止编辑
        textArea.setEditable(false);
        //加入滑动条
        JScrollPane scrollPane = new JScrollPane(textArea);
        //自动换行
        textArea.setLineWrap(true);

        add(label);
        add(scrollPane);
        this.autoReFrash();
    }

    /**
     * 定时器刷新文本域
     */
    public void autoReFrash() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MyFile log = new MyFile();
                String[] logArr =  log.getFileContent(LOG_FILE_NAME);
                String printText = "";
                for (String item : logArr) {
                    printText += item + "\n";
                }
                textArea.setText(printText);
            }
        }, new Date(), 1000);
    }
}
