/*
 * Created by JFormDesigner on Thu Nov 05 20:20:40 GMT+08:00 2020
 */

package com.cbf.view;

import com.aliyuncs.utils.StringUtils;
import com.cbf.entity.Salary;
import com.cbf.entity.SalaryWithUser;
import com.cbf.entity.Staff;
import com.cbf.service.SalaryService;
import com.cbf.service.impl.SalaryServiceImpl;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/**加工资
 * @author Brainrain
 */
public class AddSalary extends JFrame {

    public AddSalary() {
        initComponents();
        init();//可显
    }

    /**
     * totalSalary 失去焦点后自动计算
     *
     * @return totalSalary总工资
     */
    public float aFloat() {
        float totalSalary = Float.parseFloat(senioritySalary.getText()) + Float.parseFloat(basicSalary.getText()) + Float.parseFloat(postSalary.getText()) + Float.parseFloat(communication.getText()) + Float.parseFloat(transportation.getText()) + Float.parseFloat(individualTaxPayment.getText()) + Float.parseFloat(socialSecurityPayment.getText()) + Float.parseFloat(housingProvidentFund.getText());
        return totalSalary;
    }

    public void init() {//设置初始化界面
        this.setVisible(true);//可显
        month.addItem("");//默认为空
        //设置下拉框月份
        month.addItem(1);
        month.addItem(2);
        month.addItem(3);
        month.addItem(4);
        month.addItem(5);
        month.addItem(6);
        month.addItem(7);
        month.addItem(8);
        month.addItem(9);
        month.addItem(10);
        month.addItem(11);
        month.addItem(12);
        //设置各项初始值为0
        totalSalary.setText("0");
        basicSalary.setText("0");
        postSalary.setText("0");
        senioritySalary.setText("0");
        communication.setText("0");
        realSalary.setText("0");
        totalSalary.setText("0");
        individualTaxPayment.setText("0");
        socialSecurityPayment.setText("0");
        housingProvidentFund.setText("0");
    }

    private void genderActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void button1ActionPerformed(ActionEvent e) {//点击确认后
        // TODO add your code here

        //获取staffid文本框的值
        String staffIdStr = staffId.getText();
        //如果为空则
        if (StringUtils.isEmpty(staffIdStr)) {
            JOptionPane.showMessageDialog(null, "员工编号不能为空");
            return;
        }
        //获取basicSalary文本框的值
        String basicSalaryStr = basicSalary.getText();
        //如果为空则
        if (StringUtils.isEmpty(basicSalaryStr)) {
            JOptionPane.showMessageDialog(null, "基本工资不能为空");
            return;
        }
        //获取postSalary文本框的值
        String postSalaryStr = postSalary.getText();
        //如果为空则
        if (StringUtils.isEmpty(postSalaryStr)) {
            JOptionPane.showMessageDialog(null, "岗位工资不能为空");
            return;
        }
        //获取senioritySalary文本框的值
        String senioritySalaryStr = senioritySalary.getText();
        //如果为空则
        if (StringUtils.isEmpty(senioritySalaryStr)) {
            JOptionPane.showMessageDialog(null, "工龄工资不能为空");
            return;
        }
        //获取communication文本框的值
        String communicationText = communication.getText();
        //如果为空则
        if (StringUtils.isEmpty(communicationText)) {
            JOptionPane.showMessageDialog(null, "通讯补助不能为空");
            return;
        }
        //获取realSalary文本框的值
        String realSalaryText = realSalary.getText();
        //如果为空则
        if (StringUtils.isEmpty(realSalaryText)) {
            JOptionPane.showMessageDialog(null, "实发工资不能为空");
            return;
        }
        //获取transportation文本框的值
        String transportationText = transportation.getText();
        //如果为空则
        if (StringUtils.isEmpty(transportationText)) {
            JOptionPane.showMessageDialog(null, "交通补助不能为空");
            return;
        }
        //获取individualTaxPayment文本框的值
        String individualTaxPaymentText = individualTaxPayment.getText();
        //如果为空则
        if (StringUtils.isEmpty(individualTaxPaymentText)) {
            JOptionPane.showMessageDialog(null, "个税代缴不能为空");
            return;
        }
        //获取socialSecurityPayment文本框的值
        String socialSecurityPaymentText = socialSecurityPayment.getText();
        //如果为空则
        if (StringUtils.isEmpty(socialSecurityPaymentText)) {
            JOptionPane.showMessageDialog(null, "社保代缴不能为空");
            return;
        }
        //获取housingProvidentFund文本框的值
        String housingProvidentFundText = housingProvidentFund.getText();
        //如果为空则
        if (StringUtils.isEmpty(housingProvidentFundText)) {
            JOptionPane.showMessageDialog(null, "住房公积金不能为空");
            return;
        }
        //获取housingProvidentFund文本框的值文本框的值
        String yearText = year.getText();
        //如果为空则
        if (StringUtils.isEmpty(yearText)) {
            JOptionPane.showMessageDialog(null, "年龄不能为空");
            return;
        }
        //获取下拉框的值
        String monthStr = month.getSelectedItem().toString();
        //如果为空则
        if (StringUtils.isEmpty(monthStr)) {
            JOptionPane.showMessageDialog(null, "月份不能为空");
            return;
        }
        //获取housingProvidentFund文本框的值文本框的值
        String totalSalarystr = totalSalary.getText();
        //如果为空则
        if (StringUtils.isEmpty(totalSalarystr)) {
            JOptionPane.showMessageDialog(null, "总工资不能为空");
            return;
        }
        //创建一个salary对象，讲文本框的值存到salary里
        Salary salary = new Salary();
        salary.setBasicSalary(Float.valueOf(basicSalaryStr));
        salary.setTotalSalary(Float.valueOf(totalSalarystr));
        salary.setHousingProvidentFund(Float.valueOf(housingProvidentFundText));
        salary.setSocialSecurityPayment(Float.valueOf(socialSecurityPaymentText));
        salary.setTransportation(Float.valueOf(transportationText));
        salary.setSenioritySalary(Float.valueOf(senioritySalaryStr));
        salary.setYear(yearText);
        salary.setMonth(monthStr);
        salary.setIndividualTaxPayment(Float.valueOf(individualTaxPaymentText));
        salary.setPostSalary(Float.valueOf(postSalaryStr));
        salary.setStaffId(staffIdStr);
        salary.setCommunication(Float.valueOf(communicationText));
        salary.setRealSalary(Float.valueOf(realSalaryText));
        //讲登录时存储的id拿来使用
        salary.setCreatUserId(Login.staffLogin.getId());
//        salary.setUpdateUserId(Login.staffLogin.getId());
        //调用SalaryService服务，实例化一个对象用来调用方法
        SalaryService salaryService = new SalaryServiceImpl();
        //调用插入方法插入
        salaryService.insert(salary);
        //如果员工编号不为空，
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(staffIdStr)) {
            //SalaryWithUser实例化对象
            SalaryWithUser salaryWithUser = new SalaryWithUser();
            //调用方法
            SalaryService salaryService1 = new SalaryServiceImpl();
            //设置salaryWithUser的staffid 为 staffIdStr
            salaryWithUser.setStaffId(Integer.valueOf(staffIdStr));
            //一个salarywithuser泛型的list ，传入员工编号，月份，年份，判断表中是不是已经存在
            List<SalaryWithUser> list = salaryService1.querySalary9(salaryWithUser.getStaffId(), yearText, monthStr);
            //如果存在，返回
            if (list.size() > 0) {
                JOptionPane.showMessageDialog(null, "该月份已经存在");
                return;
            }
        }

        // 插入salary 返回布尔值
        boolean b = salaryService.insert2(salary);
        if (b) {
            JOptionPane.showMessageDialog(null, "添加工资信息成功");
            this.setVisible(false);
            return;

        } else {
            JOptionPane.showMessageDialog(null, "添加工资信息失败");
            return;
        }
    }

    private void button2ActionPerformed(ActionEvent e) {//取消按钮
        // TODO add your code here
        this.setVisible(false);
    }

    private void userNoActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void totalSalaryFocusLost(FocusEvent e) {
        // TODO add your code here

        totalSalary.setText(String.valueOf(aFloat()));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        staffId = new JTextField();
        label2 = new JLabel();
        basicSalary = new JTextField();
        label3 = new JLabel();
        transportation = new JTextField();
        label5 = new JLabel();
        postSalary = new JTextField();
        label6 = new JLabel();
        socialSecurityPayment = new JTextField();
        label7 = new JLabel();
        senioritySalary = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        individualTaxPayment = new JTextField();
        label10 = new JLabel();
        label8 = new JLabel();
        communication = new JTextField();
        label9 = new JLabel();
        housingProvidentFund = new JTextField();
        label11 = new JLabel();
        realSalary = new JTextField();
        label12 = new JLabel();
        totalSalary = new JTextField();
        label4 = new JLabel();
        year = new JTextField();
        label13 = new JLabel();
        month = new JComboBox();
        label14 = new JLabel();

        //======== this ========
        setTitle("\u6dfb\u52a0\u5de5\u8d44");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("\u5458\u5de5\u7f16\u53f7");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 1f));
            panel1.add(label1);
            label1.setBounds(new Rectangle(new Point(130, 25), label1.getPreferredSize()));

            //---- staffId ----
            staffId.setFont(staffId.getFont().deriveFont(staffId.getFont().getSize() + 1f));
            panel1.add(staffId);
            staffId.setBounds(210, 20, 165, staffId.getPreferredSize().height);

            //---- label2 ----
            label2.setText("\u57fa\u672c\u5de5\u8d44");
            label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 1f));
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(40, 65), label2.getPreferredSize()));
            panel1.add(basicSalary);
            basicSalary.setBounds(110, 65, 165, 25);

            //---- label3 ----
            label3.setText("\u4ea4\u901a\u8865\u52a9");
            label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 1f));
            panel1.add(label3);
            label3.setBounds(310, 70, 55, 17);
            panel1.add(transportation);
            transportation.setBounds(380, 65, 165, 25);

            //---- label5 ----
            label5.setText("\u5c97\u4f4d\u5de5\u8d44");
            label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 1f));
            panel1.add(label5);
            label5.setBounds(40, 110, 60, 17);
            panel1.add(postSalary);
            postSalary.setBounds(110, 110, 165, 25);

            //---- label6 ----
            label6.setText("\u793e\u4fdd\u4ee3\u7f34");
            label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 1f));
            panel1.add(label6);
            label6.setBounds(310, 155, 55, 17);
            panel1.add(socialSecurityPayment);
            socialSecurityPayment.setBounds(380, 155, 165, 25);

            //---- label7 ----
            label7.setText("\u5de5\u9f84\u5de5\u8d44");
            label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 1f));
            panel1.add(label7);
            label7.setBounds(40, 155, 55, 17);
            panel1.add(senioritySalary);
            senioritySalary.setBounds(110, 155, 165, 25);

            //---- button1 ----
            button1.setText("\u786e\u5b9a");
            button1.addActionListener(e -> button1ActionPerformed(e));
            panel1.add(button1);
            button1.setBounds(140, 410, 70, 30);

            //---- button2 ----
            button2.setText("\u53d6\u6d88");
            button2.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button2);
            button2.setBounds(385, 410, 70, 30);
            panel1.add(individualTaxPayment);
            individualTaxPayment.setBounds(380, 110, 165, 25);

            //---- label10 ----
            label10.setText("\u4e2a\u7a0e\u4ee3\u7f34");
            label10.setFont(label10.getFont().deriveFont(label10.getFont().getSize() + 1f));
            panel1.add(label10);
            label10.setBounds(310, 110, 60, 17);

            //---- label8 ----
            label8.setText("\u901a\u8baf\u8865\u52a9");
            label8.setFont(label8.getFont().deriveFont(label8.getFont().getSize() + 1f));
            panel1.add(label8);
            label8.setBounds(40, 220, 60, label8.getPreferredSize().height);

            //---- communication ----
            communication.addActionListener(e -> userNoActionPerformed(e));
            panel1.add(communication);
            communication.setBounds(110, 215, 165, 25);

            //---- label9 ----
            label9.setText("\u4f4f\u623f\u516c\u79ef\u91d1");
            label9.setFont(label9.getFont().deriveFont(label9.getFont().getSize() + 1f));
            panel1.add(label9);
            label9.setBounds(new Rectangle(new Point(310, 220), label9.getPreferredSize()));
            panel1.add(housingProvidentFund);
            housingProvidentFund.setBounds(380, 220, 165, 25);

            //---- label11 ----
            label11.setText("\u5b9e\u53d1\u5de5\u8d44");
            label11.setFont(label11.getFont().deriveFont(label11.getFont().getSize() + 1f));
            panel1.add(label11);
            label11.setBounds(new Rectangle(new Point(40, 275), label11.getPreferredSize()));

            //---- realSalary ----
            realSalary.addActionListener(e -> userNoActionPerformed(e));
            panel1.add(realSalary);
            realSalary.setBounds(110, 275, 165, 25);

            //---- label12 ----
            label12.setText("\u603b\u5de5\u8d44");
            label12.setFont(label12.getFont().deriveFont(label12.getFont().getSize() + 2f));
            panel1.add(label12);
            label12.setBounds(130, 345, 48, 17);

            //---- totalSalary ----
            totalSalary.addActionListener(e -> userNoActionPerformed(e));
            totalSalary.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    totalSalaryFocusLost(e);
                }
            });
            panel1.add(totalSalary);
            totalSalary.setBounds(210, 340, 165, totalSalary.getPreferredSize().height);

            //---- label4 ----
            label4.setText("\u5e74\u4efd");
            label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 1f));
            panel1.add(label4);
            label4.setBounds(new Rectangle(new Point(310, 280), label4.getPreferredSize()));
            panel1.add(year);
            year.setBounds(380, 275, 75, year.getPreferredSize().height);

            //---- label13 ----
            label13.setText("\u6708\u4efd");
            label13.setFont(label13.getFont().deriveFont(label13.getFont().getSize() + 1f));
            panel1.add(label13);
            label13.setBounds(new Rectangle(new Point(465, 280), label13.getPreferredSize()));

            //---- month ----
            month.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
            panel1.add(month);
            month.setBounds(505, 275, 87, month.getPreferredSize().height);
            panel1.add(label14);
            label14.setBounds(new Rectangle(new Point(310, 305), label14.getPreferredSize()));

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
    private JTextField staffId;
    private JLabel label2;
    private JTextField basicSalary;
    private JLabel label3;
    private JTextField transportation;
    private JLabel label5;
    private JTextField postSalary;
    private JLabel label6;
    private JTextField socialSecurityPayment;
    private JLabel label7;
    private JTextField senioritySalary;
    private JButton button1;
    private JButton button2;
    private JTextField individualTaxPayment;
    private JLabel label10;
    private JLabel label8;
    private JTextField communication;
    private JLabel label9;
    private JTextField housingProvidentFund;
    private JLabel label11;
    private JTextField realSalary;
    private JLabel label12;
    private JTextField totalSalary;
    private JLabel label4;
    private JTextField year;
    private JLabel label13;
    private JComboBox month;
    private JLabel label14;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
