/*
 * Created by JFormDesigner on Thu Nov 05 10:51:08 GMT+08:00 2020
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

/** 更改员工信息
 * @author Brainrain
 */
public class StaffUpdate extends JFrame {

    StaffService staffService = new StaffServiceImpl();

    private Integer id; //传入的id

    public StaffUpdate(Integer id) {
        initComponents();
        this.id = id;
        init();
    }

    public void init() {
        this.setVisible(true);//可显
        List<Staff> list = staffService.querystaffbyId(id);// 传入id 进行查询

        if (list != null) {

            // 设置所有文本框的初始内容
            Staff staff = list.get(0);// 获取第一行
            userName.setText(staff.getUserName());
            realName.setText(staff.getRealName());
            userNo.setText(staff.getUserNo());
            idCardNumber.setText(staff.getIdCardNumber());
            bornDate.setText(staff.getBornDate());
            phone.setText(staff.getTelephone());
            gender.setSelectedItem(staff.getSex());
            eMail.setText(staff.getEmail());
            passWord.setText(staff.getPassWord());

        }

    }

    private void button1ActionPerformed(ActionEvent e) {//确定
        // TODO add your code here

        String str3 = "^[^ ]+\\w{5,}$";//不少于6位且不能含有空格
//获取password 的内容
        String passwordStr = passWord.getText();
        //判断符不符合正则
        boolean b3 = Pattern.matches(str3, passwordStr);
        if (!b3) {
            JOptionPane.showMessageDialog(null, "密码不少于6位且不能含有空格");
            return;
        }
        //判断是否为空
        if (StringUtils.isEmpty(passwordStr)) {
            JOptionPane.showMessageDialog(null, "密码不能为空");
            return;
        }
        //获取username 的内容
        String userNameStr = userName.getText();
        //判断是否符合正则
        boolean b4 = Pattern.matches(str3, userNameStr);
        if (!b4) {
            JOptionPane.showMessageDialog(null, "用户名不少于6位且不能含有空格");
        }
        //不为空
        if (StringUtils.isEmpty(userNameStr)) {
            JOptionPane.showMessageDialog(null, "用户名不能为空");
            return;
        }
        //获取realname文本框的内容
        String realNameStr = realName.getText();
        if (StringUtils.isEmpty(realNameStr)) {
            JOptionPane.showMessageDialog(null, "真实姓名不能为空");
            return;
        }

        //获取userno文本框的内容
        String userNoStr = userNo.getText();
        //如果不为空
        if (StringUtils.isEmpty(userNoStr)) {
            JOptionPane.showMessageDialog(null, "用户编号不能为空");
            return;
        } else {
            //用户编号要求e打头，后面接5位数字
            String str = "^e\\d{5}$";
            //判断符不符合
            boolean b = Pattern.matches(str, userNoStr);
            if (!b) {
                JOptionPane.showMessageDialog(null, "用户编号要求e打头，后面接5位数字！");
                return;
            }
            Staff staff = new Staff();
            staff.setUserNo(userNoStr);//设置userno传入staff
            List<Staff> list = staffService.queryStaffbystaff(staff);//进行查询
            //如果存在
            if (list.size() > 0) {
                //遍历 查询
                for (Staff staff1 : list) {
                    //除了自己之外
                    if (staff1.getId() != id) {
                        JOptionPane.showMessageDialog(null, "用户编号已存在！");
                        return;
                    }
                }

            }
        }
        /**
         *邮箱
         */
        //获取email的文本框内容
        String emailStr = eMail.getText();
        //如果不为空
        if (StringUtils.isEmpty(emailStr)) {
            JOptionPane.showMessageDialog(null, "邮箱不能为空");
            return;
        }
        ////获取phone的文本框内容
        String phoneStr = phone.getText();
        //如果不为空
        if (StringUtils.isEmpty(phoneStr)) {
            JOptionPane.showMessageDialog(null, "电话不能为空");
            return;
        }
        //获取bornDate文本框内容
        String bornDateStr = bornDate.getText();
        //
        String a = "\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}";
        //正则判断
        boolean b8 = Pattern.matches(a, bornDateStr);
        if (!b8) {
            JOptionPane.showMessageDialog(null, "出生日期格式不对");
            return;
        }
        //如果不为空
        if (StringUtils.isEmpty(bornDateStr)) {
            JOptionPane.showMessageDialog(null, "出生日期不能为空");
            return;
        }
        //idCardNumber 文本框内容
        String idCardNumberStr = idCardNumber.getText();
        // 正则判断
        String s3 = "^\\d{18}$";
        boolean b7 = Pattern.matches(s3, idCardNumberStr);
        if (!b7) {
            JOptionPane.showMessageDialog(null, "身份证格式不对");
            return;
        }
        //如果idCardNumber 不为空
        if (StringUtils.isEmpty(idCardNumberStr)) {
            JOptionPane.showMessageDialog(null, "身份证不能为空");
            return;
        }
        //创建一个staff 用来存储
        Staff staff = new Staff();
        staff.setPassWord(passwordStr);
        staff.setUserNo(userNoStr);
        staff.setBornDate(bornDateStr);
        staff.setEmail(emailStr);
        staff.setTelephone(phoneStr);
        staff.setRealName(realNameStr);
        staff.setUserName(userNameStr);
        staff.setIdCardNumber(idCardNumberStr);
        staff.setUpdateUserId(Login.staffLogin.getId());
        staff.setId(this.id);// 设置id
        //判断是否修改
        boolean b = staffService.updateStaff(staff);

        if (b) {
            JOptionPane.showMessageDialog(null, "修改成功");
            this.setVisible(false);//关闭当前窗口
            StaffList staffList = new StaffList();
            return;
        } else {
            JOptionPane.showMessageDialog(null, "修改失败");
        }
    }


    private void button2ActionPerformed(ActionEvent e) {//取消
        // TODO add your code here
        this.setVisible(false);
    }

    private void userNoActionPerformed(ActionEvent e) {
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
        gender = new JComboBox<>();
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

        //======== this ========
        setTitle("\u4fee\u6539\u5458\u5de5");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("\u7528\u6237\u540d");
            label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
            panel1.add(label1);
            label1.setBounds(40, 50, 50, 30);
            panel1.add(userName);
            userName.setBounds(110, 50, 165, userName.getPreferredSize().height);

            //---- label2 ----
            label2.setText("\u5bc6\u7801");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 2f));
            panel1.add(label2);
            label2.setBounds(40, 95, label2.getPreferredSize().width, 30);
            panel1.add(passWord);
            passWord.setBounds(110, 95, 165, 25);

            //---- label3 ----
            label3.setText("\u59d3\u540d");
            label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 1f));
            panel1.add(label3);
            label3.setBounds(310, 50, 55, 30);
            panel1.add(realName);
            realName.setBounds(380, 50, 165, 25);

            //---- label4 ----
            label4.setText("\u6027\u522b");
            label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 1f));
            panel1.add(label4);
            label4.setBounds(310, 95, 55, 30);

            //---- gender ----
            gender.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u7537",
                "\u5973"
            }));
            panel1.add(gender);
            gender.setBounds(380, 95, 75, gender.getPreferredSize().height);

            //---- label5 ----
            label5.setText("\u5bc6\u7801\u786e\u8ba4");
            label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 1f));
            panel1.add(label5);
            label5.setBounds(40, 160, 60, 17);
            panel1.add(confirmPassWord);
            confirmPassWord.setBounds(110, 155, 165, 25);

            //---- label6 ----
            label6.setText("\u90ae\u7bb1\u5730\u5740");
            label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 1f));
            panel1.add(label6);
            label6.setBounds(310, 210, 60, 30);
            panel1.add(eMail);
            eMail.setBounds(380, 215, 165, 25);

            //---- label7 ----
            label7.setText("\u7535\u8bdd\u53f7\u7801");
            label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 1f));
            panel1.add(label7);
            label7.setBounds(40, 210, 55, 30);
            panel1.add(phone);
            phone.setBounds(110, 215, 165, 25);

            //---- button1 ----
            button1.setText("\u786e\u5b9a");
            button1.addActionListener(e -> button1ActionPerformed(e));
            panel1.add(button1);
            button1.setBounds(140, 385, 75, button1.getPreferredSize().height);

            //---- button2 ----
            button2.setText("\u53d6\u6d88");
            button2.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button2);
            button2.setBounds(380, 385, 75, button2.getPreferredSize().height);
            panel1.add(idCardNumber);
            idCardNumber.setBounds(380, 155, 165, 25);

            //---- label10 ----
            label10.setText("\u8eab\u4efd\u8bc1");
            label10.setFont(label10.getFont().deriveFont(label10.getFont().getSize() + 1f));
            panel1.add(label10);
            label10.setBounds(310, 160, 60, 17);

            //---- label8 ----
            label8.setText("\u7528\u6237\u7f16\u53f7");
            label8.setFont(label8.getFont().deriveFont(label8.getFont().getSize() + 1f));
            panel1.add(label8);
            label8.setBounds(40, 280, 60, 30);

            //---- userNo ----
            userNo.addActionListener(e -> userNoActionPerformed(e));
            panel1.add(userNo);
            userNo.setBounds(110, 280, 165, 25);

            //---- label9 ----
            label9.setText("\u751f\u65e5");
            label9.setFont(label9.getFont().deriveFont(label9.getFont().getSize() + 1f));
            panel1.add(label9);
            label9.setBounds(310, 280, label9.getPreferredSize().width, 30);
            panel1.add(bornDate);
            bornDate.setBounds(380, 280, 165, 25);

            //---- label11 ----
            label11.setText("\u65f6\u95f4\u683c\u5f0fyyyy-mm-dd");
            panel1.add(label11);
            label11.setBounds(new Rectangle(new Point(310, 325), label11.getPreferredSize()));

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
        panel1.setBounds(0, 0, 615, 495);

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
    private JComboBox<String> gender;
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
