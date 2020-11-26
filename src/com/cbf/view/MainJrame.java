/*
 * Created by JFormDesigner on Wed Nov 04 18:43:26 GMT+08:00 2020
 */

package com.cbf.view;

import sun.rmi.runtime.Log;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** 管理员界面
 * @author Brainrain
 */
public class MainJrame extends JFrame {

    public MainJrame() {
        initComponents();
        init();
    }

    public void init() {//可显
        this.setVisible(true);
    }

    private void menuItem1ActionPerformed(ActionEvent e) { //添加用户
        //跳转添加用户界面
        AddStaff addStaff = new AddStaff();
        // TODO add your code here
    }

    private void menu3ActionPerformed(ActionEvent e) {


        // TODO add your code here
    }

    private void menu2ActionPerformed(ActionEvent e) {

    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        // TODO add your code here

    }

    private void menuItem4ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void menuItem2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        //跳转查看工资界面
        SalaryList salaryList = new SalaryList();

    }

    private void menuItem5ActionPerformed(ActionEvent e) {
        // TODO add your code here
        //跳转添加工资界面
        AddSalary addSalary = new AddSalary();

    }

    private void menuItem6ActionPerformed(ActionEvent e) {
        // TODO add your code here
        //跳转查看用户界面
        StaffList staffList = new StaffList();


    }

    private void button1ActionPerformed(ActionEvent e) {// 退出系统
        // TODO add your code here
        System.exit(0);
    }

    private void button2ActionPerformed(ActionEvent e) {//返回登录界面
        // TODO add your code here
        this.setVisible(false);//关闭当前窗口
        Login login = new Login();// 打开登录窗口
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem6 = new JMenuItem();
        menu4 = new JMenu();
        menuItem5 = new JMenuItem();
        menuItem2 = new JMenuItem();
        button1 = new JButton();
        button2 = new JButton();
        panel1 = new JPanel();
        label1 = new JLabel();

        //======== this ========
        setTitle("\u7ba1\u7406\u5458\u64cd\u4f5c");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u7528\u6237\u7ba1\u7406");
                menu1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem1 ----
                menuItem1.setText("\u6dfb\u52a0\u7528\u6237");
                menuItem1.setFont(menuItem1.getFont().deriveFont(menuItem1.getFont().getSize() + 2f));
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu1.add(menuItem1);

                //---- menuItem6 ----
                menuItem6.setText("\u67e5\u770b\u7528\u6237");
                menuItem6.setFont(menuItem6.getFont().deriveFont(menuItem6.getFont().getSize() + 2f));
                menuItem6.addActionListener(e -> menuItem6ActionPerformed(e));
                menu1.add(menuItem6);
            }
            menuBar1.add(menu1);

            //======== menu4 ========
            {
                menu4.setText("\u5de5\u8d44\u7ba1\u7406");
                menu4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem5 ----
                menuItem5.setText("\u6dfb\u52a0\u5de5\u8d44");
                menuItem5.setFont(menuItem5.getFont().deriveFont(menuItem5.getFont().getSize() + 2f));
                menuItem5.addActionListener(e -> menuItem5ActionPerformed(e));
                menu4.add(menuItem5);

                //---- menuItem2 ----
                menuItem2.setText("\u67e5\u770b\u5de5\u8d44");
                menuItem2.setFont(menuItem2.getFont().deriveFont(menuItem2.getFont().getSize() + 2f));
                menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                menu4.add(menuItem2);
            }
            menuBar1.add(menu4);

            //---- button1 ----
            button1.setText("\u9000\u51fa\u7cfb\u7edf");
            button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
            button1.addActionListener(e -> button1ActionPerformed(e));
            menuBar1.add(button1);

            //---- button2 ----
            button2.setText("\u8fd4\u56de\u767b\u5f55\u754c\u9762");
            button2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
            button2.addActionListener(e -> button2ActionPerformed(e));
            menuBar1.add(button2);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("text");
            label1.setIcon(new ImageIcon(getClass().getResource("/0001.jpg")));
            panel1.add(label1);
            label1.setBounds(0, 0, 770, 410);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 765, 410);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem6;
    private JMenu menu4;
    private JMenuItem menuItem5;
    private JMenuItem menuItem2;
    private JButton button1;
    private JButton button2;
    private JPanel panel1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}

