/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyoutfitıdeassys;

import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.awt.Component;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/*
  @author Sony
*/
public class Eos_main extends javax.swing.JFrame implements SerialPortEventListener{

    public static void setComPortName(String aComPortName) {
        comPortName = aComPortName;
    }

    String inputLine;
    String[] readArray = new String[100];
    SerialPort serialPort;
    int i = 0;
    private static String comPortName = "COM5";//BU BİLGİYİ FORM DAN DA ALABİLİRİZ

    public static String getComPortName() {
        return comPortName;
    }
    private static final String PORT_NAMES[] = {getComPortName(),};

    private BufferedReader input;
    private static OutputStream output;
    private static int TIME_OUT = 2000;
    private static int DATA_RATE = 9600;

    public void initialize() {
        System.setProperty("gnu.io.rxtx.SerialPorts", getComPortName());        
        CommPortIdentifier portId = null;        
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            if(currPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (currPortId.getName().equals(txt_ComPortName.getText())) {
                    System.out.println(txt_ComPortName.getText());
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            
            JOptionPane.showMessageDialog(null," Portuna bağlı cihaz yok!","Hata",JOptionPane.ERROR_MESSAGE);
            System.out.println("Porta bağlı cihaz yok!");
            return;
        }
System.out.println(portId);
        try {
            serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            serialPort.addEventListener((SerialPortEventListener) this);
           serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }
    DefaultListModel model = new DefaultListModel();
    String sicaklik,nem;
    int sira=0;
    @Override
    public synchronized void serialEvent(SerialPortEvent oEvent) {

        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {
                if (input.ready() == true) {
                    inputLine = input.readLine();
                    Object node = inputLine;
                    model.addElement(node);
                    if(inputLine.contains("Sicaklik_(Celcius):")){
                        String[] cekilen={};
                        cekilen=inputLine.split(" ");
                        this.sicaklik=cekilen[1];
                        //this.sira++;
                    }
                    if(inputLine.contains("Nem(%):")){
                        String[] cekilen={};
                        cekilen=inputLine.split(" ");
                        this.nem=cekilen[1];
                        this.sira++;
                    }
                    if(sira==5){
                        System.out.println("db kayıt");
                        DbConnection db = new DbConnection();
                        db.vt_gelen_kaydet( this.sicaklik,this.nem);
                        this.sira=0;
                    }
                    System.out.println(inputLine);
                  
                } else {
              }
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }

    }

    public Eos_main() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("java.png")));
        PortListele();
    }

   void PortListele(){
    jComboBox_PortListe.removeAllItems();        
    System.out.println("deneme");
       
    Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
             System.out.println("Has more elements");
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            if (currPortId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                 System.out.println(currPortId.getName());
                jComboBox_PortListe.addItem(currPortId.getName());
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_connect = new javax.swing.JButton();
        btn_disconnect = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_readData = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        txt_BoundRate = new javax.swing.JTextField();
        txt_TimeOut = new javax.swing.JTextField();
        jLabel_Port = new javax.swing.JLabel();
        jLabel_BoundRate = new javax.swing.JLabel();
        jLabel_TimeOut = new javax.swing.JLabel();
        txt_ComPortName = new javax.swing.JTextField();
        btn_portListe = new javax.swing.JButton();
        jComboBox_PortListe = new javax.swing.JComboBox<>();
        jLabel_veriler = new javax.swing.JLabel();
        jLabel_durum = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList_veriler = new javax.swing.JList<>();
        btn_sensor = new javax.swing.JButton();
        btn_oner = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Öneri Sistemi");

        btn_connect.setText("Bağlan");
        btn_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_connectActionPerformed(evt);
            }
        });

        btn_disconnect.setText("Bağlantıyı Kes");
        btn_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_disconnectActionPerformed(evt);
            }
        });

        txt_readData.setColumns(20);
        txt_readData.setRows(5);
        jScrollPane2.setViewportView(txt_readData);

        jPanel1.setToolTipText("");

        txt_BoundRate.setText("9600");
        txt_BoundRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_BoundRateActionPerformed(evt);
            }
        });

        txt_TimeOut.setText("2000");
        txt_TimeOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TimeOutActionPerformed(evt);
            }
        });

        jLabel_Port.setText("PORT");

        jLabel_BoundRate.setText("Bound Rate");

        jLabel_TimeOut.setText("Time Out");

        txt_ComPortName.setText("COM5");

        btn_portListe.setText("Port Listele");
        btn_portListe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_portListeActionPerformed(evt);
            }
        });

        jComboBox_PortListe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_PortListe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_PortListeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_BoundRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_Port)
                            .addComponent(txt_ComPortName)))
                    .addComponent(jLabel_TimeOut)
                    .addComponent(txt_BoundRate)
                    .addComponent(txt_TimeOut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_portListe, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(jComboBox_PortListe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel_Port)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ComPortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_portListe))
                .addGap(1, 1, 1)
                .addComponent(jLabel_BoundRate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_BoundRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_PortListe, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_TimeOut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_TimeOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel_veriler.setText("Gelen Veriler");

        jLabel_durum.setText("Durum");

        jList_veriler.setModel(model);
        jScrollPane3.setViewportView(jList_veriler);

        btn_sensor.setText("VT Sensör Verileri Listele");
        btn_sensor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sensorActionPerformed(evt);
            }
        });

        btn_oner.setText("Kıyafet Öner");
        btn_oner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_onerActionPerformed(evt);
            }
        });

        jButton1.setText("Çıkış");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_sensor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_connect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_disconnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_oner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_durum, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel_veriler, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_durum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel_veriler)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_connect, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_disconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btn_sensor)
                        .addGap(18, 18, 18)
                        .addComponent(btn_oner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(26, 26, 26))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_TimeOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TimeOutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TimeOutActionPerformed

    private void btn_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_connectActionPerformed
        setComPortName(txt_ComPortName.getText());
        DATA_RATE = Integer.parseInt(txt_BoundRate.getText());
        TIME_OUT = Integer.parseInt(txt_TimeOut.getText());
        btn_connect.setText("Bağlan");
  
        initialize();
 
        txt_readData.setText(txt_ComPortName.getText()+" Port açıldı\n");
        txt_readData.setText(txt_readData.getText() + "Veri Bekleniyor...\n");
        model.clear();
    }//GEN-LAST:event_btn_connectActionPerformed

    private void btn_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_disconnectActionPerformed
           close();
        txt_readData.setText(txt_readData.getText() + "Kapatıldı\n");
    }//GEN-LAST:event_btn_disconnectActionPerformed

    private void txt_BoundRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_BoundRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_BoundRateActionPerformed

    private void btn_portListeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_portListeActionPerformed
        PortListele();
    }//GEN-LAST:event_btn_portListeActionPerformed

    private void btn_sensorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sensorActionPerformed
       
 
        try {
            new sensor_listele().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Eos_main.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }//GEN-LAST:event_btn_sensorActionPerformed

    private void jComboBox_PortListeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_PortListeActionPerformed
        // TODO add your handling code here:
        txt_ComPortName.setText((String) jComboBox_PortListe.getSelectedItem());
        setComPortName((String) jComboBox_PortListe.getSelectedItem());
    }//GEN-LAST:event_jComboBox_PortListeActionPerformed

    private void btn_onerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_onerActionPerformed
        // TODO add your handling code here:
        try {
            new oneri().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Eos_main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_onerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
    */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Eos_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Eos_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Eos_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Eos_main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Eos_main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_connect;
    private javax.swing.JButton btn_disconnect;
    private javax.swing.JButton btn_oner;
    private javax.swing.JButton btn_portListe;
    private javax.swing.JButton btn_sensor;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox_PortListe;
    private javax.swing.JLabel jLabel_BoundRate;
    private javax.swing.JLabel jLabel_Port;
    private javax.swing.JLabel jLabel_TimeOut;
    private javax.swing.JLabel jLabel_durum;
    private javax.swing.JLabel jLabel_veriler;
    private javax.swing.JList<String> jList_veriler;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txt_BoundRate;
    private javax.swing.JTextField txt_ComPortName;
    private javax.swing.JTextField txt_TimeOut;
    private javax.swing.JTextArea txt_readData;
    // End of variables declaration//GEN-END:variables

}
