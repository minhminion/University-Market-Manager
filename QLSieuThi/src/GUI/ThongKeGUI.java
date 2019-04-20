/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import BUS.ThongKeBUS;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Shadow
 */
public class ThongKeGUI extends JPanel implements ActionListener,ItemListener{
    private SanPhamBUS spBUS = new SanPhamBUS();
    private NhanVienBUS nvBUS = new NhanVienBUS();
    
    private JPanel paneTime = new JPanel() ;
    private JPanel paneTrimester = new JPanel() ;
    private JPanel panePeriod = new JPanel();
    private JRadioButton ckMaNull,ckMaSP, ckMaNV, ckMaKH ,ckDate, ckTrimester, ckPeriod;
    private JLabel lbMa = new JLabel();
    private JTextField txtMa = new JTextField();
    private JTextArea viewStatistic;
    private JButton btnStatistic ;
    private int DEFALUT_WIDTH;
    private JPanel form;
    private JLabel lbFromDate;
    private JLabel lbToDate;
    
    private JComboBox<String> cmbFromDate = new JComboBox<>();
    private JComboBox<String> cmbFromMonth = new JComboBox<>();
    private JComboBox<String> cmbFromYear = new JComboBox<>();
    private JComboBox<String> cmbToDate = new JComboBox<>();
    private JComboBox<String> cmbToMonth = new JComboBox<>();
    private JComboBox<String> cmbToYear = new JComboBox<>();
    private JComboBox<String> cmbTrimester = new JComboBox<>();
    private JComboBox<String> cmbPeriod = new JComboBox<>();
    private JLabel lbTrimester;
    private JComboBox<String> cmbYearTrimester = new JComboBox<>();
    private JComboBox<String> cmbYearPeriod = new JComboBox<>();
    private JLabel lbPeriod;
    private JLabel lbYearPeriod;
    private JLabel lbYearTrimester;
    private JButton btnSuggest;
    public ThongKeGUI(int width)
    {
        spBUS.listSP();
        nvBUS.listNV();
        DEFALUT_WIDTH = width;
        init();
    }
    public void init()
    {
        setLayout(null);
        setBackground(null);
        setBounds(new Rectangle(50, 0, this.DEFALUT_WIDTH - 220, 730));
        Font font0 = new Font("Segoe UI",Font.PLAIN,13);
        Font font1 = new Font("Segoe UI",Font.BOLD,13);
/************** PHẦN KIỄM KÊ *****************************************/
        JPanel control = new JPanel(null);
        control.setBackground(null);
        control.setBounds(new Rectangle(0,0,(DEFALUT_WIDTH - 220)/2,730));
        
        // Chuyển đổi giữa 2 panel
        JTabbedPane controlTab = new JTabbedPane();
        controlTab.setBounds(new Rectangle(0,20,(DEFALUT_WIDTH - 220)/2 - 10,150));
        
        JPanel controlAll = new JPanel(new GridLayout(2,5));
        controlAll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // CHỌN MÃ CẦN THỐNG KÊ
        ButtonGroup id = new ButtonGroup();
        ckMaSP = new JRadioButton("Sản Phẩm");
        ckMaSP.setFont(font0);
        ckMaSP.setSelected(true);
        ckMaSP.addItemListener(this);
        id.add(ckMaSP);
        ckMaNV = new JRadioButton("Nhân viên");
        ckMaNV.addItemListener(this);
        ckMaNV.setFont(font0);
        id.add(ckMaNV);
        ckMaKH = new JRadioButton("Khách hàng");
        ckMaKH.addItemListener(this);
        ckMaKH.setFont(font0);
        id.add(ckMaKH);
        ckMaNull = new JRadioButton("Trống");
        ckMaNull.addItemListener(this);
        ckMaNull.setFont(font0);
        id.add(ckMaNull);
        
        // CHỌN KIỂU THỜI GIAN
        ButtonGroup Time = new ButtonGroup();
        ckDate = new JRadioButton("DD/MM/YYY");
        ckDate.addItemListener(this);
        ckDate.setFont(font0);
        ckDate.setSelected(true);
        Time.add(ckDate);
        ckTrimester = new JRadioButton("Quý");
        ckTrimester.setFont(font0);
        ckTrimester.addItemListener(this);
        Time.add(ckTrimester);
        ckPeriod = new JRadioButton("Kỳ(4 tháng)");
        ckPeriod.setFont(font0);
        ckPeriod.addItemListener(this);
        Time.add(ckPeriod);
        
        JLabel lbId = new JLabel("Chọn mã");
        lbId.setFont(font1);
        controlAll.add(lbId);
        controlAll.add(ckMaSP);
        controlAll.add(ckMaNV);
        controlAll.add(ckMaKH);
        controlAll.add(ckMaNull);
        
        JLabel lbTime = new JLabel("Chọn thời gian");
        lbTime.setFont(font1);
        controlAll.add(lbTime);
        controlAll.add(ckDate);
        controlAll.add(ckTrimester);
        controlAll.add(ckPeriod);
        
        JPanel controlTop = new JPanel(new GridLayout(2,4));
        controlTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        controlTab.add(controlAll,"Tất cả");
        controlTab.add(controlTop,"Top");
        control.add(controlTab);
        
        //*********************** Panel điền thông tin ***********************//
        form = new JPanel(null);
        form.setBackground(null);
        form.setBounds(new Rectangle(0,200,(DEFALUT_WIDTH - 220)/2 - 10,300));
        
        lbMa.setBounds(new Rectangle(0,0,100,30));
        lbMa.setFont(font0);
        txtMa.setBounds(new Rectangle(110,0,230,30));
        txtMa.setFont(font0);
        btnSuggest = new JButton("...");
        btnSuggest.setBounds(new Rectangle(340,0,30,30));
        btnSuggest.addActionListener(this);
        
        form.add(lbMa);
        form.add(txtMa);
        form.add(btnSuggest);
        
        /**************** CHỌN TIME ********************************/
        paneTime = new JPanel(null);
        paneTime.setBackground(null);
        paneTime.setBounds(new Rectangle(0,40,(DEFALUT_WIDTH - 220)/2 - 10,260));
        
        // FROM
        lbFromDate = new JLabel("Từ ngày");
        lbFromDate.setFont(font0);
        lbFromDate.setBounds(new Rectangle(0,0,100,30));
        
        cmbFromDate.setBounds(new Rectangle(110,0,80,30));
        cmbFromDate.setFont(font0);
        listDate(cmbFromDate,true);
        JLabel sepTime0 = new JLabel("/");
        sepTime0.setFont(font0);
        sepTime0.setBounds(new Rectangle(195,0,10,30));
        
        cmbFromMonth.addActionListener(this);
        cmbFromMonth.setBounds(new Rectangle(205,0,80,30));
        cmbFromMonth.setFont(font0);
        listMonth(cmbFromMonth);
        JLabel sepTime1 = new JLabel("/");
        sepTime1.setFont(font0);
        sepTime1.setBounds(new Rectangle(290,0,10,30));
        
        cmbFromYear.addActionListener(this);
        cmbFromYear.setBounds(new Rectangle(300,0,80,30));
        cmbFromYear.setFont(font0);
        listYear(cmbFromYear);
        
        System.out.print(cmbFromYear.getSelectedIndex());
        
        paneTime.add(lbFromDate);
        paneTime.add(cmbFromDate);
        paneTime.add(sepTime0);
        paneTime.add(cmbFromMonth);
        paneTime.add(sepTime1);
        paneTime.add(cmbFromYear);
        
        // TO
        lbToDate = new JLabel("Đến ngày");
        lbToDate.setFont(font0);
        lbToDate.setBounds(new Rectangle(0,40,100,30));
        
        cmbToDate.setBounds(new Rectangle(110,40,80,30));
        cmbToDate.setFont(font0);
        listDate(cmbToDate,false);
        JLabel sepTime2 = new JLabel("/");
        sepTime2.setFont(font0);
        sepTime2.setBounds(new Rectangle(195,40,10,30));
        
        cmbToMonth.addActionListener(this);
        cmbToMonth.setBounds(new Rectangle(205,40,80,30));
        cmbToMonth.setFont(font0);
        listMonth(cmbToMonth);
        JLabel sepTime3 = new JLabel("/");
        sepTime3.setFont(font0);
        sepTime3.setBounds(new Rectangle(290,40,10,30));
        
        cmbToYear.addActionListener(this);
        cmbToYear.setBounds(new Rectangle(300,40,80,30));
        cmbToYear.setFont(font0);
        listYear(cmbToYear);
        
        
        paneTime.add(lbFromDate);
        paneTime.add(cmbFromDate);
        paneTime.add(sepTime0);
        paneTime.add(cmbFromMonth);
        paneTime.add(sepTime1);
        paneTime.add(cmbFromYear);
        
        paneTime.add(lbToDate);
        paneTime.add(cmbToDate);
        paneTime.add(sepTime2);
        paneTime.add(cmbToMonth);
        paneTime.add(sepTime3);
        paneTime.add(cmbToYear);
        
        form.add(paneTime);
        /***********************************************************/   
        
        /*************** CHỌN THEO QUÝ *****************************/
        paneTrimester = new JPanel(null);
        paneTrimester.setBackground(null);
        paneTrimester.setBounds(new Rectangle(0,40,(DEFALUT_WIDTH - 220)/2 - 10,260));
        
        lbTrimester = new JLabel("Quý");
        lbTrimester.setFont(font0);
        lbTrimester.setBounds(new Rectangle(0,0,100,30));
        
        cmbTrimester.setBounds(new Rectangle(110,0,160,30));
        cmbTrimester.setFont(font0);
        for(int i = 1 ; i <=12  ; i+=3)
        {
            cmbTrimester.addItem("Quý "+(i+2)/3+" ( tháng "+i+" - "+(i+2)+" )");
        }
        
        lbYearTrimester = new JLabel("Năm",JLabel.CENTER);
        lbYearTrimester.setFont(font0);
        lbYearTrimester.setBounds(new Rectangle(270,0,40,30));
        
        cmbYearTrimester.setBounds(new Rectangle(310,0,80,30));
        cmbYearTrimester.setFont(font0);
        listYear(cmbYearTrimester);
        cmbYearTrimester.removeItemAt(0);
        
        paneTrimester.add(lbTrimester);
        paneTrimester.add(cmbTrimester);
        paneTrimester.add(lbYearTrimester);
        paneTrimester.add(cmbYearTrimester);
        
        paneTrimester.setVisible(false);
        form.add(paneTrimester);
        /***********************************************************/
        
        /*************** CHỌN THEO KỲ *****************************/
        panePeriod = new JPanel(null);
        panePeriod.setBackground(null);
        panePeriod.setBounds(new Rectangle(0,40,(DEFALUT_WIDTH - 220)/2 - 10,260));
        
        lbPeriod = new JLabel("Kỳ");
        lbPeriod.setFont(font0);
        lbPeriod.setBounds(new Rectangle(0,0,100,30));
        
        cmbPeriod.setBounds(new Rectangle(110,0,160,30));
        cmbPeriod.setFont(font0);
        for(int i = 1 ; i <=12  ; i+=4)
        {
            cmbPeriod.addItem("Kỳ "+(i+3)/4+" ( tháng "+i+" - "+(i+3)+" )");
        }
        
        lbYearPeriod = new JLabel("Năm",JLabel.CENTER);
        lbYearPeriod.setFont(font0);
        lbYearPeriod.setBounds(new Rectangle(270,0,40,30));
        
        cmbYearPeriod.setBounds(new Rectangle(310,0,80,30));
        cmbYearPeriod.setFont(font0);
        listYear(cmbYearPeriod);
        cmbYearPeriod.removeItemAt(0);
        
        panePeriod.add(lbPeriod);
        panePeriod.add(cmbPeriod);
        panePeriod.add(lbYearPeriod);
        panePeriod.add(cmbYearPeriod);
        
        panePeriod.setVisible(false);
        form.add(panePeriod);
        /***********************************************************/
        
        control.add(form);
        
        btnStatistic = new JButton("Thống kê");
        btnStatistic.setFont(font0);
        btnStatistic.setBounds(new Rectangle(50,500,(DEFALUT_WIDTH - 220)/2 - 100,30));
        btnStatistic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStaticticAction(e);
            }
        });
        
        control.add(btnStatistic);
        
        add(control);
/*********************************************************************/

/*************** PHẦN HIỆN THÔNG TIN *********************************/
        JPanel view = new JPanel(null);
        view.setBounds(new Rectangle((DEFALUT_WIDTH - 220)/2,0,(DEFALUT_WIDTH - 220)/2,730));
        
        viewStatistic = new JTextArea();
        viewStatistic.setEditable(false);
        JScrollPane scroll = new JScrollPane(viewStatistic);
        scroll.setBounds(new Rectangle(0,20,(DEFALUT_WIDTH - 220)/2 - 100 ,500));
        view.add(scroll);
        
        add(view);
/*********************************************************************/
    }
    public static void main(String[]args)
    {
        JFrame frame = new JFrame();
        frame.setSize(1080, 730);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ThongKeGUI(1300));
        frame.setVisible(true);
    }
    public void listDate(JComboBox cmb,boolean flag) // TRUE is FROM - FALSE is TO
    {
        cmb.addItem("Không");
        int thisMonth = 12 , thisDate = 31 ,thisYear = Calendar.getInstance().get(Calendar.YEAR);
        if( cmbFromYear.getSelectedIndex() > 0 || cmbToYear.getSelectedIndex() > 0)
        {
            thisYear = flag ? Integer.parseInt(cmbFromYear.getSelectedItem().toString()) : Integer.parseInt(cmbToYear.getSelectedItem().toString());
//            System.out.println(thisYear);
        }
        if( cmbFromMonth.getSelectedIndex() > 0 || cmbToMonth.getSelectedIndex() > 0)
        {
            thisMonth = flag?cmbFromMonth.getSelectedIndex():cmbToMonth.getSelectedIndex();
//            System.out.println(thisMonth);
        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(thisYear, thisMonth - 1, 1);
//        System.out.println(calendar.getTime());
        thisDate = calendar.getActualMaximum(Calendar.DATE);
//        System.out.println(thisDate);
        
        for(int i = 1 ; i <= thisDate ; i++)
        {
            cmb.addItem(i);
        }
    }
    public void listMonth(JComboBox cmb)
    {
        cmb.addItem("Không");
        for(int i = 1 ; i <= 12 ; i++ )
        {
            cmb.addItem(i);
        }
    }
    public void listYear(JComboBox cmb)
    {
        cmb.addItem("Không");
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for(int i = thisYear ; i >= thisYear - 20 ; i-- )
        {
            cmb.addItem(i);
        }
    }
    
    public void btnStaticticAction (ActionEvent e)
    {
        ThongKeBUS tk = new ThongKeBUS();
        String ma = txtMa.getText();
        Object obj = null;
        if(ckMaSP.isSelected())
        {
            obj = new SanPhamDTO();
            obj = spBUS.getSP(ma);
            if(obj == null)
            {
                JOptionPane.showMessageDialog(null, "Không tồn tại sản phầm !!");
                return;
            }
        }
        if(ckMaNV.isSelected())
        {
            obj = new NhanVienDTO();
            obj = nvBUS.get(ma);
            if(obj == null)
            {
                JOptionPane.showMessageDialog(null, "Không tồn tại khách hàng !!");
                return;
            }
        }
        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        
        // THÔNG KÊ THEO NGÀY
        if(ckDate.isSelected())
        {
            int fYear = cmbFromYear.getSelectedIndex()>0 ? Integer.parseInt(cmbFromYear.getSelectedItem().toString()) : 2000;
            int fMonth = cmbFromMonth.getSelectedIndex()>0 ? cmbFromMonth.getSelectedIndex()-1 : 0;
            int fDate =  cmbFromDate.getSelectedIndex()>0 ? Integer.parseInt(cmbFromDate.getSelectedItem().toString()) : 1;
            from.set(fYear, fMonth, fDate, 0, 0, 0);

            int tYear = cmbToYear.getSelectedIndex()>0 ? Integer.parseInt(cmbToYear.getSelectedItem().toString()) : Calendar.getInstance().get(Calendar.YEAR);
            int tMonth = cmbToMonth.getSelectedIndex()>0 ? cmbToMonth.getSelectedIndex()-1 : 11;
            int maxDate = cmbToDate.getItemCount();
            System.out.println(maxDate);
            int tDate =  cmbToDate.getSelectedIndex()>0 ? Integer.parseInt(cmbToDate.getSelectedItem().toString()) : maxDate-1;
            to.set(tYear, tMonth, tDate,23,0,0);
        }
        // THỐNG KÊ THEO QUÝ
        else if(ckTrimester.isSelected())
        {
            int year = Integer.parseInt(cmbYearTrimester.getSelectedItem().toString());
            int fMonth = (cmbTrimester.getSelectedIndex()+1)*3-2;
            int tMonth = fMonth + 2;
            
            from.set(year,fMonth-1,1,0,0,0);
            to.set(year, tMonth-1, 1,23,0,0);
            int dateOfMonth = to.getActualMaximum(Calendar.DAY_OF_MONTH);
            to.set(Calendar.DATE, dateOfMonth);
        }
        else if(ckPeriod.isSelected()) 
        {
            int year = Integer.parseInt(cmbYearPeriod.getSelectedItem().toString());
            int fMonth = (cmbPeriod.getSelectedIndex()+1)*4-3;
            int tMonth = fMonth + 3;
            
            from.set(year,fMonth-1,1,0,0,0);
            to.set(year, tMonth-1, 1,23,0,0);
            int dateOfMonth = to.getActualMaximum(Calendar.DAY_OF_MONTH);
            to.set(Calendar.DATE, dateOfMonth);
        }
                
        
        System.out.print(from.getTime());
        System.err.println(to.getTime());
        
        if(to.before(from))
        {
            JOptionPane.showMessageDialog(null,"Lỗi");
            return;
        }
        
        String result = "";
        if(ckMaSP.isSelected())
        {
            result = tk.StatisticSP(ma, from, to);
        }
        else if(ckMaNV.isSelected())
        {
            result = tk.StatisticNV(ma, from, to);
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
        
        viewStatistic.setText( outStatistic( obj,sdf.format( from.getTime() ), sdf.format( to.getTime() ) ,result) );
    }
    public String outStatistic(Object obj,String fromDate, String toDate, String result)
    {
        String s = "Từ ngày : "+fromDate+"\n";
        s += "Đến ngày : "+toDate+"\n";
        s += "--------------------------------------------- \n";
        if(ckMaSP.isSelected())
        {
            SanPhamDTO sp = (SanPhamDTO) obj; 
            s += "Sản phẩm :"+sp.getMaSP()+"\t";
            s += "Tên : "+sp.getTenSP()+"\n";
            s += result;
        }
        if(ckMaNV.isSelected())
        {
            NhanVienDTO nv = (NhanVienDTO) obj; 
            s += "Mã nhân viên :"+nv.getMaNV()+"\n";
            s += "Họ và tên : "+nv.getHoNV().concat(" "+nv.getTenNV())+"\n";
            s += "Tuổi :"+( Calendar.getInstance().get(Calendar.YEAR) - nv.getNamSinh() )+"\n";
            s += "Phái :"+nv.getPhai()+"\n";
            s += result;
        }
        return s;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj.equals(cmbFromMonth) || obj.equals(cmbFromYear))
        {
            cmbFromDate.removeAllItems();
            listDate(cmbFromDate,true);
        }
        if(obj.equals(cmbToMonth) || obj.equals(cmbToYear))
        {
            cmbToDate.removeAllItems();
            listDate(cmbToDate,false);
        }
        if(obj.equals(btnSuggest))
        {
            if(ckMaSP.isSelected())
            {
                SuggestSanPham sp = new SuggestSanPham(txtMa.getText());
                String s = sp.getTextFieldContent();
                txtMa.setText(s.split("%")[0]);
            }
            else if(ckMaNV.isSelected())
            {
                SuggestNhanVien sp = new SuggestNhanVien();
                String s = sp.getTextFieldContent();
                txtMa.setText(s);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) 
    {
        // Chọn Control TIME
        if(ckDate.isSelected())
        {
            paneTime.setVisible(true);
            paneTrimester.setVisible(false);
            panePeriod.setVisible(false);
        }
        else if(ckTrimester.isSelected())
        {
            paneTime.setVisible(false);
            paneTrimester.setVisible(true);
            panePeriod.setVisible(false);
        }
        else
        {
            paneTime.setVisible(false);
            paneTrimester.setVisible(false);
            panePeriod.setVisible(true);
        }
        
        // Chọn Control MĂ
        if(ckMaSP.isSelected())
        {
            lbMa.setVisible(true);
            txtMa.setVisible(true);
            lbMa.setText("Mă sản phẩm");
        }
        else if(ckMaNV.isSelected())
        {
            lbMa.setVisible(true);
            txtMa.setVisible(true);
            lbMa.setText("Mã nhân viên");
        }
        else if(ckMaKH.isSelected())
        {
            lbMa.setVisible(true);
            txtMa.setVisible(true);
            lbMa.setText("Mã khách hàng");
        }
        else
        {
            lbMa.setVisible(false);
            txtMa.setVisible(false);
        }
   
    }
}
