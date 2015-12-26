/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbmanipulation;

import com.mongodb.MongoClient;
import com.mongodb.client.model.Filters;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import static mongodbmanipulation.Constants.*;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Enamul
 */
public class UserInterface extends javax.swing.JFrame {

    /**
     *
     */
    public void getDatabaseList() {
//        System.out.println("database list updated!");
        databaseNames = mongoClient.listDatabaseNames();

        jComboBoxDBName.removeAllItems();
        jComboBoxCollectionName.removeAllItems();

        for (String listDatabaseName : databaseNames) {
            jComboBoxDBName.addItem(listDatabaseName);
        }

//        db = mongoClient.getDatabase("test");
        jButtonDropCollection.setEnabled(false);
        jButtonAddCollection.setEnabled(false);
        jTextFieldNewCollectionName.setEnabled(false);
        jButtonAddColumn.setEnabled(false);
        jButtonRemoveColumn.setEnabled(false);
        jButtonAddRow.setEnabled(false);
        jButtonRefreshTable.setEnabled(false);
        jButtonRemoveSelected.setEnabled(false);
        jComboBoxCollectionName.setEnabled(false);
        prepareTable(new Object[0], new Object[0][0]);
    }

    public void getCollectionList() {
//        System.out.println("collection list updated!");
        db = mongoClient.getDatabase((String) jComboBoxDBName.getSelectedItem());
        collectionNames = db.listCollectionNames();

        jComboBoxCollectionName.removeAllItems();

        for (String collectionName : collectionNames) {
            jComboBoxCollectionName.addItem(collectionName);
        }
        jComboBoxCollectionName.setEnabled(true);
        jButtonDropCollection.setEnabled(true);
        jButtonAddCollection.setEnabled(true);
        jTextFieldNewCollectionName.setEnabled(true);
        jButtonAddRow.setEnabled(false);
        jButtonAddColumn.setEnabled(false);
        jButtonRemoveColumn.setEnabled(false);
        jButtonRefreshTable.setEnabled(false);
        jButtonRemoveSelected.setEnabled(false);
        prepareTable(new Object[0], new Object[0][0]);
    }
    Action action = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            TableCellListener tcl = (TableCellListener) e.getSource();
//            System.out.println("Row   : " + tcl.getRow());
//            System.out.println("Column: " + tcl.getColumn());
//            System.out.println("Old   : " + tcl.getOldValue());
//            System.out.println("New   : " + tcl.getNewValue());
            System.out.println("Action: "+e.getActionCommand());
            if(columnUpdating) return;
            int row = tcl.getRow();
            int col = tcl.getColumn();
            Document where = new Document("_id",  model.getValueAt(row, _idcol));
            Document what = new Document("$set", new Document((String) columns[col],model.getValueAt(row, col)));
            collections.updateOne(Filters.eq("_id", where.get("_id")), what);
        }
    };

    /**
     * Creates new form UserInterface
     */
    public UserInterface() {
        super("MongoDB CRUD Operation Manager");
        initComponents();

        mongoClient = new MongoClient("localhost", 27017);
        getDatabaseList();

        model = (DefaultTableModel) jTableResultTable.getModel();
        TableCellListener tcl = new TableCellListener(jTableResultTable, action);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        //adjust screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        setLocation((int) Math.max((width - getWidth()) / 2, 0), (int) Math.max((height - getHeight()) / 2, 0));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelMongoDBCRUD = new javax.swing.JLabel();
        jPanelCollectionName = new javax.swing.JPanel();
        jLabelCollectionName = new javax.swing.JLabel();
        jButtonDropCollection = new javax.swing.JButton();
        jTextFieldNewCollectionName = new javax.swing.JTextField();
        jButtonAddCollection = new javax.swing.JButton();
        jLabelNewCollectionName = new javax.swing.JLabel();
        jComboBoxCollectionName = new javax.swing.JComboBox();
        jPanelResultTable = new javax.swing.JPanel();
        jButtonAddRow = new javax.swing.JButton();
        jButtonAddColumn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableResultTable = new javax.swing.JTable();
        jButtonRemoveSelected = new javax.swing.JButton();
        jButtonRefreshTable = new javax.swing.JButton();
        jButtonRemoveColumn = new javax.swing.JButton();
        jPanelCollectionName1 = new javax.swing.JPanel();
        jLabelDBName = new javax.swing.JLabel();
        jButtonSelectDB = new javax.swing.JButton();
        jTextFieldNewDBName = new javax.swing.JTextField();
        jButtonAddDB = new javax.swing.JButton();
        jLabelNewDBName = new javax.swing.JLabel();
        jComboBoxDBName = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemRefresh = new javax.swing.JMenuItem();
        jMenuItemReconnect = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelMongoDBCRUD.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelMongoDBCRUD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMongoDBCRUD.setText("MongoDB CRUD");

        jLabelCollectionName.setText("Collection Name:");

        jButtonDropCollection.setText("Drop Collection");
        jButtonDropCollection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDropCollectionActionPerformed(evt);
            }
        });

        jTextFieldNewCollectionName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNewCollectionNameActionPerformed(evt);
            }
        });

        jButtonAddCollection.setText("Add New Collection");
        jButtonAddCollection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddCollectionActionPerformed(evt);
            }
        });

        jLabelNewCollectionName.setText("New Collection Name:");

        jComboBoxCollectionName.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxCollectionNamePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanelCollectionNameLayout = new javax.swing.GroupLayout(jPanelCollectionName);
        jPanelCollectionName.setLayout(jPanelCollectionNameLayout);
        jPanelCollectionNameLayout.setHorizontalGroup(
            jPanelCollectionNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCollectionNameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCollectionNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCollectionNameLayout.createSequentialGroup()
                        .addComponent(jLabelNewCollectionName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNewCollectionName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAddCollection))
                    .addGroup(jPanelCollectionNameLayout.createSequentialGroup()
                        .addComponent(jLabelCollectionName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxCollectionName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDropCollection)))
                .addContainerGap())
        );
        jPanelCollectionNameLayout.setVerticalGroup(
            jPanelCollectionNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCollectionNameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCollectionNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCollectionName)
                    .addComponent(jButtonDropCollection)
                    .addComponent(jComboBoxCollectionName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCollectionNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNewCollectionName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddCollection)
                    .addComponent(jLabelNewCollectionName))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonAddRow.setText("Add Row");
        jButtonAddRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddRowActionPerformed(evt);
            }
        });

        jButtonAddColumn.setText("Add Column");
        jButtonAddColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddColumnActionPerformed(evt);
            }
        });

        jTableResultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableResultTable.setColumnSelectionAllowed(true);
        jTableResultTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableResultTablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jTableResultTable);

        jButtonRemoveSelected.setText("Remove Selected");
        jButtonRemoveSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveSelectedActionPerformed(evt);
            }
        });

        jButtonRefreshTable.setText("Refresh Table");
        jButtonRefreshTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshTableActionPerformed(evt);
            }
        });

        jButtonRemoveColumn.setText("Remove Column");
        jButtonRemoveColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveColumnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelResultTableLayout = new javax.swing.GroupLayout(jPanelResultTable);
        jPanelResultTable.setLayout(jPanelResultTableLayout);
        jPanelResultTableLayout.setHorizontalGroup(
            jPanelResultTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelResultTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelResultTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelResultTableLayout.createSequentialGroup()
                        .addComponent(jButtonRemoveSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRefreshTable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAddRow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAddColumn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonRemoveColumn)))
                .addContainerGap())
        );
        jPanelResultTableLayout.setVerticalGroup(
            jPanelResultTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultTableLayout.createSequentialGroup()
                .addGroup(jPanelResultTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddRow)
                    .addComponent(jButtonAddColumn)
                    .addComponent(jButtonRemoveSelected)
                    .addComponent(jButtonRefreshTable)
                    .addComponent(jButtonRemoveColumn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jLabelDBName.setText("DB Name:");

        jButtonSelectDB.setText("Drop DB");
        jButtonSelectDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectDBActionPerformed(evt);
            }
        });

        jTextFieldNewDBName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNewDBNameActionPerformed(evt);
            }
        });

        jButtonAddDB.setText("Add New DB");
        jButtonAddDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddDBActionPerformed(evt);
            }
        });

        jLabelNewDBName.setText("New DB Name:");

        jComboBoxDBName.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxDBNamePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanelCollectionName1Layout = new javax.swing.GroupLayout(jPanelCollectionName1);
        jPanelCollectionName1.setLayout(jPanelCollectionName1Layout);
        jPanelCollectionName1Layout.setHorizontalGroup(
            jPanelCollectionName1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCollectionName1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCollectionName1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCollectionName1Layout.createSequentialGroup()
                        .addComponent(jLabelNewDBName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNewDBName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAddDB))
                    .addGroup(jPanelCollectionName1Layout.createSequentialGroup()
                        .addComponent(jLabelDBName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxDBName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSelectDB)))
                .addContainerGap())
        );
        jPanelCollectionName1Layout.setVerticalGroup(
            jPanelCollectionName1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCollectionName1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCollectionName1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDBName)
                    .addComponent(jButtonSelectDB)
                    .addComponent(jComboBoxDBName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCollectionName1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNewDBName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddDB)
                    .addComponent(jLabelNewDBName))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuFile.setText("File");

        jMenuItemRefresh.setText("Refresh");
        jMenuItemRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRefreshActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemRefresh);

        jMenuItemReconnect.setText("Reconnect");
        jMenuItemReconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReconnectActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemReconnect);

        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemExit);

        jMenuBar1.add(jMenuFile);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMongoDBCRUD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelCollectionName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelResultTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelCollectionName1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMongoDBCRUD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelCollectionName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelCollectionName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelResultTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSelectDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelectDBActionPerformed
        // TODO add your handling code here:
        try {
            mongoClient.getDatabase((String) jComboBoxDBName.getSelectedItem()).drop();
        } catch (Exception e) {
        }
        getDatabaseList();
    }//GEN-LAST:event_jButtonSelectDBActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jButtonAddDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddDBActionPerformed
        // TODO add your handling code here:
        db = mongoClient.getDatabase(jTextFieldNewDBName.getText());
        jComboBoxDBName.addItem(jTextFieldNewDBName.getText());
        jComboBoxDBName.setSelectedItem(jTextFieldNewDBName.getText());

        jTextFieldNewDBName.setText("");
    }//GEN-LAST:event_jButtonAddDBActionPerformed

    private void jButtonAddCollectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddCollectionActionPerformed
        // TODO add your handling code here:
        db.createCollection(jTextFieldNewCollectionName.getText());
        getCollectionList();
        jTextFieldNewCollectionName.setText("");
    }//GEN-LAST:event_jButtonAddCollectionActionPerformed

    private void jButtonDropCollectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDropCollectionActionPerformed
        // TODO add your handling code here:
        try {
            db.getCollection((String) jComboBoxCollectionName.getSelectedItem()).drop();
        } catch (Exception e) {
        }
        getCollectionList();
    }//GEN-LAST:event_jButtonDropCollectionActionPerformed

    public boolean refreshTable() {
        collections = db.getCollection((String) jComboBoxCollectionName.getSelectedItem());
        if(collections.count()>1000){
            int ret = JOptionPane.showConfirmDialog(this, "The table contains more than thousand row.\nThis may slow down the process and could cause Memory error.Are you sure to continue?","Too Large Collection ("+collections.count()+" Rows)",YES_NO_OPTION, INFORMATION_MESSAGE);
            if(ret!=YES_OPTION) return true;
        }
        documents = collections.find().into(new ArrayList<Document>());

        Set<String> colNames = new HashSet<>();

        for (Document doc : documents) {
            for (String key : doc.keySet()) {
                colNames.add(key);
            }
        }

        columns = colNames.toArray();
        Object[][] elements = new Object[documents.size()][columns.length];
        int docNo = 0;

        for (int i = 0; i < columns.length; i++) {
            if (((String) columns[i]).equalsIgnoreCase("_id")) {
                _idcol = i;
                break;
            }
        }

        for (Document doc : documents) {
            for (int i = 0; i < columns.length; i++) {
                if (doc.containsKey(columns[i])) {
                    elements[docNo][i] = doc.get(columns[i]);
                }
            }
            docNo++;
        }
        model.setRowCount(0);
        prepareTable(columns, elements);
        return false;
    }

    private void jComboBoxDBNamePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxDBNamePopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        getCollectionList();
    }//GEN-LAST:event_jComboBoxDBNamePopupMenuWillBecomeInvisible

    private void jComboBoxCollectionNamePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxCollectionNamePopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        if(refreshTable()) return;
        jButtonAddRow.setEnabled(true);
        jButtonAddColumn.setEnabled(true);
        jButtonRemoveColumn.setEnabled(true);
        jButtonRefreshTable.setEnabled(true);
        jButtonRemoveSelected.setEnabled(true);
    }//GEN-LAST:event_jComboBoxCollectionNamePopupMenuWillBecomeInvisible

    private void jMenuItemRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRefreshActionPerformed
        // TODO add your handling code here:
        getDatabaseList();
    }//GEN-LAST:event_jMenuItemRefreshActionPerformed

    private void jTextFieldNewDBNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNewDBNameActionPerformed
        // TODO add your handling code here:
        jButtonAddDBActionPerformed(evt);
    }//GEN-LAST:event_jTextFieldNewDBNameActionPerformed

    private void jTextFieldNewCollectionNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNewCollectionNameActionPerformed
        // TODO add your handling code here:
        jButtonAddCollectionActionPerformed(evt);
    }//GEN-LAST:event_jTextFieldNewCollectionNameActionPerformed

    private void jButtonRefreshTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshTableActionPerformed
        // TODO add your handling code here:
        refreshTable();
    }//GEN-LAST:event_jButtonRefreshTableActionPerformed

    private void jButtonAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddRowActionPerformed
        // TODO add your handling code here:
        model.setRowCount(model.getRowCount() + 1);
        Document toBeInserted = new Document();
        collections.insertOne(toBeInserted);
        if(model.getColumnCount()==0){
            refreshTable();
            return;
        }
        model.setValueAt(toBeInserted.get("_id"), model.getRowCount() - 1, _idcol);
    }//GEN-LAST:event_jButtonAddRowActionPerformed

    private void jTableResultTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableResultTablePropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_jTableResultTablePropertyChange

    private void jButtonRemoveSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveSelectedActionPerformed
        // TODO add your handling code here:
        
        int indexes[] = jTableResultTable.getSelectedRows();
        for (int row : indexes) {
            Document where = new Document("_id",  model.getValueAt(row, _idcol));
            collections.deleteOne(where);
        }
        refreshTable();
    }//GEN-LAST:event_jButtonRemoveSelectedActionPerformed

    private void jButtonAddColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddColumnActionPerformed
        // TODO add your handling code here:
        String colName = JOptionPane.showInputDialog(this, "Enter New Column Name:", "New Column Info",QUESTION_MESSAGE);
        if(colName==null) return;
        String idName = JOptionPane.showInputDialog(this, "Enter _id of the document in which you want to add this column:", "New Column Info",QUESTION_MESSAGE);
        if(colName==null) return;
        String value = JOptionPane.showInputDialog(this, "Enter the value of _id = "+idName+":", "New Column Info",QUESTION_MESSAGE);
        if(colName==null) return;
        columnUpdating=true;
        Document where = new Document("_id",  new ObjectId(idName));
        Document what = new Document("$set", new Document((String) colName,value));
        collections.updateOne(Filters.eq("_id", where.get("_id")), what);
        refreshTable();
        columnUpdating = false;
    }//GEN-LAST:event_jButtonAddColumnActionPerformed

    private void jButtonRemoveColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveColumnActionPerformed
        // TODO add your handling code here:
        int indexes[] = jTableResultTable.getSelectedColumns();
        for (int col : indexes) {
            if(((String) columns[col]).equalsIgnoreCase("_id")){
                JOptionPane.showMessageDialog(this, "_id could not be removed!", "Error in Column Deletion", ERROR_MESSAGE);
                refreshTable();
                columnUpdating = false;
                return;
            }
            columnUpdating = true;
            Document where = new Document((String) columns[col], new Document("$exists", true));
            Document what = new Document("$unset", new Document((String) columns[col],1));
            collections.updateMany(where,what);
        }
        refreshTable();
        columnUpdating = false;
    }//GEN-LAST:event_jButtonRemoveColumnActionPerformed

    private void jMenuItemReconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReconnectActionPerformed
        // TODO add your handling code here:
        mongoClient = new MongoClient("localhost", 27017);
        getDatabaseList();
    }//GEN-LAST:event_jMenuItemReconnectActionPerformed
    private void prepareTable(Object[] columnNames, Object[][] data) {
        jTableResultTable.setModel(new javax.swing.table.DefaultTableModel(
                data,
                columnNames
        ) {
//            Class[] types = new Class [] {
//                java.lang.String.class, java.lang.Boolean.class
//            };

//            public Class getColumnClass(int columnIndex) {
//                return types [columnIndex];
//            }
        });
        model = (DefaultTableModel) jTableResultTable.getModel();
    }

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
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddCollection;
    private javax.swing.JButton jButtonAddColumn;
    private javax.swing.JButton jButtonAddDB;
    private javax.swing.JButton jButtonAddRow;
    private javax.swing.JButton jButtonDropCollection;
    private javax.swing.JButton jButtonRefreshTable;
    private javax.swing.JButton jButtonRemoveColumn;
    private javax.swing.JButton jButtonRemoveSelected;
    private javax.swing.JButton jButtonSelectDB;
    private javax.swing.JComboBox jComboBoxCollectionName;
    private javax.swing.JComboBox jComboBoxDBName;
    private javax.swing.JLabel jLabelCollectionName;
    private javax.swing.JLabel jLabelDBName;
    private javax.swing.JLabel jLabelMongoDBCRUD;
    private javax.swing.JLabel jLabelNewCollectionName;
    private javax.swing.JLabel jLabelNewDBName;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemReconnect;
    private javax.swing.JMenuItem jMenuItemRefresh;
    private javax.swing.JPanel jPanelCollectionName;
    private javax.swing.JPanel jPanelCollectionName1;
    private javax.swing.JPanel jPanelResultTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableResultTable;
    private javax.swing.JTextField jTextFieldNewCollectionName;
    private javax.swing.JTextField jTextFieldNewDBName;
    // End of variables declaration//GEN-END:variables
}
