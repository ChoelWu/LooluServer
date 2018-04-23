package LooluServer;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class IPControl {
    /**
     * 将IP段分解为单个IP组
     * @param StartIP
     * @param EndIP
     * @return
     */
    public List<String> AnalysisIPSection (String StartIP, String EndIP) {
        //202.201.52.135
        List<String> IPList = new ArrayList<>();
        if (StartIP.equals(EndIP)) {
            IPList.add(StartIP);
        } else {
            String[] StartIPArr = StartIP.split("\\.");
            String[] EndIPArr = EndIP.split("\\.");
            int[] StartIPArrInt = new int[4];
            int[] EndIPArrInt = new int[4];
            for (int i = 0; i < 4; i ++) {
                StartIPArrInt[i] = Integer.parseInt(StartIPArr[i]);
                EndIPArrInt[i] = Integer.parseInt(EndIPArr[i]);
            }
            do {
                IPList.add(String.valueOf(StartIPArrInt[0]) + "." + String.valueOf(StartIPArrInt[1]) + "." + String.valueOf(StartIPArrInt[2]) + "." + String.valueOf(StartIPArrInt[3]));
                if (StartIPArrInt[3] == 255) {
                    StartIPArrInt[2] ++;
                    StartIPArrInt[3] = 0;
                    if (StartIPArrInt[2] == 255) {
                        StartIPArrInt[1] ++;
                        StartIPArrInt[2] = 0;
                        if (StartIPArrInt[1] == 255) {
                            StartIPArrInt[0] ++;
                            StartIPArrInt[1] = 0;
                        }
                    }
                } else {
                    StartIPArrInt[3] ++;
                }
            } while(StartIPArrInt[3] != EndIPArrInt[3] || StartIPArrInt[2] != EndIPArrInt[2] || StartIPArrInt[1] != EndIPArrInt[1] || StartIPArrInt[0] != EndIPArrInt[0]);
        }
        return IPList;
    }

    /**
     * 检查IP是否合规范
     * @param StartIP
     * @param EndIP
     * @return
     */
    public boolean CheckIPObeyRules(String StartIP, String EndIP) {
        boolean flag = true;
        boolean IP_D = false;
        String[] StartIPArr = StartIP.split("\\.");
        String[] EndIPArr = EndIP.split("\\.");
        int[] StartIPArrInt = new int[4];
        int[] EndIPArrInt = new int[4];
        if (StartIPArr.length != 4 || EndIPArr.length != 4) {//IP格式不正确
            flag = false;
        } else {
            int startInt;
            int endInt;
            try {
                for (int i = 0; i < 4; i ++) {
                    startInt = Integer.parseInt(StartIPArr[i]);
                    endInt = Integer.parseInt(EndIPArr[i]);
                    if (startInt <= 255 && startInt >= 0 && endInt <= 255 && endInt >= 0) {
                        StartIPArrInt[i] = startInt;
                        EndIPArrInt[i] = endInt;
                        if(startInt < endInt && !IP_D) {
                            IP_D = true;
                        }
                    } else { //IP不规范
                        flag = false;
                        break;
                    }
                }
            } catch(Exception e) { //存在非法字符
                flag = false;
            }
        }
        if (IP_D) {
            return flag;
        } else {
            if (StartIP.equals(EndIP)) {
                return true;
            } else {
                return IP_D;
            }
        }
    }

    /**
     * IP限制规则写入配置文件
     * @param FileName
     * @param IPList
     */
    public void WriteInConfFile(String FileName, String IPList) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(FileName, true),true);
            out.println(IPList);
            out.close();
        } catch(Exception e) {

        }
    }
}
