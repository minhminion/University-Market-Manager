/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shadow
 */
public class ChiTietHDGUI extends JFrame{
    private JTextField txtMaSP,txtSL,txtDonGia;
    private DefaultTableModel model;
    private JTable tbl;
    private int DWIDTH = 700;
    public ChiTietHDGUI()
    {
        init();
    }
    public void init()
    {
        setTitle("Chi tiết hóa đơn");
        setSize(DWIDTH,390);
        getContentPane().setBackground(new Color(55, 63, 81));
        setLayout(null);
        setLocation(250, 150);
        
        Font ftitle = new Font("Segoe UI",Font.BOLD,25);
        Font font0 = new Font("Segoe UI",Font.PLAIN,13);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
        
        //HEADER
        JLabel title = new JLabel("CHI TIẾT HÓA ĐƠN :",JLabel.CENTER);
        title.setFont(ftitle);
        title.setForeground(Color.WHITE);
        title.setBounds(0, 0, DWIDTH, 60);
        add(title);
/***************** PHẦN HIỂN THỊ THÔNG TIN ***************************/
        JPanel itemView = new JPanel(null);
        itemView.setBounds(new Rectangle(0, 60,this.DWIDTH, 230));
        itemView.setBackground(Color.WHITE);
        
        JLabel lbMaSP = new JLabel("Mã sản phẩm ");
        lbMaSP.setFont(font0);
        lbMaSP.setBounds(20,20,100,30);
        txtMaSP = new JTextField();
        txtMaSP.setBounds(new Rectangle(120,20,210,30));
        itemView.add(lbMaSP);
        itemView.add(txtMaSP);
        
        JLabel lbSL = new JLabel("Số lượng ");
        lbSL.setFont(font0);
        lbSL.setBounds(20,60,100,30);
        txtSL = new JTextField();
        txtSL.setBounds(new Rectangle(120,60,210,30));
        itemView.add(lbSL);
        itemView.add(txtSL);
        
        JLabel lbDonGia = new JLabel("Đơn giá ");
        lbDonGia.setFont(font0);
        lbDonGia.setBounds(20,100,100,30);
        txtDonGia = new JTextField();
        txtDonGia.setBounds(new Rectangle(120,100,210,30));
        itemView.add(lbDonGia);
        itemView.add(txtDonGia);
/**************** TẠO CÁC BTN XÓA, SỬA, VIEW, IN BILL ********************/

        JLabel btnEdit = new JLabel(new ImageIcon("./src/image/btnEdit_150px.png"));
        btnEdit.setBounds(new Rectangle(20,160,150,50));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
              
        JLabel btnDelete = new JLabel(new ImageIcon("./src/image/btnDelete_150px.png"));
        btnDelete.setBounds(new Rectangle(180,160,150,50));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));    
        
        itemView.add(btnEdit);
        itemView.add(btnDelete);
/*************************************************************************/

/**************** TẠO TABLE ************************************************************/

    /************** TẠO MODEL VÀ HEADER *********************************/
        Vector header = new Vector();
        header.add("Mă SP");
        header.add("Số lượng");
        header.add("Đơn giá");
        model = new DefaultTableModel(header,5);
        tbl = new JTable(model);
//        listSP(); //Đọc từ database lên table 
        
    /*******************************************************************/
        

    /******** CUSTOM TABLE ****************/
    
        // Chỉnh width các cột 
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(100);

        // Custom table
        tbl.setFocusable(false);
        tbl.setIntercellSpacing(new Dimension(0,0));     
        tbl.getTableHeader().setFont(font1);
        tbl.setRowHeight(30);
        tbl.setShowVerticalLines(false);              
        tbl.getTableHeader().setOpaque(false);
        tbl.setFillsViewportHeight(true);
        tbl.getTableHeader().setBackground(new Color(232,57,99));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.setSelectionBackground(new Color(52,152,219));          
        
        // Add table vào ScrollPane
        JScrollPane scroll = new JScrollPane(tbl);
        scroll.setBounds(new Rectangle(350, 20, 310 , 180));
        scroll.setBackground(null);
        
        itemView.add(scroll);
        
        add(itemView);
    /**************************************/
/*****************************************************************************************/
/*********************************************************************/
        
        setVisible(true);
    }
}
