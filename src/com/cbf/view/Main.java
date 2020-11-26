package com.cbf.view;

import javax.swing.*;

/**
 * 作者：chenbingfeng
 * 日期: 2020/11/4 18:39
 * 描述:主函数
 */
public class Main {
    public static void main(String[] args) {
        //美化界面
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        //首先打开login登录窗口
        Login login=new Login();
    }
}
