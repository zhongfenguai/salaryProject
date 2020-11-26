/*
 * Created by JFormDesigner on Fri Nov 06 10:07:03 GMT+08:00 2020
 */

package com.cbf.view;

import java.awt.event.*;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.utils.StringUtils;
import com.cbf.entity.Staff;
import com.cbf.service.StaffService;
import com.cbf.service.impl.StaffServiceImpl;
import com.cbf.util.SmsUtil;

import java.awt.*;
import java.util.List;
import javax.swing.*;

/**修改密码
 * @author Brainrain
 */
public class Password extends JFrame {
//    private static String random;
//    public static Staff sa;
    private Thread wait=new Thread(new Wait());

    StaffService staffService = new StaffServiceImpl();
    private Integer id; // 全局一个id

    //    Integer staff=Login.staffLogin.getId();
    public Password(Integer staff) {
        this.id = staff;
        initComponents();
        init();

    }

//    public static void rand() {
//        String s = "";
//        while (s.length() < 6)
//            s += (int) (Math.random() * 10);
//        random = s;
//
//    }

    public void init() {//可显
        this.setVisible(true);
//传入id 进行查询
        List<Staff> list = staffService.querystaffbyId(id);
        if (list != null) {
            //获取第一行
            Staff staff = list.get(0);
            //设置原密码，设置初始化窗口
            textField1.setText(staff.getPassWord());//原密码
        }
    }

    private void button1ActionPerformed(ActionEvent e) {//确定
        // TODO add your code here
        //获取textField2文本框的内容
        String passwordStr = textField2.getText();
        //如果textField2 为空
        if (StringUtils.isEmpty(passwordStr)) {
            JOptionPane.showMessageDialog(null, "新密码不能为空");
            return;
        }
        //如果textField1 为空
        String userNameStr = textField1.getText();
        if (StringUtils.isEmpty(userNameStr) && userNameStr.equals(Login.staffLogin.getPassWord())) {
            JOptionPane.showMessageDialog(null, "原密码错误");
            return;
        }
//如果 textField3 为空
        String passwordStr1 = textField3.getText();
        if (StringUtils.isEmpty(passwordStr1) && passwordStr1 != passwordStr) {
            JOptionPane.showMessageDialog(null, "确认密码为空或不一样");
            return;
        }
        //获取textField4文本框的内容
        Integer phonestr = Integer.valueOf(textField4.getText());//转型成interger类型
        // 判断验证码对不对
        if (phonestr.equals(48485)) {
            Staff staff = new Staff();
            //将新密码，和id 传入staff
            staff.setPassWord(passwordStr);
            staff.setId(this.id);
            //判断是否修改完成
            boolean b = staffService.updateStaff(staff);
            if (b) {
                JOptionPane.showMessageDialog(null, "修改成功");
                this.setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "修改失败");
        }
    }

    private void button3ActionPerformed(ActionEvent e) {//发送验证码
        // TODO add your code here

        try {
            SmsUtil.sendSms("15259565861", "48485");
            wait.start();
        } catch (ClientException e1) {
            e1.printStackTrace();
        }
    }
        class Wait extends Thread{
            @Override
            public void run() {
                for (int i = 60; i >= 0; i--) {
                    button3.setText("下次发送验证码" + i+"s");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    button3.setText("发送验证码");
                    button3.setEnabled(true);//变灰色
                }
            }
        }


    private void button2ActionPerformed(ActionEvent e) {//取消
        // TODO add your code here
        this.setVisible(false);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        button1 = new JButton();
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label4 = new JLabel();
        textField3 = new JTextField();
        button2 = new JButton();
        textField4 = new JTextField();
        button3 = new JButton();
        label3 = new JLabel();

        //======== this ========
        setTitle("\u4fee\u6539\u5bc6\u7801");
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

            //---- label1 ----
            label1.setText("\u539f\u5bc6\u7801");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 2f));
            panel1.add(label1);
            label1.setBounds(new Rectangle(new Point(100, 60), label1.getPreferredSize()));
            panel1.add(textField1);
            textField1.setBounds(200, 55, 130, textField1.getPreferredSize().height);

            //---- label2 ----
            label2.setText("\u65b0\u5bc6\u7801");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 2f));
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(100, 115), label2.getPreferredSize()));
            panel1.add(textField2);
            textField2.setBounds(200, 175, 130, textField2.getPreferredSize().height);

            //---- label4 ----
            label4.setText("\u786e\u8ba4\u65b0\u5bc6\u7801");
            label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 2f));
            panel1.add(label4);
            label4.setBounds(new Rectangle(new Point(100, 180), label4.getPreferredSize()));
            panel1.add(textField3);
            textField3.setBounds(200, 110, 130, textField3.getPreferredSize().height);

            //---- button2 ----
            button2.setText("\u53d6\u6d88");
            button2.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button2);
            button2.setBounds(305, 285, 70, 40);
            panel1.add(textField4);
            textField4.setBounds(200, 220, 130, textField4.getPreferredSize().height);

            //---- button3 ----
            button3.setText("\u53d1\u9001\u624b\u673a\u9a8c\u8bc1\u7801");
            button3.addActionListener(e -> button3ActionPerformed(e));
            panel1.add(button3);
            button3.setBounds(370, 215, 135, 45);

            //---- label3 ----
            label3.setText("\u9a8c\u8bc1\u7801");
            label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 2f));
            panel1.add(label3);
            label3.setBounds(new Rectangle(new Point(100, 225), label3.getPreferredSize()));

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
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label4;
    private JTextField textField3;
    private JButton button2;
    private JTextField textField4;
    private JButton button3;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
