/*
 * Created by JFormDesigner on Mon Nov 09 19:03:24 GMT+08:00 2020
 */

package com.cbf.view;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.utils.StringUtils;
import com.cbf.entity.Staff;
import com.cbf.service.StaffService;
import com.cbf.service.impl.StaffServiceImpl;
import com.cbf.util.SmsUtil;
import com.sun.deploy.util.Waiter;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/** 找回密码
 * @author Brainrain
 */
public class Zhaohui extends JFrame {
    //创建一个线程
    private Thread wait=new Thread(new Wait());
    StaffService staffService = new StaffServiceImpl();

    private static String random;//全局一个随机数
    public static Staff sa; //

    public Zhaohui() {
        initComponents();
        init();
    }

    public void init() {
        this.setVisible(true);//可显
    }

    // 产生随机数+的方法
    public static void rand() {
        String s = "";
        while (s.length() < 6)
            s += (int) (Math.random() * 10);
        random = s;

    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        //获取username文本框的内容
        String username = username2.getText();
        if (StringUtils.isEmpty(username)) {
            JOptionPane.showMessageDialog(null, "用户名不能为空");
            return;
        }
        //获取password文本框的内容
        String passwordStr = password.getText();
        if (StringUtils.isEmpty(passwordStr)) {
            JOptionPane.showMessageDialog(null, "新密码不能为空");
            return;
        }
        //获取password2 文本框的内容
        String passwordStr1 = password2.getText();
        if (StringUtils.isEmpty(passwordStr1) && passwordStr1 != passwordStr) {
            JOptionPane.showMessageDialog(null, "确认密码为空或不一样");
            return;
        }
        //获取phone 文本框的内容
        String phonestr = phone.getText();
        //判断验证码是否正确
        if (phonestr.equals(random)) {
            Staff staff = new Staff();
            //设置密码和id 传入staff 进行更改
            staff.setPassWord(passwordStr);
            staff.setId(sa.getId());
            //去更改密码
            boolean b = staffService.updateStaff(staff);
            if (b) {
                JOptionPane.showMessageDialog(null, "修改成功");
                this.setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "修改失败");
        }
    }


    private void button3ActionPerformed(ActionEvent e) {
        // TODO add your code here
        //获取username文本框的值
        String username = username2.getText();
        List<Staff> list1 = staffService.querystaff10(username);
        sa = list1.get(0);//获取第一行
        rand();//随机一个验证码
        try {
            //发送短信
            SmsUtil.sendSms(sa.getTelephone(), Zhaohui.random);
            //变灰 失去功能
            button3.setEnabled(false);
            //进入线程等待
            wait.start();
        } catch (ClientException e1) {
            e1.printStackTrace();
        }
    }
        class Wait extends Thread{
            @Override
            public void run() {
                //等待60s
                for (int i = 60; i >= 0; i--) {
                    button3.setText("下次发送验证码" + i+"s");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    //60s 后恢复原样
                    button3.setText("发送验证码");
                    button3.setEnabled(true);//变灰色
                }
            }
        }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        button1 = new JButton();
        label2 = new JLabel();
        password2 = new JTextField();
        label4 = new JLabel();
        password = new JTextField();
        button2 = new JButton();
        phone = new JTextField();
        button3 = new JButton();
        label1 = new JLabel();
        label3 = new JLabel();
        username2 = new JTextField();

        //======== this ========
        setTitle("\u627e\u56de\u5bc6\u7801");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- button1 ----
            button1.setText("\u786e\u8ba4");
            button1.addActionListener(e -> button1ActionPerformed(e));
            panel1.add(button1);
            button1.setBounds(165, 285, 70, 40);

            //---- label2 ----
            label2.setText("\u65b0\u5bc6\u7801");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 2f));
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(100, 95), label2.getPreferredSize()));
            panel1.add(password2);
            password2.setBounds(200, 155, 130, password2.getPreferredSize().height);

            //---- label4 ----
            label4.setText("\u786e\u8ba4\u65b0\u5bc6\u7801");
            label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 2f));
            panel1.add(label4);
            label4.setBounds(new Rectangle(new Point(100, 155), label4.getPreferredSize()));
            panel1.add(password);
            password.setBounds(200, 90, 130, password.getPreferredSize().height);

            //---- button2 ----
            button2.setText("\u53d6\u6d88");
            panel1.add(button2);
            button2.setBounds(305, 285, 70, 40);
            panel1.add(phone);
            phone.setBounds(200, 210, 130, phone.getPreferredSize().height);

            //---- button3 ----
            button3.setText("\u53d1\u9001\u624b\u673a\u9a8c\u8bc1\u7801");
            button3.addActionListener(e -> button3ActionPerformed(e));
            panel1.add(button3);
            button3.setBounds(370, 200, 145, 45);

            //---- label1 ----
            label1.setText("\u5bc6\u4fdd\u624b\u673a");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 2f));
            panel1.add(label1);
            label1.setBounds(new Rectangle(new Point(100, 210), label1.getPreferredSize()));

            //---- label3 ----
            label3.setText("\u7528\u6237\u540d");
            label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 2f));
            panel1.add(label3);
            label3.setBounds(100, 45, 43, 19);
            panel1.add(username2);
            username2.setBounds(200, 45, 130, 25);

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
        panel1.setBounds(0, 0, 615, 425);

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
    private JPanel panel1;
    private JButton button1;
    private JLabel label2;
    private JTextField password2;
    private JLabel label4;
    private JTextField password;
    private JButton button2;
    private JTextField phone;
    private JButton button3;
    private JLabel label1;
    private JLabel label3;
    private JTextField username2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
