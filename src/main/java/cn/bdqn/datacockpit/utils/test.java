package cn.bdqn.datacockpit.utils;

import java.util.List;

public class test {
  /*  public static void main(String[] args) {
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read("D:/360Downloads/shujubiao.xlsx");
        String lists = list.toString();
        ChineseToPinYin ctp = new ChineseToPinYin();
        String pingyin = ctp.getPingYin(lists);
        System.out.println(pingyin);

    }*/
    
    public static void main(String[] args) {
        ImportExecl poi = new ImportExecl();
        List<List<String>> list = poi.read("D:/360Downloads/Software/360SoftMgrSafeRun/test1.xlsx");
        for (List<String> list2 : list) {
            for (String string : list2) {
                System.out.print(string+"\t");
            }
            System.out.println();
        }
        
       /* String lists = list.toString();
        ChineseToPinYin ctp = new ChineseToPinYin();
        String pingyin = ctp.getPingYin(lists);
        System.out.println(pingyin);*/
    }
    
    
    
}
