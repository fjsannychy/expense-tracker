/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package expensetracker.Frames;

import Services.HeadService;
import Services.TransactionService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class ReportFrame extends javax.swing.JFrame {

    /**
     * Creates new form ReportFrame
     */
    
    private SimpleDateFormat _formatter;
    private HeadService _headService;
    private Object[][] _data;
    private String[] _columnNames;
    private TransactionService _transactionService;
    private  DefaultTableModel _model;
    
    public ReportFrame() {
        
        initComponents();
           
        this._headService = new HeadService();
        this._transactionService = new TransactionService();
        this._columnNames = new String[]{"Id", "Head", "Amount", "Date", "Type" };
        
        FromDatePicker.setDate(new Date());
        ToDatePicker.setDate(new Date());
        
        _formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        loadOptionsInHeadComboBox();
        
        GetDetailsReport();
    }
        
    public void loadOptionsInHeadComboBox(){
        HeadComboBox.removeAllItems();
        HeadComboBox.addItem("");
        for(int i = 0; i< this._headService._heads.size(); i++ ){
            HeadComboBox.addItem(this._headService._heads.get(i).Name);
        }
    }
    
    public static void exportTableToCSV(JTable table, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Get column count
            int columnCount = table.getColumnCount();

            // Write column names (headers) to CSV
            for (int i = 0; i < columnCount; i++) {
                writer.write(table.getColumnName(i));
                if (i < columnCount - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();

            // Write data rows to CSV
            for (int row = 0; row < table.getRowCount(); row++) {
                for (int col = 0; col < columnCount; col++) {
                    writer.write(table.getValueAt(row, col).toString());
                    if (col < columnCount - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }

            System.out.println("Table exported to CSV successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while exporting the table.");
        }
    }
    
    
    public static void createFolderAndFile(String folderPath, String filePath) {
        // Create the folder if it doesn't exist
        File folder = new File(folderPath);
        if (!folder.exists()) {
            if (folder.mkdirs()) {  // mkdirs() creates the folder and any necessary parent directories
                System.out.println("Folder created: " + folderPath);
            } else {
                System.out.println("Failed to create folder: " + folderPath);
            }
        } else {
            System.out.println("Folder already exists: " + folderPath);
        }

        // Create the file inside the folder if it doesn't exist
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created: " + filePath);
                } else {
                    System.out.println("Failed to create file: " + filePath);
                }
            } else {
                System.out.println("File already exists: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }
    
    
     public void GetDetailsReport(){
        
         
        //set balance
        var report = this._transactionService.GetDetailsReport(
                HeadComboBox.getSelectedItem().toString(), 
                _formatter.format(FromDatePicker.getDate()), 
                _formatter.format(ToDatePicker.getDate()));
        
        IncomeLabel.setText("Income: "+report.balance.Income);
        ExpenseLabel.setText("Expense: "+report.balance.Expense);
        BalanceLabel.setText("Balance: "+(report.balance.Income-report.balance.Expense));
       ///
       
       ///set table model
        this._data = new Object[report.transactions.size()][];
        
        for(int i = 0; i< report.transactions.size(); i++){

           var transaction =  report.transactions.get(i);
            
           this._data[i] = (new Object[]{ 
                       transaction.Id,
                       transaction.Head,
                       transaction.Amount,
                       transaction.Date,
                       transaction.Type
                    });
        }
        
        
        this._model = new DefaultTableModel(this._data, _columnNames){
        
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
            
        };
       
        this.DataTable.setModel(this._model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Center align text
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        // Right align text
        this.DataTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        this.DataTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        this.DataTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        this.DataTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        this.DataTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        
         
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        HeadComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        FromDatePicker = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        ToDatePicker = new org.jdesktop.swingx.JXDatePicker();
        IncomeLabel = new javax.swing.JLabel();
        ExpenseLabel = new javax.swing.JLabel();
        BalanceLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DataTable = new javax.swing.JTable();
        ExportButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Detail Report");

        HeadComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HeadComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Head:");

        jLabel3.setText("From:");

        FromDatePicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FromDatePickerActionPerformed(evt);
            }
        });

        jLabel4.setText("To:");

        ToDatePicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToDatePickerActionPerformed(evt);
            }
        });

        IncomeLabel.setText("Total Income: 0");

        ExpenseLabel.setText("Total Expense: 0");

        BalanceLabel.setText("Balance: 0");

        DataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(DataTable);

        ExportButton.setText("Export To CSV");
        ExportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HeadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FromDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(IncomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ExpenseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ToDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BalanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(ExportButton)))
                .addGap(34, 34, 34))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ExportButton))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(HeadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(FromDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ToDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IncomeLabel)
                    .addComponent(ExpenseLabel)
                    .addComponent(BalanceLabel))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HeadComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HeadComboBoxActionPerformed
        // TODO add your handling code here:
        
        GetDetailsReport();
    }//GEN-LAST:event_HeadComboBoxActionPerformed

    private void FromDatePickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FromDatePickerActionPerformed
        // TODO add your handling code here:
        GetDetailsReport();
    }//GEN-LAST:event_FromDatePickerActionPerformed

    private void ToDatePickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToDatePickerActionPerformed
        // TODO add your handling code here:
        GetDetailsReport();
    }//GEN-LAST:event_ToDatePickerActionPerformed

    private void ExportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportButtonActionPerformed
        // TODO add your handling code here:
        String folderPath = "Exports";
        String filePath = folderPath + File.separator + "report_"+System.currentTimeMillis()+".csv";
        createFolderAndFile(folderPath, filePath);
        exportTableToCSV(DataTable, filePath);
        
        JOptionPane.showMessageDialog(ReportFrame.this, "Exportation Successfull. Exported to "+filePath);
        
    }//GEN-LAST:event_ExportButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReportFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReportFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BalanceLabel;
    private javax.swing.JTable DataTable;
    private javax.swing.JLabel ExpenseLabel;
    private javax.swing.JButton ExportButton;
    private org.jdesktop.swingx.JXDatePicker FromDatePicker;
    private javax.swing.JComboBox<String> HeadComboBox;
    private javax.swing.JLabel IncomeLabel;
    private org.jdesktop.swingx.JXDatePicker ToDatePicker;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
