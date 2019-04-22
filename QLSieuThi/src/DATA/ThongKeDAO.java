/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.HoaDonDTO;
import DTO.NhapHangDTO;
import com.sun.javafx.binding.StringFormatter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shadow
 */
public class ThongKeDAO {
    public ThongKeDAO()
    {
        
    }
    public String StatisticSP( ArrayList<HoaDonDTO> listHd, ArrayList<NhapHangDTO> listNH,String MaSP)
    {
        String s = "";
        int slOut=0,sumOut = 0;
        int slIn=0,sumIn = 0;
        try {
            MySQLConnect mySQL = new MySQLConnect();
            // BÁN
            if(!listHd.isEmpty())
            {
                String sql1 = "SELECT SUM(SOLUONG) AS SL,SUM(SOLUONG*DONGIA) AS TONGTIEN FROM chitiethd WHERE (";
                for(int i = 0 ; i < listHd.size() ; i++)
                {
                    String mahd = listHd.get(i).getMaHD();
                    if(i == (listHd.size() - 1))
                    {
                        sql1 += "MAHD ='"+ mahd +"') ";
                        break;
                    }
                    sql1 += "MAHD ='"+ mahd +"' OR ";
                }
                sql1+= "AND MASP = '"+MaSP+"' ";
                sql1 += "GROUP BY MAHD";
                System.out.println(sql1);
                ResultSet rs1 = mySQL.executeQuery(sql1);
                while(rs1.next())
                {
                    slOut += rs1.getInt("SL");
                    sumOut += rs1.getInt("TONGTIEN");
                }
            }
            
            // NHẬP
            if(!listNH.isEmpty())
            {
                String sql2 = "SELECT SUM(SOLUONG) AS SL,SUM(TONGTIEN) AS TONGTIEN FROM phieunhaphang WHERE (";
                for(int i = 0 ; i < listNH.size() ; i++)
                {
                    String idNhap = listNH.get(i).getIdNH();
                    if(i == (listNH.size() - 1))
                    {
                        sql2 += "IDNHAP = '"+ idNhap +"') ";
                        break;
                    }
                    sql2 += "IDNHAP = '"+ idNhap +"' OR ";
                }
                sql2+= "AND MASP = '"+MaSP+"' ";
                sql2 += "GROUP BY IDNHAP";
                System.out.println(sql2);
                ResultSet rs2 = mySQL.executeQuery(sql2);
                while(rs2.next())
                {
                    slIn += rs2.getInt("SL");
                    sumIn += rs2.getInt("TONGTIEN");
                }

            }
            
            if(mySQL.isConnect()) mySQL.disConnect();
            
            s += String.format("Số lượng bán : %6d || Số lượng nhập  : %5d\n",slIn,slOut);
            s += String.format("Tổng tiền    : %5dđ || Tổng tiền nhập : %5dđ\n",sumIn,sumOut);
            s += "--------------------------------------------------- \n";
            s += "TỔNG THU NHẬP : "+(sumOut-sumIn)+"VNĐ"+"\n";      
            System.out.print(s);
            
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public String StatisticNV(ArrayList<HoaDonDTO> listHd,String MaNV)
    {
        String s ="";
        int sum = 0;
        String listItem = String.format("|%10s|%10s|\n","Mã SP","Số lượng");
        if(!listHd.isEmpty())
        {
            MySQLConnect mySQL = new MySQLConnect();
        try {
            // Tổng tiền 
            String sql1 = "SELECT SUM(TONGTIEN) AS TONGTIEN FROM hoadon WHERE (";
            for(int i = 0 ; i < listHd.size() ; i++)
            {
                String mahd = listHd.get(i).getMaHD();
                if(i == (listHd.size() - 1))
                {
                    sql1 += "MAHD ='"+ mahd +"') ";
                    break;
                }
                sql1 += "MAHD ='"+ mahd +"' OR ";
            }
            sql1+= "AND MANV = '"+MaNV+"' ";
            sql1 += "GROUP BY MANV";
            System.out.println(sql1);
            ResultSet rs1 = mySQL.executeQuery(sql1);
            while(rs1.next())
            {
                sum += rs1.getInt("TONGTIEN");
                
            }
            
            // Mã SP || Số lượng 
            String sql2 = "SELECT MASP,SUM(chitiethd.SOLUONG) AS SOLUONG FROM chitiethd WHERE chitiethd.MAHD IN (SELECT MAHD FROM hoadon WHERE (";
            for(int i = 0 ; i < listHd.size() ; i++)
            {
                String mahd = listHd.get(i).getMaHD();
                if(i == (listHd.size() - 1))
                {
                    sql2 += "MAHD ='"+ mahd +"') ";
                    break;
                }
                sql2 += "MAHD ='"+ mahd +"' OR ";
            }
            sql2+= "AND MANV = '"+MaNV+"' )";
            sql2 += "GROUP BY MASP";
            System.out.println(sql2);
            ResultSet rs2 = mySQL.executeQuery(sql2);
            while(rs2.next())
            {
                listItem += String.format("|%10s|%10s|\n",rs2.getString("MASP"),rs2.getString("SOLUONG"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        s += listItem;
        s += "--------------------------------------------------- \n";
        s += "TỔNG THU NHẬP : "+sum+"VNĐ"+"\n";      
        return s;
    }
    /*
    SELECT DISTINCT chitiethd.MASP,SUM(chitiethd.SOLUONG) 
    FROM hoadon,chitiethd 
    WHERE (hoadon.MAHD ='003' OR hoadon.MAHD ='004' OR hoadon.MAHD ='006' OR hoadon.MAHD ='007') AND MANV = '001' 
    GROUP BY chitiethd.MASP
    */
}
