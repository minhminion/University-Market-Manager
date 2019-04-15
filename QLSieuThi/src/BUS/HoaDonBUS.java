/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.HoaDonDAO;
import DTO.HoaDonDTO;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Shadow
 */
public class HoaDonBUS {
    private ArrayList<HoaDonDTO> dsHD ;
    public HoaDonBUS()
    {
        
    }
    public void list()
    {
        HoaDonDAO hdDAO = new HoaDonDAO();
        dsHD = new ArrayList<>();
        dsHD = hdDAO.list();
    }
    public void add(HoaDonDTO hd)
    {
        dsHD.add(hd);
        HoaDonDAO hdDAO = new HoaDonDAO();
        hdDAO.add(hd);
    }

    public void delete(String idChiTietHD)
    {
        for(HoaDonDTO hd : dsHD )
        {
            if(hd.getMaHD().equals(idChiTietHD))
            {
                dsHD.remove(hd);
                HoaDonDAO hdDAO = new HoaDonDAO();
                hdDAO.delete(idChiTietHD);
                return;
            }
        }
    }
    public int set(HoaDonDTO s)
    {
        for(int i = 0 ; i < dsHD.size() ; i++)
        {
            if(dsHD.get(i).getMaHD().equals(s.getMaHD()))
            {
                dsHD.set(i, s);
                HoaDonDAO hdDAO = new HoaDonDAO();
                hdDAO.set(s);
                return i;
            }
        }
        return -1;
    }
    public ArrayList<HoaDonDTO> search( int mm, int yyy,double max, double min,ArrayList<String> mahd)
    {
        int mm1 = 0, mm2 = 12;
        int yyy1 = 0, yyy2 = Calendar.getInstance().get(Calendar.YEAR);
        
        if(mm != -1)
        {
            mm1 = mm;
            mm2 = mm;
        }
        if(yyy != 0)
        {
            yyy1 = yyy;
            yyy2 = yyy;
        }
        
        ArrayList<HoaDonDTO> ds = getListWidthArray(mahd);
        ArrayList<HoaDonDTO> search = new ArrayList<>();
        for(HoaDonDTO hd : ds)
        {
            Timestamp time = Timestamp.valueOf(hd.getNgayHD());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time.getTime());;
            
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            
            if( hd.getTongTien() >= min && hd.getTongTien() <= max 
                && (month >= mm1 && month <= mm2)
                && (year >= yyy1 && year <= yyy2))
            {
                search.add(hd);
            }
        }
        return search;
    }
    public boolean check(String maHD)
    {
        for(HoaDonDTO hd : dsHD)
        {
            if( hd.getMaHD().equals(maHD))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<HoaDonDTO> getListWidthArray(ArrayList<String> s)
    {
        ArrayList<HoaDonDTO> ds = new ArrayList<>();
        if(s == null) return dsHD;
        for(HoaDonDTO hd : dsHD)
        {
            String mahd = hd.getMaHD();
            for(String a: s)
            {
                if(mahd.equals(a))
                {
                    ds.add(hd);
                }
            }
        }
        return ds;
    }
    public ArrayList<HoaDonDTO> getList() {
        return dsHD;
    }
}
