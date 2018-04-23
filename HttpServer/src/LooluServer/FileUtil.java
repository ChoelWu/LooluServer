/*
    +----------------------------------------------------------------------
    + LooluServer
    +----------------------------------------------------------------------
    + Create Info: 2018-02-28 By Cheol Wu <775669127@qq.com>
    +----------------------------------------------------------------------
    + Update Info 1:
    + Update Info 2:
    +----------------------------------------------------------------------
*/

package LooluServer;

import java.io.*;

public class FileUtil {
    private static final int BUFFER_SIZE = 1024;

    public void readFile(String file_Name, PrintStream ps) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;
        File file = new File("src/resources/" + file_Name);
        FileInputStream fis = null;

        if (file.exists()) {
            try {
                fis = new FileInputStream(file);
                while ((length = fis.read(buffer)) != -1) {
                    ps.write(buffer, 0, length);
                    ps.flush();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            ps.println("File not found");
            ps.println();
        }
    }
}