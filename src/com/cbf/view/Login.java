/*
 * Created by JFormDesigner on Wed Nov 04 19:14:14 GMT+08:00 2020
 */

package com.cbf.view;

import com.cbf.entity.Staff;
import com.cbf.service.StaffService;
import com.cbf.service.impl.StaffServiceImpl;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.*;

/**登录
 * @author Brainrain
 */
public class Login extends JFrame {
    public static Staff staffLogin; // 全局变量， 登录时保存的id

    public Login() {
        initComponents();
        init();
    }


    public void init() {//可显
        this.setVisible(true);
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        StaffService staffService = new StaffServiceImpl();
        //获取三个文本框的内容
        String userNameStr = userName.getText();
        String passWordStr = password.getText();
        String userTypeStr = userType.getSelectedItem().toString();
        //设置用户名不少于6位且不能含有空格正则
        String str = "^[^ ]+\\w{5,}$";
        //判断符不符合正则表达式
        boolean b1 = Pattern.matches(str, userNameStr);
        boolean b2 = Pattern.matches(str, passWordStr);
        if (!b1) {
            JOptionPane.showMessageDialog(null, "用户名不少于6位且不能含有空格");
            //清空输入框
            userName.setText("");
        } else if (!b2) {
            JOptionPane.showMessageDialog(null, "密码不少于6位且不能含有空格");
            //清空输入框
            password.setText("");
        } else if (b1 == b2 == true) {
            Staff staff = new Staff();
            //将三个内容都传入staff
            staff.setUserName(userNameStr);
            staff.setPassWord(passWordStr);
            staff.setUserType(userTypeStr);
            //将staff 传入stafflist 进行查询
            List<Staff> staffList = staffService.querystaff(userNameStr, passWordStr, userTypeStr);
//如果不为空，且存在，且usertyperstr为管理员
            if (staffList != null && staffList.size() > 0 && userTypeStr.equals("管理员")) {
                staffLogin = staffList.get(0); //保存登录账户
                JOptionPane.showMessageDialog(null, "管理员登录成功！！");
                //跳转管理员MainJrame框
                MainJrame mainJrame = new MainJrame();
                //关闭当前窗口
                this.setVisible(false);
            } //如果不为空，且存在，且usertyperstr为员工
            else if (staffList != null && staffList.size() > 0 && userTypeStr.equals("员工")) {
                staffLogin = staffList.get(0); //保存登录账户
                JOptionPane.showMessageDialog(null, "员工登录成功！！");
                //打开员工StaffUser跳转框
                StaffUser staffUser = new StaffUser();
                //关闭当前框
                this.setVisible(false);

            } else {
                //提示框
                JOptionPane.showMessageDialog(null, "账户或密码错误！！");
                return;
            }

        }
    }


    private void button2ActionPerformed(ActionEvent e) {//取消
        // TODO add your code here
        //清空所有
        userName.setText("");
        password.setText("");
    }

    private void button3ActionPerformed(ActionEvent e) {//点错了
        // TODO add your code here
        //退出系统
        System.exit(0);
    }

    private void button5ActionPerformed(ActionEvent e) { //忘记密码
        // TODO add your code here
        //跳转找回密码界面
        Zhaohui zhaohui = new Zhaohui();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label7 = new JLabel();
        label6 = new JLabel();
        userName = new JTextField();
        password = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        userType = new JComboBox<>();
        button3 = new JButton();
        button5 = new JButton();
        label5 = new JLabel();

        //======== this ========
        setTitle("\u767b\u5f55");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label7 ----
            label7.setText("\u5bc6\u7801");
            label7.setIcon(new ImageIcon(getClass().getResource("/008.png")));
            label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 2f));
            panel1.add(label7);
            label7.setBounds(50, 115, 105, label7.getPreferredSize().height);

            //---- label6 ----
            label6.setText("\u7528\u6237\u540d");
            label6.setIcon(new ImageIcon(getClass().getResource("/007.png")));
            label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 1f));
            panel1.add(label6);
            label6.setBounds(50, 50, 105, label6.getPreferredSize().height);
            panel1.add(userName);
            userName.setBounds(170, 50, 220, 35);
            panel1.add(password);
            password.setBounds(170, 120, 220, 35);

            //---- button1 ----
            button1.setText("\u786e\u5b9a");
            button1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
            button1.addActionListener(e -> button1ActionPerformed(e));
            panel1.add(button1);
            button1.setBounds(135, 285, 90, 40);

            //---- button2 ----
            button2.setText("\u53d6\u6d88");
            button2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
            button2.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button2);
            button2.setBounds(335, 285, 90, 40);

            //---- userType ----
            userType.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u5458\u5de5",
                "\u7ba1\u7406\u5458"
            }));
            userType.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(userType);
            userType.setBounds(170, 200, 135, 35);

            //---- button3 ----
            button3.setText("\u70b9\u9519\u4e86 ");
            button3.addActionListener(e -> button3ActionPerformed(e));
            panel1.add(button3);
            button3.setBounds(465, 15, 80, 25);

            //---- button5 ----
            button5.setText("\u5fd8\u8bb0\u5bc6\u7801\uff1f");
            button5.setFont(button5.getFont().deriveFont(button5.getFont().getSize() + 1f));
            button5.addActionListener(e -> button5ActionPerformed(e));
            panel1.add(button5);
            button5.setBounds(new Rectangle(new Point(420, 125), button5.getPreferredSize()));

            //---- label5 ----
            label5.setText("text");
            label5.setIcon(new ImageIcon(getClass().getResource("/02.jpg")));
            panel1.add(label5);
            label5.setBounds(0, 0, 560, 375);

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
        panel1.setBounds(0, 0, 560, 375);

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
    private JLabel label7;
    private JLabel label6;
    private JTextField userName;
    private JTextField password;
    private JButton button1;
    private JButton button2;
    private JComboBox<String> userType;
    private JButton button3;
    private JButton button5;
    private JLabel label5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
