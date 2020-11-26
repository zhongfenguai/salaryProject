/*
 * Created by JFormDesigner on Thu Nov 05 10:30:40 GMT+08:00 2020
 */

package com.cbf.view;

import com.cbf.entity.SalaryWithUser;
import com.cbf.entity.Staff;
import com.cbf.service.SalaryService;
import com.cbf.service.StaffService;
import com.cbf.service.impl.SalaryServiceImpl;
import com.cbf.service.impl.StaffServiceImpl;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**管理员 查看员工
 * @author
 */
public class StaffList extends JFrame {
    Integer pagesize=1;
    Integer pageNum=5;
    //设置默认值
    List<Staff> list = null;
    Object[][] values = null;
    boolean b = false;//设置默认值
    StaffService staffService = new StaffServiceImpl();

    public StaffList() {
        initComponents();
        init();
        initTable(null);
    }

    public void init() {
        this.setVisible(true);//可显
    }

    /**
     * 传入realname
     * @param realName
     */
    public void initTable(String realName) {
        // 设置表栏的属性
        Object[] objects = new Object[]{"序号", "id", "用户编号", "用户类型", "真实姓名", "密码", "用户名", "身份证", "性别", "生日", "电话", "邮箱", "创建者id", "更新者id"};
        // 传入realname 进行查询
        list = staffService.querystaff12(realName,pagesize,pageNum);
        if (list != null) {
            //开辟位子
            values = new Object[list.size()][15];
            //遍历 获取
            for (int i = 0; i < list.size(); i++) {
                Object[] object = new Object[20];
                object[0] = i + 1;//序号
                object[1] = list.get(i).getId();
                object[2] = list.get(i).getUserNo();
                object[3] = list.get(i).getUserType();
                object[4] = list.get(i).getRealName();
                object[5] = list.get(i).getPassWord();
                object[6] = list.get(i).getUserName();
                object[7] = list.get(i).getIdCardNumber();
                object[8] = list.get(i).getSex();
                object[9] = list.get(i).getBornDate();
                object[10] = list.get(i).getTelephone();
                object[11] = list.get(i).getEmail();
                object[12] = list.get(i).getCreatUserId();
                object[13] = list.get(i).getUpdateUserId();
                values[i] = object;
            }
        } else {
            values = new Object[0][20];
        }
        //设置表的内容
        table1.setModel(new DefaultTableModel(values, objects));
        button7.setText(String.valueOf(pagesize));
    }

    private void button1ActionPerformed(ActionEvent e) {//修改
        // TODO add your code here
        int a = table1.getSelectedRow();//获取选中的行
        if (a < 0) {
            JOptionPane.showMessageDialog(null, "请选择你要删除的行数");
            return;
        }

//        Object[] objects = values[a];//
//        Integer userNo = (Integer) objects[1]; //获取到 userid
        Staff staff = list.get(a); // 获取到他要删除 的行

        boolean b = staffService.deleteId(staff.getId());//根据id 删除几记录

        if (b) {
            JOptionPane.showMessageDialog(null, "修改成功");
            initTable(realName.getText());//刷新列表
        }
    }

    private void button2ActionPerformed(ActionEvent e) { //查询

        // TODO add your code here
        initTable(realName.getText());//刷新列表
    }

    private void button3ActionPerformed(ActionEvent e) { //修改
        // TODO add your code
        int a = table1.getSelectedRow(); //获取选中的行
        if (a < 0) {
            JOptionPane.showMessageDialog(null, "请选择你要修改的行数！！");
            return;
        }
//        Object[] objects=values[a];
        Staff staff = list.get(a);//获取到他要删除的行
//        Integer id= (Integer) objects[1];
        StaffUpdate staffUpdate = new StaffUpdate(staff.getId());// 用id修改
        this.setVisible(false); // 关闭当前窗口
    }

    private void button4ActionPerformed(ActionEvent e) {//返回上一级
        // TODO add your code here
        this.setVisible(false);//关闭当前窗口
//        MainJrame mainJrame=new MainJrame();

    }

    private void button5ActionPerformed(ActionEvent e) {//上一页
        // TODO add your code here
        if (pagesize<=1){
            JOptionPane.showMessageDialog(null,"这是第一页");
            return;
        }else {
            pagesize--;
            initTable(realName.getText());
        }
    }

    private void button6ActionPerformed(ActionEvent e) {//下一页
        // TODO add your code here
        List<Staff> list=new ArrayList<>();
        StaffService staffService=new StaffServiceImpl();
        //将文本框的realname 插入查找
        list=staffService.querystaff2(realName.getText());
        int a=list.size();//
        if (a%pageNum!=0){
            if (pagesize>=(a/pageNum+1)){
                JOptionPane.showMessageDialog(null,"这已经是最后一页了");
                return;
            }else {
                pagesize++;
                initTable(realName.getText());
            }
        }
        if (a%pageNum==0){
            if (pagesize>=(a/pageNum)){
                JOptionPane.showMessageDialog(null,"这已经是最后一页了");
                return;
            }else {
                pagesize++;
                initTable(realName.getText());
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        label1 = new JLabel();
        realName = new JTextField();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();

        //======== this ========
        setTitle("\u67e5\u770b\u5458\u5de5");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 45, 840, 340);

        //---- button1 ----
        button1.setText("\u5220\u9664");
        button1.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1);
        button1.setBounds(350, 5, button1.getPreferredSize().width, 25);

        //---- button2 ----
        button2.setText("\u67e5\u8be2");
        button2.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2);
        button2.setBounds(280, 5, 60, button2.getPreferredSize().height);

        //---- label1 ----
        label1.setText("\u5458\u5de5\u540d\u5b57\u6216id");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 1f));
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(20, 10), label1.getPreferredSize()));
        contentPane.add(realName);
        realName.setBounds(130, 5, 85, realName.getPreferredSize().height);

        //---- button3 ----
        button3.setText("\u4fee\u6539");
        button3.addActionListener(e -> button3ActionPerformed(e));
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(420, 5), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u8fd4\u56de\u4e0a\u4e00\u7ea7");
        button4.addActionListener(e -> button4ActionPerformed(e));
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(655, 5), button4.getPreferredSize()));

        //---- button5 ----
        button5.setText("\u4e0a\u4e00\u9875");
        button5.addActionListener(e -> button5ActionPerformed(e));
        contentPane.add(button5);
        button5.setBounds(new Rectangle(new Point(240, 400), button5.getPreferredSize()));

        //---- button6 ----
        button6.setText("\u4e0b\u4e00\u9875");
        button6.addActionListener(e -> button6ActionPerformed(e));
        contentPane.add(button6);
        button6.setBounds(new Rectangle(new Point(465, 400), button6.getPreferredSize()));
        contentPane.add(button7);
        button7.setBounds(365, 400, 55, 30);

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
    private JButton button1;
    private JButton button2;
    private JLabel label1;
    private JTextField realName;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
