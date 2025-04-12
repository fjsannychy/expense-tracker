/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package expensetracker.Frames;


import Models.Transaction;
import Services.HeadService;
import Services.TransactionService;
import expensetracker.AppSettings;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SHAFI
 */
public class DashboardFrame extends javax.swing.JFrame {

    /**
     * Creates new form DashboardFrame
     */
    SimpleDateFormat _formatter;
    private HeadService _headService;
    private Object[][] _data;
    private String[] _columnNames;
    private TransactionService _transactionService;
    private  DefaultTableModel _model;
    private long _editRowId;
    private int _selectedRow;

    
    public DashboardFrame() {
        
        initComponents();
        
        this._headService = new HeadService();
        
        TransactionDatePicker.setDate(new Date());
        
        _formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        loadOptionsInHeadComboBox();
        
        this.EditHeadButton.setVisible(false);
        this.DeleteHeadButton.setVisible(false);
        this.CancelButton.setVisible(false);
        this.DeleteButton.setVisible(false);
        this._columnNames = new String[]{"Id", "Head", "Amount", "Date", "Type" };
        this._transactionService = new TransactionService(); 
       
        this.setBalance();
        
        this.setTableDataModel();
       
        // Add a ListSelectionListener to detect row selection changes
        this.DataTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Check if the selection is not adjusting (for multiple selections)
                if (!e.getValueIsAdjusting()) {
                    _selectedRow = DataTable.getSelectedRow();
                    if (_selectedRow != -1) {
                        // Get the values of the selected row
                        _editRowId = Long.parseLong(DataTable.getValueAt(_selectedRow, 0).toString());
                        String head = DataTable.getValueAt(_selectedRow, 1).toString();
                        String amount = DataTable.getValueAt(_selectedRow, 2).toString();
                        String date = DataTable.getValueAt(_selectedRow, 3).toString();
                        String type = DataTable.getValueAt(_selectedRow, 4).toString();
                        
                        HeadComboBox.setSelectedItem(head);
                        try {
                            TransactionDatePicker.setDate(_formatter.parse(date));
                        } catch (ParseException ex) {
                            Logger.getLogger(DashboardFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        AmountTextField.setText(amount);
                        TypeComboBox.setSelectedItem(type);
                        
                        DeleteButton.setVisible(true);
                        CancelButton.setVisible(true);
                        
                    }
                }
            }
        });
        
    }
    
    
    private void setBalance(){
        
        var balance = this._transactionService.GetBalance();
        
        IncomeLabel.setText("Income: "+balance.Income);
        ExpenseLabel.setText("Expense: "+balance.Expense);
        BalanceLabel.setText("Balance: "+(balance.Income-balance.Expense));
        
    }
    
    
    public void loadOptionsInHeadComboBox(){
        HeadComboBox.removeAllItems();
        HeadComboBox.addItem("");
        for(int i = 0; i< this._headService._heads.size(); i++ ){
            HeadComboBox.addItem(this._headService._heads.get(i).Name);
        }
    }
    
    private void setTableDataModel(){
        
        this._data = new Object[this._transactionService._transactions.size()][];
        
        for(int i = 0; i< this._transactionService._transactions.size(); i++){

           var transaction =  this._transactionService._transactions.get(i);
            
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

        DashboardLabel = new javax.swing.JLabel();
        HeadComboBox = new javax.swing.JComboBox<>();
        HeadLabel = new javax.swing.JLabel();
        AmountLabel = new javax.swing.JLabel();
        AmountTextField = new javax.swing.JTextField();
        TypeComboBox = new javax.swing.JComboBox<>();
        TypeLabel = new javax.swing.JLabel();
        SaveButton = new javax.swing.JButton();
        IncomeLabel = new javax.swing.JLabel();
        ExpenseLabel = new javax.swing.JLabel();
        BalanceLabel = new javax.swing.JLabel();
        LogoutButton = new javax.swing.JButton();
        TransactionScrollPane = new javax.swing.JScrollPane();
        DataTable = new javax.swing.JTable();
        ChangePasswordButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        AddHeadButton = new javax.swing.JButton();
        DeleteHeadButton = new javax.swing.JButton();
        EditHeadButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        TransactionDatePicker = new org.jdesktop.swingx.JXDatePicker();
        DateLabel = new javax.swing.JLabel();
        ViewReportButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        DashboardLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        DashboardLabel.setText("Expense Tracker");

        HeadComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HeadComboBoxActionPerformed(evt);
            }
        });

        HeadLabel.setText("Head:");

        AmountLabel.setText("Amount:");

        AmountTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AmountTextFieldActionPerformed(evt);
            }
        });

        TypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Expense", "Income" }));
        TypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TypeComboBoxActionPerformed(evt);
            }
        });

        TypeLabel.setText("Type:");

        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        IncomeLabel.setText("Income: 0");

        ExpenseLabel.setText("Expense: 0");

        BalanceLabel.setText("Balance: 0");

        LogoutButton.setText("Logout");
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
            }
        });

        DataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        DataTable.setColumnSelectionAllowed(true);
        DataTable.getTableHeader().setReorderingAllowed(false);
        DataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataTableMouseClicked(evt);
            }
        });
        TransactionScrollPane.setViewportView(DataTable);
        DataTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        ChangePasswordButton.setText("Change Password");
        ChangePasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangePasswordButtonActionPerformed(evt);
            }
        });

        DeleteButton.setText("Delete");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        AddHeadButton.setText("Add");
        AddHeadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddHeadButtonActionPerformed(evt);
            }
        });

        DeleteHeadButton.setText("Delete");
        DeleteHeadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteHeadButtonActionPerformed(evt);
            }
        });

        EditHeadButton.setText("Edit");
        EditHeadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditHeadButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        TransactionDatePicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransactionDatePickerActionPerformed(evt);
            }
        });

        DateLabel.setText("Date:");

        ViewReportButton.setText("View Report");
        ViewReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewReportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TransactionScrollPane)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IncomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ChangePasswordButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ViewReportButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(HeadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HeadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(DashboardLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(281, 281, 281)
                        .addComponent(LogoutButton))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AddHeadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EditHeadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DeleteHeadButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(183, 183, 183)
                                .addComponent(ExpenseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(DateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TransactionDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DeleteButton)
                                .addGap(27, 27, 27)
                                .addComponent(CancelButton))
                            .addComponent(BalanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DashboardLabel)
                    .addComponent(LogoutButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ChangePasswordButton)
                        .addComponent(ViewReportButton)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IncomeLabel)
                    .addComponent(ExpenseLabel)
                    .addComponent(BalanceLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HeadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HeadLabel)
                    .addComponent(AddHeadButton)
                    .addComponent(DeleteHeadButton)
                    .addComponent(EditHeadButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AmountLabel)
                    .addComponent(AmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TypeLabel)
                    .addComponent(TypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DateLabel)
                    .addComponent(TransactionDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SaveButton)
                    .addComponent(DeleteButton)
                    .addComponent(CancelButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(TransactionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HeadComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HeadComboBoxActionPerformed
        // TODO add your handling code here:
        
        if(HeadComboBox.getSelectedItem() == null || HeadComboBox.getSelectedItem().toString().length() == 0){
            this.AddHeadButton.setVisible(true);
            this.EditHeadButton.setVisible(false);
            this.DeleteHeadButton.setVisible(false);
        }
        else{
            this.AddHeadButton.setVisible(false);
            this.EditHeadButton.setVisible(true);
            this.DeleteHeadButton.setVisible(true);
        }
    }//GEN-LAST:event_HeadComboBoxActionPerformed

    private void AmountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AmountTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AmountTextFieldActionPerformed

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        // TODO add your handling code here:
        
        var amount = AmountTextField.getText();
        Date date = TransactionDatePicker.getDate();
       
        if(HeadComboBox.getSelectedItem() == null ||
           "".equals(HeadComboBox.getSelectedItem().toString())){
            
            JOptionPane.showMessageDialog(DashboardFrame.this, "Please select a head!");
        }
        else if(date == null){
            
            JOptionPane.showMessageDialog(DashboardFrame.this, "Please provide valid date!");
        }
        else if("".equals(amount)){
            
            JOptionPane.showMessageDialog(DashboardFrame.this, "Please provide valid amount!");
        }
        else{
            
            Transaction transaction = new Transaction(
                HeadComboBox.getSelectedItem().toString(),
                Double.parseDouble(amount),
                _formatter.format(date),
                TypeComboBox.getSelectedItem().toString()
            );
            
            
            if(this._editRowId == 0){
                   this._transactionService.Add(transaction);
                   this._transactionService.SaveChanges();

                   this._model.addRow(
                           new Object[]{ 
                               transaction.Id,
                               transaction.Head,
                               transaction.Amount,
                               transaction.Date,
                               transaction.Type
                            }
                   );
            }
            else{
                   
                   transaction.Id = this._editRowId;
                   this._transactionService.Edit(transaction);
                   this._transactionService.SaveChanges();
                   
                   this.setTableDataModel();
                   
                   this._editRowId = 0;
                   this._selectedRow = -1;
                   this.DeleteButton.setVisible(false);
                   this.CancelButton.setVisible(false);

            }
           
            
           HeadComboBox.setSelectedIndex(0);
           AmountTextField.setText("");
           TypeComboBox.setSelectedIndex(0);
           
           setBalance();
        }
       

    }//GEN-LAST:event_SaveButtonActionPerformed

    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        // TODO add your handling code here:
        AppSettings.loggedUser = null;
        var loginFrame =  new LoginFrame();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_LogoutButtonActionPerformed

    private void ChangePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangePasswordButtonActionPerformed
        // TODO add your handling code here:
        var changePassFrame =  new ChangePassFrame();
        changePassFrame.setLocationRelativeTo(null);
        changePassFrame.setVisible(true);
    }//GEN-LAST:event_ChangePasswordButtonActionPerformed

    private void DataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DataTableMouseClicked

    private void TypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TypeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TypeComboBoxActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        // TODO add your handling code here:
        
        this._transactionService.Delete(this._editRowId);
        this._transactionService.SaveChanges();
        this._model.removeRow(this._selectedRow);
        
        this._editRowId = 0;
        this._selectedRow = -1;
        this.DeleteButton.setVisible(false);
        this.CancelButton.setVisible(false);

        HeadComboBox.setSelectedIndex(0);
        AmountTextField.setText("");
        TypeComboBox.setSelectedIndex(0);
           
        setBalance();
        
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void TransactionDatePickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransactionDatePickerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TransactionDatePickerActionPerformed

    private void AddHeadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddHeadButtonActionPerformed
        // TODO add your handling code here:
            this.setVisible(false);
            var headFrame =  new HeadFrame(null);
            headFrame.setLocationRelativeTo(null);
            headFrame.setVisible(true);
    }//GEN-LAST:event_AddHeadButtonActionPerformed

    private void EditHeadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditHeadButtonActionPerformed
        // TODO add your handling code here:
            this.setVisible(false);
            var currentHead = this._headService.GetHead(HeadComboBox.getSelectedItem().toString());
            var headFrame =  new HeadFrame(currentHead);
            headFrame.setLocationRelativeTo(null);
            headFrame.setVisible(true);
    }//GEN-LAST:event_EditHeadButtonActionPerformed

    private void DeleteHeadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteHeadButtonActionPerformed
        // TODO add your handling code here:
        this._headService.Delete(HeadComboBox.getSelectedItem().toString());
        this._headService.SaveChanges();
        this.loadOptionsInHeadComboBox();
    }//GEN-LAST:event_DeleteHeadButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        // TODO add your handling code here:
        this._editRowId = 0;
        this._selectedRow = -1;
        this.DeleteButton.setVisible(false);
        this.CancelButton.setVisible(false);

        HeadComboBox.setSelectedIndex(0);
        AmountTextField.setText("");
        TypeComboBox.setSelectedIndex(0);
        
        setTableDataModel();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void ViewReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewReportButtonActionPerformed
        // TODO add your handling code here:
        var reportFrame =  new ReportFrame();
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setVisible(true);
    }//GEN-LAST:event_ViewReportButtonActionPerformed

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
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddHeadButton;
    private javax.swing.JLabel AmountLabel;
    private javax.swing.JTextField AmountTextField;
    private javax.swing.JLabel BalanceLabel;
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton ChangePasswordButton;
    private javax.swing.JLabel DashboardLabel;
    private javax.swing.JTable DataTable;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton DeleteHeadButton;
    private javax.swing.JButton EditHeadButton;
    private javax.swing.JLabel ExpenseLabel;
    private javax.swing.JComboBox<String> HeadComboBox;
    private javax.swing.JLabel HeadLabel;
    private javax.swing.JLabel IncomeLabel;
    private javax.swing.JButton LogoutButton;
    private javax.swing.JButton SaveButton;
    private org.jdesktop.swingx.JXDatePicker TransactionDatePicker;
    private javax.swing.JScrollPane TransactionScrollPane;
    private javax.swing.JComboBox<String> TypeComboBox;
    private javax.swing.JLabel TypeLabel;
    private javax.swing.JButton ViewReportButton;
    // End of variables declaration//GEN-END:variables
}
