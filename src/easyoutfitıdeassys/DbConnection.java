/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyoutfitıdeassys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import easyoutfitıdeas.model.sensor;
import easyoutfitıdeas.model.veri;



public class DbConnection {
    public Connection connection;
    public void baglan() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/eos?verifyServerCertificate=false&useSSL=true";
        String username = "root";
        String password = "";
        System.out.println("Connecting database...");
        this.connection = DriverManager.getConnection(url, username, password); 
    }
    public boolean girisKontrol(String name,String password) throws SQLException, ClassNotFoundException{
        baglan();
        Statement stmt = connection.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM eos.users");
        
        while (rs.next()) {
        String k = rs.getString("name");
        String p = rs.getString("password");
        if(k.equals(name) && p.equals(password)){
            return true;
        }
        }
        return false;
        
        
    }
    public void vt_gelen_kaydet(String sic,String nem) throws SQLException{
        baglan();
      
          PreparedStatement ps = connection.prepareStatement("insert into gelen_sensor(gelen_sicaklik, gelen_nem ) values(?,?)");
            ps.setString(1, sic);
            ps.setString(2, nem);
            ps.executeUpdate();
        
        
    }
    public List<sensor> sensor_listele() throws SQLException{
        baglan();
        List<sensor> gelen = new ArrayList<>();
     
        sensor veriler ;
        Statement stmt;
        try {
            stmt = connection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM gelen_sensor");
           
            while (rs.next()) {
                System.out.println(rs.getString("gelen_sicaklik"));
                System.out.println(rs.getString("gelen_nem"));
                veriler = new sensor();
                veriler.setSicaklik(rs.getString("gelen_sicaklik"));
                veriler.setNem(rs.getString("gelen_nem"));
                
                    gelen.add(veriler);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gelen;
    }
        public void vt_giden_kaydet(String led) throws SQLException{
        baglan();
      
          PreparedStatement ps = connection.prepareStatement("insert into giden_sensor(giden_led ) values(?)");
            ps.setString(1, led);
            ps.executeUpdate();
        
        
    }
    
      public List<veri> veri_listele() throws SQLException{
        baglan();
        List<veri> gelen = new ArrayList<>();
     
        veri veriler ;
        Statement stmt;
        try {
            stmt = connection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM giden_sensor");
           
            while (rs.next()) {
                System.out.println(rs.getString("giden_led"));
               
                veriler = new veri();
                veriler.setLed(rs.getString("giden_led"));
               
                
                    gelen.add(veriler);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gelen;
    }
    
}

