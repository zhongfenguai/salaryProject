/*
 * Created by JFormDesigner on Fri Nov 06 09:35:37 GMT+08:00 2020
 */

package com.cbf.view;

import com.cbf.entity.SalaryWithUser;
import com.cbf.service.SalaryService;
import com.cbf.service.impl.SalaryServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** 员工查询 工资
 * @author Brainrain
 */
public class StaffUserList extends JFrame {
    SalaryService salaryService = new SalaryServiceImpl();
    List<SalaryWithUser> list = null;//设置默认值
    //    SalaryWithUser salaryWithUser=new SalaryWithUser();
    Object[][] values = null;

    public StaffUserList() {
        initComponents();
        init();//可显
        initTable(null, null, null);//初始化显示

    }

    /**
     * 进行分页， 且根据3个文本框的内容进行查询
     * @param str id 或者username
     * @param year
     * @param month
     */
    public void initTable(String str, String year, String month) {
        Object[] objects = new Object[]{"id", "姓名", "关联的员工编号", "创建人ID", "更新人ID", "年份", "月份", "基本工资", "岗位工资", "工龄工资", "通讯补助", "交通补助", "个税代缴", "社保代缴", "住房公积金", "实发工资", "总工资"};
        //获取三个文本框的内容
        str = username.getText();
        year = textField1.getText();
        month = comboBox1.getSelectedItem().toString();
        //插入查询
        list = salaryService.querySalary3(str, year, month);
//        list = salaryService.querySalarybystaffId(staffid);

        //如果list不为空
        if (list != null) {
            values = new Object[list.size()][20];
            for (int i = 0; i < list.size(); i++) {
                Object[] object = new Object[20];
                object[0] = i + 1;
                object[1] = list.get(i).getRealName();
//                object[0]=list.get(i).getId();
                object[2] = list.get(i).getStaffId();
                object[3] = list.get(i).getCreatUserId();
                object[4] = list.get(i).getUpdateUserId();
                object[5] = list.get(i).getYear();
                object[6] = list.get(i).getMonth();
                object[7] = list.get(i).getBasicSalary();
                object[8] = list.get(i).getPostSalary();
                object[9] = list.get(i).getSenioritySalary();
                object[10] = list.get(i).getCommunication();
                object[11] = list.get(i).getTransportation();
                object[12] = list.get(i).getIndividualTaxPayment();
                object[13] = list.get(i).getSocialSecurityPayment();
                object[14] = list.get(i).getHousingProvidentFund();
                object[15] = list.get(i).getRealSalary();
                object[16] = list.get(i).getTotalSalary();
                object[17] = list.get(i).getMonth();

                values[i] = object;
            }
        } else {
            values = new Object[0][20];
        }
        //把属性和对象都放到表中
        table1.setModel(new DefaultTableModel(values, objects));
    }

    public void init() {
        this.setVisible(true);//可显
        //设置月份下拉框的值
        comboBox1.addItem("");
        comboBox1.addItem(1);
        comboBox1.addItem(2);
        comboBox1.addItem(3);
        comboBox1.addItem(4);
        comboBox1.addItem(5);
        comboBox1.addItem(6);
        comboBox1.addItem(7);
        comboBox1.addItem(8);
        comboBox1.addItem(9);
        comboBox1.addItem(10);
        comboBox1.addItem(11);
        comboBox1.addItem(12);

    }

    private void button2ActionPerformed(ActionEvent e) {//查询
        // TODO add your code here
        //如果都不为空， 就条件查询
        if (StringUtils.isNotEmpty(username.getText()) && StringUtils.isNotEmpty(textField1.getText()) && StringUtils.isNotEmpty(comboBox1.getSelectedItem().toString())) {
            initTable(username.getText(), textField1.getText(), comboBox1.getSelectedItem().toString());
        } else {
            // 输出全部
            initTable(null, null, null);
        }

    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);
//        StaffUser staffUser = new StaffUser();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button2 = new JButton();
        label1 = new JLabel();
        username = new JTextField();
        comboBox1 = new JComboBox();
        textField1 = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setTitle("\u67e5\u770b\u5de5\u8d44");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 45, 985, 550);

        //---- button2 ----
        button2.setText("\u67e5\u8be2\u5de5\u8d44");
        button2.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(230, 5, 110, button2.getPreferredSize().height);

        //---- label1 ----
        label1.setText("\u5458\u5de5\u59d3\u540d");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 10), label1.getPreferredSize()));
        contentPane.add(username);
        username.setBounds(90, 5, 85, username.getPreferredSize().height);
        contentPane.add(comboBox1);
        comboBox1.setBounds(560, 10, 100, comboBox1.getPreferredSize().height);
        contentPane.add(textField1);
        textField1.setBounds(405, 10, 60, textField1.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u5e74\u4efd");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(360, 10), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u6708\u4efd");
        contentPane.add(label3);
        label3.setBounds(495, 15, 45, label3.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u8fd4\u56de\u4e0a\u4e00\u7ea7");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(695, 10), button1.getPreferredSize()));

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
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button2;
    private JLabel label1;
    private JTextField username;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JLabel label2;
    private JLabel label3;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
