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

import java.io.File;

import javax.swing.*;

public class FileChoose extends JFileChooser {
    private String path;

    /**
     * 文件选择器
     */
    public FileChoose(){
        JFileChooser jfc=new JFileChooser();
        //文件选择器的类型为  文件夹
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        //得到文件位置
        path = file.getAbsolutePath();
    }

    /**
     * 返回文件位置
     * @return
     */
    public String getPath() {
        return path;
    }
}