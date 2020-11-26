/*
 * Created by JFormDesigner on Wed Nov 04 23:30:25 GMT+08:00 2020
 */

package com.cbf.view;

import com.aliyuncs.utils.StringUtils;
import com.cbf.entity.Staff;
import com.cbf.service.StaffService;
import com.cbf.service.impl.StaffServiceImpl;
import sun.rmi.runtime.Log;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.*;

/**添加员工
 * @author Brainrain
 */
public class AddStaff extends JFrame {
    //构造函数
    public AddStaff() {
        initComponents();
        init();
    }

    public void init() {//可显
        this.setVisible(true);
    }


    private void button1ActionPerformed(ActionEvent e) {//确定
        // TODO add your code here
        //获取username 文本框的值
        String userNameStr = userName.getText();
        //用户名不少于6位且不能含有空格
        String str = "^[^ ]+\\w{5,}$";
        //判断符不符合类型
        boolean b1 = Pattern.matches(str, userNameStr);
        //如果不符合
        if (!b1) {
            JOptionPane.showMessageDialog(null, "用户名不少于6位且不能含有空格");
            userName.setText("");
        }
        //如果文本框没输入
        if (StringUtils.isEmpty(userNameStr)) {
            JOptionPane.showMessageDialog(null, "用户名不能为空");
            return;
        } else {
            StaffService staffService = new StaffServiceImpl();
            Staff staff = new Staff();
            staff.setUserName(userNameStr);//创建一个staff 对象传入username
            List<Staff> list = staffService.queryStaffbystaff(staff);//传入queryStaffbystaff
            //去查询，如果size>1 ，则存在
            if (list.size() > 1) {
                JOptionPane.showMessageDialog(null, "用户名已经存在");
                return;
            }
        }
        //获取phone 文本框的信息
        String phoneStr = phone.getText();
        //如果文本框没输入
        if (StringUtils.isEmpty(phoneStr)) {
            JOptionPane.showMessageDialog(null, "手机不能为空");
            return;
        }
//获取idCardNumber 文本框的信息
        String idCardNumberStr = idCardNumber.getText();
        //判断身份证的正则
        String s1 = "^\\d{18}$";
        //判断文本框的内容符不符合正则
        boolean b3 = Pattern.matches(s1, idCardNumberStr);
        if (!b3) {
            JOptionPane.showMessageDialog(null, "身份证格式不对");
            //清空文本框
            idCardNumber.setText("");
            return;
        }
        //如果文本框没输入
        if (StringUtils.isEmpty(idCardNumberStr)) {
            JOptionPane.showMessageDialog(null, "身份证不能为空");
            return;
        }
        //获取email文本框的内容
        String emailStr = eMail.getText();
        //如果文本框没输入
        if (StringUtils.isEmpty(emailStr)) {
            JOptionPane.showMessageDialog(null, "邮箱不能为空");
            return;
        }
        /**
         * 真实姓名不超过10位
         */
        String realNameStr = realName.getText();
        //真实姓名的正则
        String s = "^\\S{0,10}$";
        //判断真实姓名的格式符不符合正则
        boolean b2 = Pattern.matches(s, realNameStr);
        if (!b2) {
            JOptionPane.showMessageDialog(null, "真实姓名格式不对");
            //清空文本框
            realName.setText("");
            return;
        }
        //如果文本框没输入
        if (StringUtils.isEmpty(realNameStr)) {
            JOptionPane.showMessageDialog(null, "真实姓名不能为空");
            return;
        }
        //设置//不少于6位且不能含有空格
        String str3 = "^[^ ]+\\w{5,}$";
        String passwordStr = passWord.getText();//或者password 文本框内容
        boolean b6 = Pattern.matches(str3, passwordStr);//判断符不符合正则表达式
        if (!b6) {
            JOptionPane.showMessageDialog(null, "密码不少于6位且不能含有空格");
            return;
        }
        //如果文本框没输入
        if (StringUtils.isEmpty(passwordStr)) {
            JOptionPane.showMessageDialog(null, "密码不能为空");
            return;
        }
        //获取confirmPassWord文本框的内容
        String confirmPassWordStr = confirmPassWord.getText();
        //如果文本框没输入
        if (StringUtils.isEmpty(confirmPassWordStr)) {
            JOptionPane.showMessageDialog(null, "再次输入密码不能为空");
            return;
        }
        //获取userno 文本框的内容
        String userNoStr = userNo.getText();
        //如果文本框没输入
        if (StringUtils.isEmpty(userNoStr)) {
            JOptionPane.showMessageDialog(null, "用户编号不能为空");
            return;
        } else {
            //设置正则表达式 e开头则后面接5个数字
            String s3 = "^e\\d{5}";
//判断userno符不符合正则表达式
            boolean b = Pattern.matches(s3, userNoStr);
            if (!b) {
                JOptionPane.showMessageDialog(null, "用户编号格式不对");
                //清空文本框内容
                userNo.setText("");
                return;
            }

        }
        //获取borndate 文本框的内容
        String bornDateStr = bornDate.getText();
        //设置出生日期格式 yyyy-dd-mm;
        String a = "\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}";
        //判断borndate 符不符合正则表达式
        boolean b9 = Pattern.matches(a, bornDateStr);
        if (!b9) {
            JOptionPane.showMessageDialog(null, "出生日期格式不对");
            return;
        }
        //截取年份
        Integer ss = Integer.valueOf(bornDateStr.substring(0, 4));
        //判断时间格式且年满18周岁小于60周岁
        if (ss > 2002 || ss < 1960) {
            JOptionPane.showMessageDialog(null, "要求年满18周岁小于60周岁");
            return;
        }
        //如果文本框没输入
        if (StringUtils.isEmpty(bornDateStr)) {
            JOptionPane.showMessageDialog(null, "出生日期不能为空");
            return;
        }
        //获取sex下拉框的呢绒
        String gengerstr = sex.getSelectedItem().toString();
        //如果文本框没输入
        if (StringUtils.isEmpty(gengerstr)) {
            JOptionPane.showMessageDialog(null, "性别不能为空");
            return;
        }
        // 获取用户类型下拉框
        String userTypeStr = userType.getSelectedItem().toString();
        //如果文本框没输入
        if (StringUtils.isEmpty(userTypeStr)) {
            JOptionPane.showMessageDialog(null, "用户类别不能为空");
            return;
        }
        //如果两个密码不一致
        if (!passwordStr.equals(confirmPassWordStr)) {
            JOptionPane.showMessageDialog(null, "两次输入的密码不一致！！");
            return;
        }
        //创建一个staff 对象，
        Staff staff = new Staff();
        staff.setUserName(userNameStr);
        staff.setRealName(realNameStr);
        staff.setSex(gengerstr);
        staff.setPassWord(passwordStr);
        staff.setTelephone(phoneStr);
        staff.setEmail(emailStr);
        staff.setIdCardNumber(idCardNumberStr);
        staff.setBornDate(bornDateStr);
        staff.setUserNo(userNoStr);
        staff.setUserType(userTypeStr);
        //Login.staffLogin.getId() ，登录时保存
        staff.setCreatUserId(Login.staffLogin.getId());
        StaffService staffService = new StaffServiceImpl();
        boolean b = staffService.insert(staff);//判断是不是插入成功
        JOptionPane.showConfirmDialog(null, "确定吗?", "添加员工", JOptionPane.YES_NO_OPTION);
        if (b) {
            JOptionPane.showMessageDialog(null, "用户添加成功");
            this.setVisible(false);
            return;
        } else {
            JOptionPane.showMessageDialog(null, "用户添加失败");
            return;
        }
    }

    private void button2ActionPerformed(ActionEvent e) {//取消 按钮
        // TODO add your code here
        this.setVisible(false);
    }

    private void userNoActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void genderActionPerformed(ActionEvent e) {
        // TODO add your code here

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        userName = new JTextField();
        label2 = new JLabel();
        passWord = new JTextField();
        label3 = new JLabel();
        realName = new JTextField();
        label4 = new JLabel();
        sex = new JComboBox<>();
        label5 = new JLabel();
        confirmPassWord = new JTextField();
        label6 = new JLabel();
        eMail = new JTextField();
        label7 = new JLabel();
        phone = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        idCardNumber = new JTextField();
        label10 = new JLabel();
        label8 = new JLabel();
        userNo = new JTextField();
        label9 = new JLabel();
        bornDate = new JTextField();
        label11 = new JLabel();
        userType = new JComboBox<>();
        label12 = new JLabel();

        //======== this ========
        setTitle("\u6dfb\u52a0\u5458\u5de5");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("\u7528\u6237\u540d");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 1f));
            panel1.add(label1);
            label1.setBounds(new Rectangle(new Point(40, 25), label1.getPreferredSize()));
            panel1.add(userName);
            userName.setBounds(110, 25, 165, userName.getPreferredSize().height);

            //---- label2 ----
            label2.setText("\u5bc6\u7801");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 1f));
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(40, 80), label2.getPreferredSize()));
            panel1.add(passWord);
            passWord.setBounds(110, 75, 165, 25);

            //---- label3 ----
            label3.setText("\u59d3\u540d");
            label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 2f));
            panel1.add(label3);
            label3.setBounds(new Rectangle(new Point(310, 25), label3.getPreferredSize()));
            panel1.add(realName);
            realName.setBounds(380, 25, 165, 25);

            //---- label4 ----
            label4.setText("\u6027\u522b");
            label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 2f));
            panel1.add(label4);
            label4.setBounds(new Rectangle(new Point(310, 80), label4.getPreferredSize()));

            //---- sex ----
            sex.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u7537",
                "\u5973"
            }));
            sex.addActionListener(e -> genderActionPerformed(e));
            panel1.add(sex);
            sex.setBounds(380, 75, 75, sex.getPreferredSize().height);

            //---- label5 ----
            label5.setText("\u5bc6\u7801\u786e\u8ba4");
            label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 1f));
            panel1.add(label5);
            label5.setBounds(new Rectangle(new Point(40, 130), label5.getPreferredSize()));
            panel1.add(confirmPassWord);
            confirmPassWord.setBounds(110, 125, 165, 25);

            //---- label6 ----
            label6.setText("\u90ae\u7bb1\u5730\u5740");
            label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 1f));
            panel1.add(label6);
            label6.setBounds(new Rectangle(new Point(310, 180), label6.getPreferredSize()));
            panel1.add(eMail);
            eMail.setBounds(380, 180, 165, 25);

            //---- label7 ----
            label7.setText("\u7535\u8bdd\u53f7\u7801");
            label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 1f));
            panel1.add(label7);
            label7.setBounds(new Rectangle(new Point(40, 185), label7.getPreferredSize()));
            panel1.add(phone);
            phone.setBounds(110, 180, 165, 25);

            //---- button1 ----
            button1.setText("\u786e\u5b9a");
            button1.addActionListener(e -> button1ActionPerformed(e));
            panel1.add(button1);
            button1.setBounds(145, 415, 75, 25);

            //---- button2 ----
            button2.setText("\u53d6\u6d88");
            button2.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button2);
            button2.setBounds(380, 415, 75, 25);
            panel1.add(idCardNumber);
            idCardNumber.setBounds(380, 125, 165, 25);

            //---- label10 ----
            label10.setText("\u8eab\u4efd\u8bc1");
            label10.setFont(label10.getFont().deriveFont(label10.getFont().getSize() + 1f));
            panel1.add(label10);
            label10.setBounds(new Rectangle(new Point(310, 130), label10.getPreferredSize()));

            //---- label8 ----
            label8.setText("\u7528\u6237\u7f16\u53f7");
            label8.setFont(label8.getFont().deriveFont(label8.getFont().getSize() + 1f));
            panel1.add(label8);
            label8.setBounds(new Rectangle(new Point(40, 245), label8.getPreferredSize()));

            //---- userNo ----
            userNo.addActionListener(e -> userNoActionPerformed(e));
            panel1.add(userNo);
            userNo.setBounds(110, 240, 165, 25);

            //---- label9 ----
            label9.setText("\u751f\u65e5");
            label9.setFont(label9.getFont().deriveFont(label9.getFont().getSize() + 1f));
            panel1.add(label9);
            label9.setBounds(new Rectangle(new Point(310, 240), label9.getPreferredSize()));
            panel1.add(bornDate);
            bornDate.setBounds(380, 240, 165, 25);

            //---- label11 ----
            label11.setText("\u7528\u6237\u7c7b\u578b");
            label11.setFont(label11.getFont().deriveFont(label11.getFont().getSize() + 1f));
            panel1.add(label11);
            label11.setBounds(new Rectangle(new Point(40, 310), label11.getPreferredSize()));

            //---- userType ----
            userType.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u7528\u6237",
                "\u7ba1\u7406\u5458"
            }));
            userType.setFont(userType.getFont().deriveFont(userType.getFont().getSize() + 1f));
            panel1.add(userType);
            userType.setBounds(new Rectangle(new Point(110, 305), userType.getPreferredSize()));

            //---- label12 ----
            label12.setText("\u751f\u65e5\u65f6\u95f4\u683c\u5f0fyyyy-mm-dd");
            label12.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 12));
            panel1.add(label12);
            label12.setBounds(310, 275, label12.getPreferredSize().width, 20);

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
        panel1.setBounds(0, 0, 615, 485);

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
    private JLabel label1;
    private JTextField userName;
    private JLabel label2;
    private JTextField passWord;
    private JLabel label3;
    private JTextField realName;
    private JLabel label4;
    private JComboBox<String> sex;
    private JLabel label5;
    private JTextField confirmPassWord;
    private JLabel label6;
    private JTextField eMail;
    private JLabel label7;
    private JTextField phone;
    private JButton button1;
    private JButton button2;
    private JTextField idCardNumber;
    private JLabel label10;
    private JLabel label8;
    private JTextField userNo;
    private JLabel label9;
    private JTextField bornDate;
    private JLabel label11;
    private JComboBox<String> userType;
    private JLabel label12;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
