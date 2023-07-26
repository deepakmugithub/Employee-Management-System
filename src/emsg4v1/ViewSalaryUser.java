/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package emsg4v1;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import static java.awt.image.ImageObserver.HEIGHT;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Walter && Koang Biel
 */
public class ViewSalaryUser extends javax.swing.JFrame {

    Connectdb conn = new Connectdb();
    DefaultTableModel model;
    String myid="";
    //public String nm;
    
    public ViewSalaryUser() {
        initComponents();
        
        model = (DefaultTableModel) tableSal.getModel();
        displayTable();
        
    }
    
        public ViewSalaryUser(String myid) {
        initComponents();
        
        this.myid= myid;
        searchEmp();
        
        model = (DefaultTableModel) tableSal.getModel();
        displayTable();
        
    }
    
    void slip()
    {
        String id= tid.getText();
        
        float bs= Float.parseFloat(tbs.getText());       
        float hra=bs/2;
        float bonus= (bs*20)/100 ;
        
        float inTax= (bs*4)/100 ;//PF
        float insur= (bs*5)/100 ;
        float ta= bs/10 ;
        
        float gs= bs+hra+ta+bonus;
        float netsal= gs-inTax-insur;
        
        String name= tn.getText();
        String cont= tc.getText();
        String mnth="";
        String yr="";
        String idemp="";
        
        
        try
        {
            ResultSet rs;
            String query = "select month, year, empId from salary where id = '"+id+"' ";
            rs = conn.stmt.executeQuery(query);
          
          if ( rs.next())
          {
              mnth = rs.getString("month");
              yr = rs.getString("year");
              idemp = rs.getString("empId");
          }
        
        }
        catch(Exception e)
        {
            System.out.println("ERROR IN Displayn TableSal..."+e);
        
        }            
       
        try
        {
            String fl="slip_"+idemp+mnth+yr;
            String fnm="/Users/KoangBiel/Documents/slip/"+fl+".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fnm));
            
            document.open();
            
             Paragraph ptitle= new Paragraph("\t\t******** PAY SLIP ********");
             
             Paragraph pid= new Paragraph("\t EMPLOYEE ID :"+idemp);
             Paragraph pc= new Paragraph("\t CONTACT:"+cont);
             Paragraph pnm= new Paragraph("\t Name :"+name);
             Paragraph pspace= new Paragraph(" ");
             Paragraph pline= new Paragraph("\t**************************************** ");
             
             Paragraph phra= new Paragraph("\t HRA:"+hra);
             Paragraph pta= new Paragraph("\t D A:"+ta);
             Paragraph pmid= new Paragraph("\t BONUS:"+bonus);
             Paragraph ppf= new Paragraph("\t INCOME TAXE:"+inTax);
             Paragraph pbs= new Paragraph("\t BS:"+bs);
             
             Paragraph pgs= new Paragraph("\t GROSS SALARY:"+gs);
             Paragraph pns= new Paragraph("\t NET SALARY :"+netsal);
             Paragraph pm_y= new Paragraph("\t MONTH :"+mnth +" "+yr);
             
             document.add(ptitle);
             document.add(pspace);
             document.add(pid);
             document.add(pnm);
             document.add(pc);
             document.add(pspace);
             document.add(pline);
             document.add(pspace);
             document.add(phra);
             document.add(pta);
             document.add(pmid);
             document.add(ppf);
             document.add(pbs);
             document.add(pline);
             document.add(pspace);
             document.add(pgs);
             document.add(pns);
             document.add(pspace);
             document.add(pm_y);
             document.add(pspace);
             document.add(pline);
             
             System.out.println("Slip Generated ...");
            
            document.close();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
            
            
    
    void calcSalary()
    {
        
        float bs= Float.parseFloat(tbs.getText());
        
        float hra=bs/2;
        float bonus= (bs*20)/100 ;
        
        float inTax= (bs*4)/100 ;//PF
        float insur= (bs*5)/100 ;
        float ta= bs/10 ;
        
        float gs= bs+hra+ta+bonus;
        float netsal= gs-inTax-insur;
        
        ttot.setText(""+netsal);
       
        thra.setText(""+hra);
        tta.setText(""+ta);
        
        tbonus.setText(""+bonus);
        tpf.setText(""+inTax);
        
        
    }
    
    private void  displayTable()
    {
        
        
        try
        {
            ResultSet rs;
            String query = "select * from salary where empId='"+myid+"'  ";
            //String query2 = "select id,fname,contact from emp where id = (select empId from salary where id= '"+tid.getText()+"') ";
            rs = conn.stmt.executeQuery(query);
            //rs2 = conn.stmt.executeQuery(query2);
          
          while ( rs.next())
          {
              model.insertRow(model.getRowCount(), new Object[] {rs.getString("id"),rs.getString("bsal"),rs.getString("hra"),rs.getString("dra"),
                  rs.getString("mid"),rs.getString("pf"),rs.getString("month"),rs.getString("year")});
              
              //model.insertRow(model.getRowCount(), new Object[] {rs.getString("id"),rs2.getString("fname"),rs.getString("bsal"),rs.getString("hra"),rs.getString("dra"),
               //   rs.getString("mid"),rs.getString("pf"),rs.getString("month"),rs.getString("year")});
          
          }
        
        }
        catch(Exception e)
        {
            System.out.println("ERROR IN Displayn TableSal..."+e);
        
        }    
        
    }
    
void dispTableUser() 
{
        
        try
        {
            ResultSet rs;
            String query = "select * from salary where empId= (select id from emp where fname= '"+tn.getText()+"') and year='"+tyear.getText()+"' ";
            //String query2 = "select id,fname,contact from emp where id = (select empId from salary where id= '"+tid.getText()+"') ";
            rs = conn.stmt.executeQuery(query);
            //rs2 = conn.stmt.executeQuery(query2);
          
          while ( rs.next())
          {
              model.insertRow(model.getRowCount(), new Object[] {rs.getString("id"),rs.getString("bsal"),rs.getString("hra"),rs.getString("dra"),
                  rs.getString("mid"),rs.getString("pf"),rs.getString("month"),rs.getString("year")});
              
              //model.insertRow(model.getRowCount(), new Object[] {rs.getString("id"),rs2.getString("fname"),rs.getString("bsal"),rs.getString("hra"),rs.getString("dra"),
               //   rs.getString("mid"),rs.getString("pf"),rs.getString("month"),rs.getString("year")});
          
          }
        
        }
        catch(Exception e)
        {
            System.out.println("ERROR IN Displayn TableSal..."+e);
        
        }    
        
    }
    
    private void searchEmp()
    {
        try
        {
        ResultSet rs;
        //String query = "select id,fname,contact from emp where id = (select empId from salary where id= '"+tid.getText()+"') ";
        String query = "select id,fname,contact from emp where id = '"+myid+"' ";
          
        rs = conn.stmt.executeQuery(query);

          
          if( rs.next() )
           {
              
                //tid.setText();
               //nm=rs.getString("fname");
               
               tn.setText(rs.getString("fname"));
               tc.setText(rs.getString("contact"));
           }   
        
        }
        catch(SQLException e)
        {
             System.out.println("Error In View Salary..."+e);
        
        }
    
    
    }

    
    void search()
    {
       
        if( ! tid.getText().isEmpty())
        {
        
        
        try
        {
          ResultSet rs;
           String query = "select * from salary where  id = '"+tid.getText()+"' and empId = '"+myid+"' ";
          //String query = "select id,fname,contact from emp where id = (select empId from salary where id= '"+tid.getText()+"') ";
          
          
          rs = conn.stmt.executeQuery(query);
          
          
          if( rs.next() )
           {
              
                //tid.setText();
               //tn.setText(rs2.getString("fname"));
               //tc.setText(rs2.getString("contact"));
                tbs.setText(rs.getString("bsal"));
                thra.setText(rs.getString("hra"));
        
                tta.setText(rs.getString("dra"));
                tbonus.setText(rs.getString("mid"));
                tpf.setText(rs.getString("pf"));
                
                searchEmp();
        
           }
          else
              JOptionPane.showMessageDialog(this,"No Record Found For That Salary Number... ","WRONG Salary Number", HEIGHT);
          
        }
        catch(Exception e)
        {
            System.out.println("Error In View Salary..."+e);
        } 
        
        }
    
    }
    
    void clear()
    {
        
        tid.setText("");
        tn.setText("");
        tc.setText("");
        
        tbs.setText("");
        thra.setText("");
        tta.setText("");
        
        tbonus.setText("");
        tpf.setText("");
        ttot.setText("");
        tyear.setText("");
            
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSal = new javax.swing.JTable();
        cbdisp = new javax.swing.JComboBox<>();
        tyear = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tid = new javax.swing.JTextField();
        tc = new javax.swing.JTextField();
        tbonus = new javax.swing.JTextField();
        thra = new javax.swing.JTextField();
        tbs = new javax.swing.JTextField();
        tpf = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        tn = new javax.swing.JTextField();
        tta = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        ttot = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(300, 200));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        tableSal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sal No", "Basic Sal", "Hra", "T A", "Bonus", "Income Tax", "Month", "Year"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableSal);

        cbdisp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Year", "All" }));
        cbdisp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbdispActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tyear, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(cbdisp, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbdisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Sal No.");

        jLabel3.setText("Name");

        jLabel4.setText("Basic Salary");

        jLabel5.setText("Contact");

        jLabel6.setText("HRA");

        jLabel7.setText("T A ");

        jLabel8.setText("BONUS");

        jLabel9.setText("Tot.");

        tc.setEditable(false);

        tbonus.setEditable(false);

        thra.setEditable(false);

        tbs.setEditable(false);

        tpf.setEditable(false);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tn.setEditable(false);

        tta.setEditable(false);

        jLabel10.setText("PF");

        ttot.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(tid, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(tc, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tn, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(thra, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tta, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(ttot))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tpf, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tbs, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tbonus, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 41, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(thra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ttot, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("My Salary");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Generate Slip ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Clear ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Back");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton1)
                        .addGap(74, 74, 74)
                        .addComponent(jButton4)
                        .addGap(71, 71, 71)
                        .addComponent(jButton7))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        search();
        calcSalary();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        
         clear();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        this.setVisible(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if( ! tid.getText().isEmpty())
        {
            slip();
            JOptionPane.showMessageDialog(this,"SLIP ","SLIP GENERATED ... GO AND CHECK...", HEIGHT);
        }
        else
            JOptionPane.showMessageDialog(this,"EMPTY Salary Id","Enter Salary Id First...", HEIGHT);
        

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbdispActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbdispActionPerformed

        if(tid.getText().isEmpty() && tyear.getText().isEmpty())
        {
            
        }
        else if(cbdisp.getSelectedItem().equals("Year"))
        {
            model.setRowCount(0);
            dispTableUser();
        }
        else
        {
            model.setRowCount(0);
            displayTable();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_cbdispActionPerformed

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
            java.util.logging.Logger.getLogger(ViewSalaryUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewSalaryUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewSalaryUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewSalaryUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ViewSalaryUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbdisp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableSal;
    private javax.swing.JTextField tbonus;
    private javax.swing.JTextField tbs;
    private javax.swing.JTextField tc;
    private javax.swing.JTextField thra;
    private javax.swing.JTextField tid;
    private javax.swing.JTextField tn;
    private javax.swing.JTextField tpf;
    private javax.swing.JTextField tta;
    private javax.swing.JTextField ttot;
    private javax.swing.JTextField tyear;
    // End of variables declaration//GEN-END:variables
}
