/*
 * Created by JFormDesigner on Fri Nov 06 09:28:34 GMT+08:00 2020
 */

package com.cbf.view;

import com.cbf.entity.Staff;
import sun.rmi.runtime.Log;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

/** 员工界面
 * @author Brainrain
 */
public class StaffUser extends JFrame {

    public StaffUser() {
        initComponents();
        init();
    }

    public void init() {
        this.setVisible(true);//可显
        label2.setText("当前用户：" + Login.staffLogin.getRealName());
        //时间
        Timer time = new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                label3.setText(new SimpleDateFormat("当前时间"+"yyyy年MM月dd日 EEEE hh:mm:ss").format(new Date()));
            }
        });
        time.start();//时间
        //时间格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        label3.setText("登录时间：" + df.format(new Date()));
//        textField1.setText("登录时间："+df.format(new Date()));

    }

    private void menu1ActionPerformed(ActionEvent e) {
        // TODO add your code here

    }

    private void menuItem1ActionPerformed(ActionEvent e) {//工资查询
        // TODO add your code here
        StaffUserList staffUserList = new StaffUserList();
    }

    private void menuItem2ActionPerformed(ActionEvent e) {//修改密码
        // TODO add your code here
        // 获取登录时， 存储的id
        Integer staff = Login.staffLogin.getId();

        //跳转到password，并传一个staff过去
        Password password = new Password(staff);

    }

    private void menu3ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button1ActionPerformed(ActionEvent e) { // 退出系统

        System.exit(0);
    }

    private void menuItem3ActionPerformed(ActionEvent e) {
        // TODO add your code here


    }

    private void textField1ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button2ActionPerformed(ActionEvent e) {//返回登录界面
        // TODO add your code here
        this.setVisible(false);
        Login login=new Login();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menu2 = new JMenu();
        menuItem2 = new JMenuItem();
        button1 = new JButton();
        button2 = new JButton();
        panel1 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label1 = new JLabel();

        //======== this ========
        setTitle("\u5458\u5de5\u64cd\u4f5c");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u5de5\u8d44");
                menu1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menu1.addActionListener(e -> menu1ActionPerformed(e));

                //---- menuItem1 ----
                menuItem1.setText("\u5de5\u8d44\u67e5\u8be2");
                menuItem1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
                menuItem1.addActionListener(e -> menuItem1ActionPerformed(e));
                menu1.add(menuItem1);
            }
            menuBar1.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u8d26\u53f7\u5b89\u5168");
                menu2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem2 ----
                menuItem2.setText("\u4fee\u6539\u5bc6\u7801");
                menuItem2.setFont(menuItem2.getFont().deriveFont(menuItem2.getFont().getSize() + 1f));
                menuItem2.addActionListener(e -> menuItem2ActionPerformed(e));
                menu2.add(menuItem2);
            }
            menuBar1.add(menu2);

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

            //---- label2 ----
            label2.setText("text");
            label2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
            panel1.add(label2);
            label2.setBounds(5, 0, 225, 30);

            //---- label3 ----
            label3.setText("text");
            label3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
            panel1.add(label3);
            label3.setBounds(315, 5, 310, 25);

            //---- label1 ----
            label1.setText("text");
            label1.setIcon(new ImageIcon(getClass().getResource("/034.jpg")));
            panel1.add(label1);
            label1.setBounds(-10, 35, 640, 365);

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
        panel1.setBounds(0, 0, 630, 400);

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
    private JMenu menu2;
    private JMenuItem menuItem2;
    private JButton button1;
    private JButton button2;
    private JPanel panel1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
