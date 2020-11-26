/*
 * Created by JFormDesigner on Fri Nov 06 16:42:32 GMT+08:00 2020
 */

package com.cbf.view;

import com.aliyuncs.utils.StringUtils;
import com.cbf.entity.Salary;
import com.cbf.entity.Staff;
import com.cbf.service.SalaryService;
import com.cbf.service.impl.SalaryServiceImpl;
import sun.rmi.runtime.Log;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

/**修改工资
 * @author Brainrain
 */
public class UpdateSalary extends JFrame {
    SalaryService salaryService = new SalaryServiceImpl();
    private Integer id;//全局一个id，另一边传过来的

    public UpdateSalary(Integer id) {
        initComponents();
        this.id = id;
        init();
    }

    public void init() {
        this.setVisible(true);//可显

        //设置月份下拉框的值
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
        month.addItem("");
        //传入id 进行查询
        List<Salary> list = salaryService.querysalarybyid(id);
        if (list != null) {
            Salary salary = list.get(0);//提取第一行
            //设置每个原来的值 到窗口里
            staffId.setText(salary.getStaffId());
            basicSalary.setText(salary.getBasicSalary().toString());
            postSalary.setText(salary.getPostSalary().toString());
            senioritySalary.setText(salary.getSenioritySalary().toString());
            communication.setText(salary.getCommunication().toString());
            realSalary.setText(salary.getRealSalary().toString());
            transportation.setText(salary.getTransportation().toString());
            individualTaxPayment.setText(salary.getIndividualTaxPayment().toString());
            socialSecurityPayment.setText(salary.getSocialSecurityPayment().toString());
            year.setText(salary.getYear());
            totalSalary.setText(salary.getTotalSalary().toString());
            month.setSelectedItem(salary.getMonth());
            housingProvidentFund.setText(salary.getHousingProvidentFund().toString());
        }

    }
    private void button1ActionPerformed(ActionEvent e) {//确定
        // TODO add your code here
        //获取 staffId 文本框的内容
        String staffIdStr = staffId.getText();
        //如果不为空
        if (StringUtils.isEmpty(staffIdStr)) {
            JOptionPane.showMessageDialog(null, "员工编号不能为空");
            return;
        }
        //获取 basicSalary 文本框的内容
        String basicSalaryStr = basicSalary.getText();
        //如果不为空
        if (StringUtils.isEmpty(basicSalaryStr)) {
            JOptionPane.showMessageDialog(null, "基本工资不能为空");
            return;
        }
        //获取 postSalary 文本框的内容
        String postSalaryStr = postSalary.getText();
        //如果不为空
        if (StringUtils.isEmpty(postSalaryStr)) {
            JOptionPane.showMessageDialog(null, "岗位工资不能为空");
            return;
        }
        //获取 senioritySalary 文本框的内容
        String senioritySalaryStr = senioritySalary.getText();
        //如果不为空
        if (StringUtils.isEmpty(senioritySalaryStr)) {
            JOptionPane.showMessageDialog(null, "工龄工资不能为空");
            return;
        }
        //获取 communication 文本框的内容
        String communicationText = communication.getText();
        //如果不为空
        if (StringUtils.isEmpty(communicationText)) {
            JOptionPane.showMessageDialog(null, "通讯补助不能为空");
            return;
        }
        //获取 realSalary 文本框的内容
        String realSalaryText = realSalary.getText();
        //如果不为空
        if (StringUtils.isEmpty(realSalaryText)) {
            JOptionPane.showMessageDialog(null, "实发工资不能为空");
            return;
        }
        //获取 transportation 文本框的内容
        String transportationText = transportation.getText();
        //如果不为空
        if (StringUtils.isEmpty(transportationText)) {
            JOptionPane.showMessageDialog(null, "交通补助不能为空");
            return;
        }
        //获取 individualTaxPayment 文本框的内容
        String individualTaxPaymentText = individualTaxPayment.getText();
        //如果不为空
        if (StringUtils.isEmpty(individualTaxPaymentText)) {
            JOptionPane.showMessageDialog(null, "个税代缴不能为空");
            return;
        }
        //获取 socialSecurityPayment 文本框的内容
        String socialSecurityPaymentText = socialSecurityPayment.getText();
        //如果不为空
        if (StringUtils.isEmpty(socialSecurityPaymentText)) {
            JOptionPane.showMessageDialog(null, "社保代缴不能为空");
            return;
        }
        //获取 housingProvidentFund 文本框的内容
        String housingProvidentFundText = housingProvidentFund.getText();
        //如果不为空
        if (StringUtils.isEmpty(housingProvidentFundText)) {
            JOptionPane.showMessageDialog(null, "住房公积金不能为空");
            return;
        }

        //获取 year 文本框的内容
        String yearText = year.getText();
        //如果不为空
        if (StringUtils.isEmpty(yearText)) {
            JOptionPane.showMessageDialog(null, "年龄不能为空");
            return;
        }
        //获取 month 下拉框的内容
        String monthStr = month.getSelectedItem().toString();
        //如果不为空
        if (StringUtils.isEmpty(monthStr)) {
            JOptionPane.showMessageDialog(null, "月份不能为空");
            return;
        }
        //获取 totalSalary 文本框的内容
        String totalSalarystr = totalSalary.getText();
        //如果不为空
        if (StringUtils.isEmpty(totalSalarystr)) {
            JOptionPane.showMessageDialog(null, "总工资不能为空");
            return;
        }
        Salary salary = new Salary();//创建一个salary对象
        //传值进入salary
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
        //设置更改id
        salary.setUpdateUserId(Login.staffLogin.getId());
//        salary.setCreatUserId(Login.staffLogin.getId());
        //根据id 去查
        salary.setId(this.id);

        //判断是否修改成功
        boolean b = salaryService.updateSalary(salary);
        if (b) {
            JOptionPane.showMessageDialog(null, "修改工资信息成功");
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "修改工资信息失败");
        }
    }

    private void button2ActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void userNoActionPerformed(ActionEvent e) {
        // TODO add your code here
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

        //======== this ========
        setTitle("\u4fee\u6539\u5de5\u8d44");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("\u5458\u5de5\u7f16\u53f7");
            panel1.add(label1);
            label1.setBounds(new Rectangle(new Point(155, 25), label1.getPreferredSize()));
            panel1.add(staffId);
            staffId.setBounds(230, 20, 165, staffId.getPreferredSize().height);

            //---- label2 ----
            label2.setText("\u57fa\u672c\u5de5\u8d44");
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(40, 65), label2.getPreferredSize()));
            panel1.add(basicSalary);
            basicSalary.setBounds(110, 65, 165, 25);

            //---- label3 ----
            label3.setText("\u4ea4\u901a\u8865\u52a9");
            panel1.add(label3);
            label3.setBounds(new Rectangle(new Point(310, 70), label3.getPreferredSize()));
            panel1.add(transportation);
            transportation.setBounds(380, 65, 165, 25);

            //---- label5 ----
            label5.setText("\u5c97\u4f4d\u5de5\u8d44");
            panel1.add(label5);
            label5.setBounds(new Rectangle(new Point(40, 110), label5.getPreferredSize()));
            panel1.add(postSalary);
            postSalary.setBounds(110, 110, 165, 25);

            //---- label6 ----
            label6.setText("\u793e\u4fdd\u4ee3\u7f34");
            panel1.add(label6);
            label6.setBounds(new Rectangle(new Point(310, 155), label6.getPreferredSize()));
            panel1.add(socialSecurityPayment);
            socialSecurityPayment.setBounds(380, 155, 165, 25);

            //---- label7 ----
            label7.setText("\u5de5\u9f84\u5de5\u8d44");
            panel1.add(label7);
            label7.setBounds(new Rectangle(new Point(40, 155), label7.getPreferredSize()));
            panel1.add(senioritySalary);
            senioritySalary.setBounds(110, 155, 165, 25);

            //---- button1 ----
            button1.setText("\u786e\u5b9a");
            button1.addActionListener(e -> button1ActionPerformed(e));
            panel1.add(button1);
            button1.setBounds(135, 410, 65, 30);

            //---- button2 ----
            button2.setText("\u53d6\u6d88");
            button2.addActionListener(e -> button2ActionPerformed(e));
            panel1.add(button2);
            button2.setBounds(385, 410, 65, 30);
            panel1.add(individualTaxPayment);
            individualTaxPayment.setBounds(380, 110, 165, 25);

            //---- label10 ----
            label10.setText("\u4e2a\u7a0e\u4ee3\u7f34");
            panel1.add(label10);
            label10.setBounds(new Rectangle(new Point(310, 110), label10.getPreferredSize()));

            //---- label8 ----
            label8.setText("\u901a\u8baf\u8865\u52a9");
            panel1.add(label8);
            label8.setBounds(new Rectangle(new Point(40, 220), label8.getPreferredSize()));

            //---- communication ----
            communication.addActionListener(e -> userNoActionPerformed(e));
            panel1.add(communication);
            communication.setBounds(110, 215, 165, 25);

            //---- label9 ----
            label9.setText("\u4f4f\u623f\u516c\u79ef\u91d1");
            panel1.add(label9);
            label9.setBounds(new Rectangle(new Point(310, 220), label9.getPreferredSize()));
            panel1.add(housingProvidentFund);
            housingProvidentFund.setBounds(380, 220, 165, 25);

            //---- label11 ----
            label11.setText("\u5b9e\u53d1\u5de5\u8d44");
            panel1.add(label11);
            label11.setBounds(new Rectangle(new Point(40, 275), label11.getPreferredSize()));

            //---- realSalary ----
            realSalary.addActionListener(e -> userNoActionPerformed(e));
            panel1.add(realSalary);
            realSalary.setBounds(110, 275, 165, 25);

            //---- label12 ----
            label12.setText("\u603b\u5de5\u8d44");
            panel1.add(label12);
            label12.setBounds(new Rectangle(new Point(130, 345), label12.getPreferredSize()));

            //---- totalSalary ----
            totalSalary.addActionListener(e -> userNoActionPerformed(e));
            panel1.add(totalSalary);
            totalSalary.setBounds(210, 340, 165, 25);

            //---- label4 ----
            label4.setText("\u5e74\u4efd");
            panel1.add(label4);
            label4.setBounds(new Rectangle(new Point(330, 280), label4.getPreferredSize()));
            panel1.add(year);
            year.setBounds(380, 275, 75, year.getPreferredSize().height);

            //---- label13 ----
            label13.setText("\u6708\u4efd");
            panel1.add(label13);
            label13.setBounds(new Rectangle(new Point(465, 280), label13.getPreferredSize()));
            panel1.add(month);
            month.setBounds(505, 275, 87, month.getPreferredSize().height);

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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
