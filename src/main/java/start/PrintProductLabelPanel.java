package start;


import extenders.MyIntFilter;
import net.miginfocom.swing.MigLayout;
import testing.DefaultPrinter;
import testing.SetDefaultPrinter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.regex.PatternSyntaxException;

import static java.awt.Font.BOLD;
import static java.awt.Font.ITALIC;

public class PrintProductLabelPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private String[] columnToolTips = {null, null, "Description must be seperated by commas NOT periods", "Does the label need to have \"Media Technologies\" or \"Media Contract\"", null};
    private String so = null;
    private String modelNo = null;
    private JTextField salesOrderTxt = new JTextField();
    private JTextField modelTxt = new JTextField();
    private JComboBox<String> modelCombo = new JComboBox<>();
    private String[] quantity = {
            "01",
            "02",
            "03",
            "04",
            "05",
            "06",
            "07",
            "08",
            "09",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "19",
            "20",
            "21"
    };
    private JComboBox<String> quantityCombo = new JComboBox<>(quantity);


    PrintProductLabelPanel() {

        salesOrderTxt.setFont(new Font("Verdana", BOLD, 10));
        salesOrderTxt.setForeground(Color.BLUE);
        salesOrderTxt.setToolTipText("Must be in SAP");

        modelTxt.setFont(new Font("Verdana", ITALIC, 10));
        JButton filterBtn = new JButton("Filter Table");
        filterBtn.setFont(new Font("Verdana", BOLD, 10)); // NOI18N
        filterBtn.setMaximumSize(new Dimension(150, 40));

        JLabel salesOrderLabel = new JLabel("Enter Sales Order Number:", SwingConstants.RIGHT);
        salesOrderLabel.setFont(new Font("Verdana", BOLD, 10)); // NOI18N
        JLabel salesOrderModelLabel = new JLabel("Enter Model Number:", SwingConstants.RIGHT);
        salesOrderModelLabel.setFont(new Font("Verdana", BOLD, 10)); // NOI18N

        JLabel modelLabel = new JLabel("Select Model:", SwingConstants.RIGHT);
        modelLabel.setFont(new Font("Verdana", BOLD, 10)); // NOI18N
        JLabel qtyLabel = new JLabel("QTY to Print:", SwingConstants.RIGHT);
        qtyLabel.setFont(new Font("Verdana", BOLD, 10)); // NOI18N




        JButton printLabelBtn = new JButton("Print Label(s)");
        printLabelBtn.setFont(new Font("Verdana", BOLD, 10)); // NOI18N
        
        JButton selectPrinterBtn = new JButton("Select Printer");
        printLabelBtn.setFont(new Font("Verdana", BOLD, 10)); // NOI18N

        printLabelBtn.setMaximumSize(new Dimension(200, 40));


        // layout, column, row (debug layout);

        List<SalesOrder> list = ProductDao.view();
        int size = list.size();
        String[][] rows = new String[size][7];
        int row = 0;
        for (SalesOrder s : list) {
            rows[row][0] = String.valueOf(s.getSalesOrder());
            String code = String.valueOf(s.getCode());
            String qty = String.valueOf(s.getQuantity());
            rows[row][1] = s.getModel();
            rows[row][2] = s.getDescription();
            if ("112".equals(code)) {
                rows[row][3] = "Contract";
            } else {
                rows[row][3] = "Media Tech";
            }
            int qty1 = Integer.parseInt(qty);
            int i = 0;
            if (qty1 < 10) {
                i = 1;
            }

            if (i == 1) {
                rows[row][4] = "0" + s.getQuantity();
            } else {
                rows[row][4] = String.valueOf(s.getQuantity());
            }
            rows[row][5] = s.getDate();
            rows[row][6] = s.getCustRef();
            row++;

        }
        String[] columns = {"Sales Order", "Model", "Description", "Contract Code", "Quantity", "MFG Date", "Cust Ref"};

        new JTable(rows, columns) {


            //Implement table header tool tips.
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            protected JTableHeader createDefaultTableHeader() {
                return new JTableHeader(columnModel) {
                    /**
                     *
                     */
                    private static final long serialVersionUID = 1L;

                    public String getToolTipText(MouseEvent e) {
                        Point p = e.getPoint();
                        int index = columnModel.getColumnIndexAtX(p.x);
                        int realIndex =
                                columnModel.getColumn(index).getModelIndex();
                        return columnToolTips[realIndex];
                    }
                };
            }
        };

        TableModel model = new DefaultTableModel(rows, columns) {
            /**
             *
             */
            private static final long serialVersionUID = 1L;


        };

        final JTable jt = new JTable(model);
        //     jt.setPreferredScrollableViewportSize(jt.getPreferredSize());
        jt.getColumnModel().getColumn(0).setPreferredWidth(30);
        jt.getColumnModel().getColumn(1).setPreferredWidth(150);
        jt.getColumnModel().getColumn(2).setPreferredWidth(600);
        jt.getColumnModel().getColumn(3).setPreferredWidth(40);
        jt.getColumnModel().getColumn(4).setPreferredWidth(40);
        jt.getColumnModel().getColumn(5).setPreferredWidth(40);
        jt.getColumnModel().getColumn(6).setPreferredWidth(40);
        jt.setFont(new Font("Verdana", ITALIC, 12));

        jt.setDragEnabled(true);
        jt.setAutoCreateRowSorter(true);
        jt.setAutoscrolls(true);
        jt.setShowVerticalLines(true);

        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        jt.setRowSorter(sorter);


        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int column = 0;
                int column2 = 1;
                int row2 = jt.getSelectedRow();
                int row3 = jt.convertRowIndexToModel(row2);
                so = jt.getModel().getValueAt(row3, column).toString();
                modelNo = jt.getModel().getValueAt(row3, column2).toString();

                jTable1MouseClicked();
            }
        });

        filterBtn.addActionListener(e -> {
            String salesOrderNbr = salesOrderTxt.getText();
            if (salesOrderNbr.length() == 1) {
                sorter.setRowFilter(null);
            } else {
                try {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + salesOrderNbr));
                    populateModelDropDown(salesOrderNbr);
                } catch (PatternSyntaxException pse) {
                    System.err.println("Bad regex pattern");
                }
            }
        });

        JScrollPane sp = new JScrollPane(jt);
        sp.setViewportView(jt);

        modelCombo.addItemListener(e -> {
            if ((e.getStateChange() == ItemEvent.SELECTED)) {
                String str = Objects.requireNonNull(modelCombo.getSelectedItem()).toString();
                //   salesOrderModelLabel.setText("Model = " + str);
            }
        });



        MigLayout layout = new MigLayout("fillx", "[grow,fill]");
        MigLayout layout2 = new MigLayout("fillx", "[grow,fill]");
        //   jt.setPreferredScrollableViewportSize(jt.getPreferredSize());
        jt.setFillsViewportHeight(true);
        JPanel top = new JPanel(layout2);
        this.setLayout(layout);

        top.add(salesOrderLabel);
        top.add(salesOrderTxt);
        top.add(filterBtn);


        // top.add(modelLabel);
        top.add(modelCombo);
        top.add(salesOrderModelLabel);
        // top.add(modelTxt);
        top.add(qtyLabel);
        top.add(quantityCombo);
        top.add(printLabelBtn);
        top.add(selectPrinterBtn);
        JPanel bottom = new JPanel(new GridLayout());
        bottom.add(sp);
        this.add(top, " span, wrap");
        this.add(bottom, " span, wrap");

        PlainDocument doc = (PlainDocument) salesOrderTxt.getDocument();
        doc.setDocumentFilter(new MyIntFilter());

        printLabelsListener(printLabelBtn);
        selectPrinterListener(selectPrinterBtn);
    }


    private void printLabelsListener(JButton button) {
    	
        button.addActionListener(arg0 -> {

            String sO = salesOrderTxt.getText().trim();
            String model = Objects.requireNonNull(modelCombo.getSelectedItem()).toString();
            String quantity = Objects.requireNonNull(quantityCombo.getSelectedItem()).toString().trim();
            System.out.println(sO + " " + model + " " + quantity);

            LabelsForProductDB labelsForProductDB = new LabelsForProductDB();
            labelsForProductDB.printLabels(sO, model, quantity);
        });
    }
    
    private void selectPrinterListener(JButton button) {
    	
        button.addActionListener(arg0 -> {
        	SetDefaultPrinter defaultprinter = new SetDefaultPrinter();
        	String herItIs = defaultprinter.getDefaultPrinter();
        	DefaultPrinter printer = new DefaultPrinter();
        	printer.setPrinter(herItIs);
        	System.out.println("The printer is set " + herItIs);



        });
    }

    private void jTable1MouseClicked() {
        salesOrderTxt.setText(so);
        modelTxt.setText(modelNo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }

    private void populateModelDropDown(String salesOrderNbr) {
        String sqlQuery = "SELECT T0.[U_SSI_ModPart] AS 'Model' FROM   [dbo].[RDR1] T0   INNER JOIN   [dbo].[ORDR] T1 ON   T0.[DocEntry] = T1.[DocEntry]  where T1.[DocNum] = " + salesOrderNbr + "Order by T1.[DocNum]" +
                "                    ";
        try {
            Connection con = DriverManager.getConnection(Settings.DBCONNECTION);
                PreparedStatement ps = con.prepareStatement(sqlQuery);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String model2 = rs.getString("Model");
                    modelCombo.addItem(model2);
                }
            } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}