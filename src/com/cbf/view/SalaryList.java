/*
 * Created by JFormDesigner on Thu Nov 05 17:48:17 GMT+08:00 2020
 */

package com.cbf.view;

import com.cbf.entity.Salary;
import com.cbf.entity.SalaryWithUser;
import com.cbf.entity.Staff;
import com.cbf.service.SalaryService;
import com.cbf.service.impl.SalaryServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.event.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** 管理员 查看工资
 * @author
 */
public class SalaryList extends JFrame {
    Integer pagesize=1;
    Integer pageNum=5;
    SalaryService salaryService = new SalaryServiceImpl();
    List<SalaryWithUser> list = null;//设置默认值
    Object[][] values = null;

    public SalaryList() {
        initComponents();
        init();
        initTable(null, null, null);// 显示table的全部内容

    }

    public void init() {
        this.setVisible(true);//可显
        //设置每个月份
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

    /**
     * 传入str ，year ，month 进行查询
     *
     * @param str
     * @param year
     * @param month
     */
    public void initTable(String str, String year, String month) {
        //设置表栏的内容
        Object[] objects = new Object[]{"id", "姓名", "关联的员工编号", "创建人ID", "更新人ID", "年份", "月份", "基本工资", "岗位工资", "工龄工资", "通讯补助", "交通补助", "个税代缴", "社保代缴", "住房公积金", "实发工资", "总工资"};
//接受文本框的内容
        str = staffName.getText();
        year = textField1.getText();
        month = comboBox1.getSelectedItem().toString();
        // 传入querySalary3查询
        list = salaryService.querysalary111(str, year, month,pagesize,pageNum);
//        list = salaryService.querySalary3(str, year, month);
        if (list != null) {
            //开辟位子
            values = new Object[list.size()][20];
            // 遍历输出
            for (int i = 0; i < list.size(); i++) {
                // 设置每一列的内容
                Object[] object = new Object[20];
                object[0] = i + 1; // 序号
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

                values[i] = object; // 值
            }
        } else {
            values = new Object[0][20];
        }
        //传入表中
        table1.setModel(new DefaultTableModel(values, objects));
        //页面跳转
        button6.setText(String.valueOf(pagesize));
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        this.setVisible(false);//关闭当前窗口
//        MainJrame mainJrame=new MainJrame();
    }

    private void button2ActionPerformed(ActionEvent e) {//查询工资
        // TODO add your code here

        //输入三个条件进行查询
        if (StringUtils.isNotEmpty(staffName.getText()) && StringUtils.isNotEmpty(textField1.getText()) && StringUtils.isNotEmpty(comboBox1.getSelectedItem().toString())) {
            initTable(staffName.getText(), textField1.getText(), comboBox1.getSelectedItem().toString());
        } else {
            initTable(null, null, null);
        }
    }

    private void button3ActionPerformed(ActionEvent e) { // 修改工资
        // TODO add your code here
        int a = table1.getSelectedRow(); //获取选中的行
        //没选中
        if (a < 0) {
            JOptionPane.showMessageDialog(null, "请选择你要修改的行数！！");
            return;
        }

//        Object[] objects=values[a];
        SalaryWithUser salaryWithUser = list.get(a);//获取到他要修改的行
//        Integer id= (Integer) objects[1];  //获取到 userid
        //跳转UpdateSalary窗口，并将id 传过去
        UpdateSalary updateSalary = new UpdateSalary(salaryWithUser.getId());
    }

    private void button4ActionPerformed(ActionEvent e) {//上一页
        if (pagesize<=1){
            JOptionPane.showMessageDialog(null,"这是第一页");
            return;
        }else {
            pagesize--;
            initTable(staffName.getText(),textField1.getText(),comboBox1.getSelectedItem().toString());
        }

    }

    private void button5ActionPerformed(ActionEvent e) {//下一页
        // TODO add your code here
        List<SalaryWithUser> list=new ArrayList<>();
        SalaryService salaryService=new SalaryServiceImpl();
        list=salaryService.querySalary3(staffName.getText(),textField1.getText(),comboBox1.getSelectedItem().toString());
        int a=list.size();
        if (a%pageNum!=0){
            if (pagesize>=(a/pageNum+1)){
                JOptionPane.showMessageDialog(null,"这已经是最后一页了");
                return;
            }else {
                pagesize++;
                initTable(staffName.getText(),textField1.getText(),comboBox1.getSelectedItem().toString());
            }
        }
        if (a%pageNum==0){
            if (pagesize>=(a/pageNum)){
                JOptionPane.showMessageDialog(null,"这已经是最后一页了");
                return;
            }else {
                pagesize++;
                initTable(staffName.getText(),textField1.getText(),comboBox1.getSelectedItem().toString());
            }
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button2 = new JButton();
        label1 = new JLabel();
        staffName = new JTextField();
        button3 = new JButton();
        label2 = new JLabel();
        textField1 = new JTextField();
        comboBox1 = new JComboBox();
        button1 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();

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
        scrollPane1.setBounds(0, 60, 985, 465);

        //---- button2 ----
        button2.setText("\u67e5\u8be2\u5de5\u8d44");
        button2.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(230, 5, 110, button2.getPreferredSize().height);

        //---- label1 ----
        label1.setText("\u5458\u5de5\u59d3\u540d\u6216\u7f16\u53f7");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 1f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(15, 10), label1.getPreferredSize()));
        contentPane.add(staffName);
        staffName.setBounds(110, 5, 85, staffName.getPreferredSize().height);

        //---- button3 ----
        button3.setText("\u4fee\u6539\u5de5\u8d44");
        button3.addActionListener(e -> button3ActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(660, 5, 115, button3.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u5e74\u4efd");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 1f));
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(350, 10), label2.getPreferredSize()));
        contentPane.add(textField1);
        textField1.setBounds(395, 5, 55, textField1.getPreferredSize().height);
        contentPane.add(comboBox1);
        comboBox1.setBounds(480, 5, 120, comboBox1.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u8fd4\u56de\u4e0a\u4e00\u7ea7");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(855, 5), button1.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u4e0a\u4e00\u9875");
        button4.addActionListener(e -> button4ActionPerformed(e));
        contentPane.add(button4);
        button4.setBounds(235, 540, 80, 35);

        //---- button5 ----
        button5.setText("\u4e0b\u4e00\u9875");
        button5.addActionListener(e -> button5ActionPerformed(e));
        contentPane.add(button5);
        button5.setBounds(675, 540, 80, 35);
        contentPane.add(button6);
        button6.setBounds(460, 540, 80, 35);

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
    private JTextField staffName;
    private JButton button3;
    private JLabel label2;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton button1;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
