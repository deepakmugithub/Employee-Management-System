/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package emsg4v1;

import java.awt.HeadlessException;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Walter
 */
public class ApplyLeave1 extends javax.swing.JFrame {

    Connectdb conn = new Connectdb();
    
    //SimpleDateFormat df = new SimpleDateFormat();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    DefaultTableModel model;
    
    public ApplyLeave1() {
        initComponents();
        
        model = (DefaultTableModel) tleave.getModel();
        
        searchEmpId();
        searchType();
        displayTable();
    }
    
 private void searchType()
    {
    try
        {
            ResultSet rs;
            String query = " select id,type from typeleave ";
            rs = conn.stmt.executeQuery(query);
          
          while ( rs.next())
              
          {
              Object eno = rs.getString("type");
              ctype.addItem(eno.toString());
           
          }
        
        }
        catch(Exception e)
        {
            System.out.println("ERROR IN Displaying Type Leaave..."+e);
        
        }             
    
    }
 
  private void searchSpecificType()
    {
    try
        {
            ResultSet rs;
            String query = " select id,type from typeleave where id =( select leaveId from takeleave where id= '"+lid.getText()+"' ) ";
            rs = conn.stmt.executeQuery(query);
          
          if ( rs.next())
              
          {
              //Object eno = rs.getString("type");
              ctype.setSelectedItem(rs.getString("type"));
           
          }
        
        }
        catch(Exception e)
        {
            System.out.println("ERROR IN Displaying Type Leaave..."+e);
        
        }             
    
    }


    private void searchEmpId()
    {
    try
        {
            ResultSet rs;
            String query = " select id from emp ";
            rs = conn.stmt.executeQuery(query);
          
          while ( rs.next())
              
          {
              Object eno = rs.getString("id");
             // cid.addItem(eno.toString());
           
          }
        
        }
        catch(Exception e)
        {
            System.out.println("ERROR IN Displaying EMP ID..."+e);
        
        }             
    
    }
    
    
  /* void seachEmp()
    {
        
        if ( ! cid.getSelectedItem().equals(""))
        {
        try
        {
          ResultSet rs;
          String query = "select fname from emp where id = '"+cid.getSelectedItem().toString()+"' ";
          rs = conn.stmt.executeQuery(query);
          
          if( rs.next())
           {
              tn.setText(rs.getString("fname"));
                
           }
          else
              JOptionPane.showMessageDialog(this,"No Record Found For That EMP No ","WRONG EMP ID ", HEIGHT);
          
        }
        catch(HeadlessException | SQLException e)
        {
            System.out.println("Error In Search EMP ..."+e);
        }            
        }
    }*/
   
void seachEmp_id()
    {
        if (  lid.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this," Enter The Leave ID Please .... ","EMTY LEAVE ID", HEIGHT);
        }
        else{
            
        try
        {
          ResultSet rs;
          String query = "select id,fname from emp where id =( select empId from takeleave where id= '"+lid.getText()+"' ) ";
          rs = conn.stmt.executeQuery(query);
          
          if( rs.next())
           {
              tn.setText(rs.getString("fname"));
              //cid.setSelectedItem(rs.getString("id"));
           }
          else
              JOptionPane.showMessageDialog(this,"No Record Found For That Leave Id ","WRONG EMP ID ", HEIGHT);
          
        }
        catch(HeadlessException | SQLException e)
        {
            System.out.println("Error In Search EMP ..."+e);
        }            
        }
    }
   
 void seachLeave()
    {
        
         if (  lid.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this," Enter The Leave ID Please .... ","EMTY LEAVE NUMBER ", HEIGHT);
        }
        else{
        try
        {
          ResultSet rs;
          String query = "select * from takeleave where id = '"+lid.getText()+"' ";
          rs = conn.stmt.executeQuery(query);
          
          if( rs.next())
           {
              
              sdc.setDate(rs.getDate("sdate"));
              edc.setDate(rs.getDate("edate"));
              trsn.setText(rs.getString("reason"));
              cdecision.setSelectedItem(rs.getString("decision"));
                
           }
          else
              JOptionPane.showMessageDialog(this,"No Record Found For That Leave No ","WRONG LEAVE ID ", HEIGHT);
          
        }
        catch(HeadlessException | SQLException e)
        {
            System.out.println("Error In Search leave ..."+e);
        }            
         }
    }
    
    void updateleave()
    {
        try
        {
        
            PreparedStatement st;
            String sdate = df.format(sdc.getDate());
            String edate = df.format(edc.getDate());
            
            int resp;
            String query = "update takeleave set sdate=?, edate=?, reason=?, decision=?  where id=?";
            
            //resp = conn.stmt.executeUpdate(query);
            st = conn.con.prepareStatement(query);
              st.setString(1, sdate);
              st.setString(2, edate);
              st.setString(3, trsn.getText());
              st.setString(4, cdecision.getSelectedItem().toString());
              st.setString(5, lid.getText());
              
            resp = st.executeUpdate();
            //conn.con.close();
            if(resp==1)
            {
                JOptionPane.showMessageDialog(this,"Leave Approved Successfully... ","LEAVE", HEIGHT); 
            }
        
        }
        catch(SQLException e)
        {
            System.out.println("Error In Leave Updating.... "+e);
        
        }         
        
    }    
    
    void deleteAtt()
    {
        try
        {
        
            PreparedStatement st;
            
            String query = "delete from takeleave  where id=?";
            
              st = conn.con.prepareStatement(query);
             
              st.setString(1, lid.getText());
              
              int resp = st.executeUpdate();
            
            if(resp==1)
            {
                JOptionPane.showMessageDialog(this,"Deletion Done... ","Take Leave", HEIGHT); 
            }       
        }
        catch(SQLException e)
        {
            System.out.println("Error In Take Leave Deletion.... "+e);
        }          
    }
    
    String idtype()
    {
        String idt = null;
        try
        {
          ResultSet rs;
          String query = " select id from typeleave where type='"+ctype.getSelectedItem()+"'  ";
          rs = conn.stmt.executeQuery(query);
          
          if( rs.next())
           {
               idt = rs.getString("id");
                
           }
          else
              JOptionPane.showMessageDialog(this,"ID TYpe Not Found","ID TYPE", HEIGHT);
          
        }
        catch(HeadlessException | SQLException e)
        {
            System.out.println("Error In Search EMP ..."+e);
        }                     
        return idt ;
    
    }

    void insertleave()
    {
        try
        {
        
            PreparedStatement st;
            String sdate = df.format(sdc.getDate());
            String edate = df.format(edc.getDate());
           
            
            int resp;
            String query = "insert into takeleave (sdate,edate,reason,empId,leaveId,decision) values(?,?,?,?,?)";
            
           
            
              st = conn.con.prepareStatement(query);
              st.setString(1, sdate);
              st.setString(2, edate);
              st.setString(3, trsn.getText());
              
              //st.setString(4, (String) cid.getSelectedItem());
              
              st.setString(4, idtype());
              st.setString(5, (String) cdecision.getSelectedItem());
              
            resp = st.executeUpdate();
            //conn.con.close();
            if(resp==1)
            {
                JOptionPane.showMessageDialog(this,"Leave Added Successfully... ","Take Leave", HEIGHT); 
            }
        
        }
        catch(SQLException e)
        {
            System.out.println("Error In Take Leave Insertion.... "+e);
        
        }        
    
    }
    
    private void displayTable()
    {
         try
        {
            ResultSet rs;
            String query = "select * from takeleave  ";
            rs = conn.stmt.executeQuery(query);
          
          while ( rs.next())
          {
              model.insertRow(model.getRowCount(), new Object[] {rs.getString("id"),rs.getString("sdate"),rs.getString("edate"),rs.getString("reason"),rs.getString("decision")});
          
          }
        
        }
        catch(Exception e)
        {
            System.out.println("ERROR IN Displayn Table ATTendence..."+e);
        
        }       
    
    }
    
    void clear()
    {
        lid.setText("");
        tn.setText("");
        sdc.setDate(null);
        edc.setDate(null);
        
        trsn.setText("");
        //cid.setSelectedItem("");
        ctype.setSelectedItem("");
        cdecision.setSelectedItem("");
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jCalendar2 = new com.toedter.calendar.JCalendar();
        jScrollPane2 = new javax.swing.JScrollPane();
        tleave = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tn = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        ctype = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        sdc = new com.toedter.calendar.JDateChooser();
        edc = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        trsn = new javax.swing.JTextArea();
        lid = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cdecision = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(400, 75));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Approve  Leave");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Leave Info"));

        jLabel2.setText("Name");

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Date", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCalendar2, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCalendar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        tleave.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr No.", "Start Date", "End Date", "Reason", "Decision"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tleave);

        jLabel3.setText("Leave No.");

        jLabel4.setText("Type");

        jLabel5.setText("End Date");

        jLabel6.setText("Start Date");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Approve");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel7.setText("Reason");

        ctype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "" }));

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        sdc.setDateFormatString("yyy MM dd");

        edc.setDateFormatString("yyyy MM dd");

        trsn.setColumns(20);
        trsn.setRows(5);
        jScrollPane3.setViewportView(trsn);

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("Search ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel9.setText("Decision");

        cdecision.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Pending", "Accepted", "Rejected" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(ctype, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(tn))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(lid, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(sdc, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(edc, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(129, 129, 129)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cdecision, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(370, 370, 370)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ctype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sdc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(edc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cdecision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int resp = JOptionPane.showConfirmDialog(this, "Do you Want to Cancel ?","CANCEL", JOptionPane.YES_NO_OPTION);
        if (resp==JOptionPane.YES_OPTION)
        {
        clear();    
  
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        this.setVisible(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        seachLeave();
        seachEmp_id();
        searchSpecificType();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        
       int resp = JOptionPane.showConfirmDialog(this, "Do you Want to APPROVE ?","UPDATE", JOptionPane.YES_NO_OPTION);
        if (resp==JOptionPane.YES_OPTION)
        {
        updateleave();

        model.setRowCount(0);
        displayTable();
        
        clear(); 
        }
        

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ApplyLeave1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApplyLeave1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApplyLeave1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApplyLeave1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ApplyLeave1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cdecision;
    private javax.swing.JComboBox<String> ctype;
    private com.toedter.calendar.JDateChooser edc;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JCalendar jCalendar2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField lid;
    private com.toedter.calendar.JDateChooser sdc;
    private javax.swing.JTable tleave;
    private javax.swing.JTextField tn;
    private javax.swing.JTextArea trsn;
    // End of variables declaration//GEN-END:variables

}
