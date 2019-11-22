package start;


import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import static java.awt.Font.BOLD;
import static java.awt.Font.ITALIC;

public class PrintShippingLabelPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    JPanel bottom = new JPanel(new GridLayout());
    private String[] columnToolTips = {null, null, "Description must be seperated by commas NOT periods", "Does the label need to have \"Media Technologies\" or \"Media Contract\"", null};
    private String so = null;
    private String part = null;
    private JTextField printingLabelsTxt = new JTextField();
    private JTextField printingLabelTxt = new JTextField();
    private JCheckBox checkBox = new JCheckBox("Print One Label Only");
    private JButton filterBtn = new JButton("Filter Table");

    public PrintShippingLabelPanel() {

        printingLabelsTxt.setFont(new Font("Verdana", ITALIC, 10));
        printingLabelTxt.setFont(new Font("Verdana", ITALIC, 10));
        printingLabelsTxt.setColumns(9);
        printingLabelTxt.setColumns(9);


        JLabel salesOrderLabel = new JLabel("Enter Sales Order Number:", SwingConstants.RIGHT);
        salesOrderLabel.setFont(new Font("Verdana", BOLD, 10)); // NOI18N
        JLabel salesOrderModelLabel = new JLabel("Enter Model Number:", SwingConstants.RIGHT);
        salesOrderModelLabel.setFont(new Font("Verdana", BOLD, 10)); // NOI18N
        JButton printLabelsBtn = new JButton("Print Labels by SO");
        printLabelsBtn.setFont(new Font("Verdana", BOLD, 10)); // NOI18N
        JButton printLabelBtn = new JButton("Print Label by SO & Model");
        printLabelBtn.setFont(new Font("Verdana", BOLD, 10)); // NOI18N
        printLabelsBtn.setMaximumSize(new Dimension(150, 40));
        printLabelBtn.setMaximumSize(new Dimension(200, 40));

        filterBtn.setFont(new Font("Verdana", BOLD, 10)); // NOI18N
        filterBtn.setMaximumSize(new Dimension(150, 40));
        checkBox.setBounds(100, 100, 50, 50);


        // layout, column, row (debug layout);

        List<SalesOrder> list = SalesOrderDao.view();
        int size = list.size();
        String[][] rows = new String[size][5];
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

            row++;

        }
        String[] columns = {"Sales Order", "Model", "Description", "Contract Code", "Quantity"};

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

            @SuppressWarnings({"unchecked", "rawtypes"})
            public Class getColumnClass(int column) {
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };

        final JTable jt = new JTable(model);
        jt.setPreferredScrollableViewportSize(jt.getPreferredSize());
        jt.getColumnModel().getColumn(0).setPreferredWidth(30);
        jt.getColumnModel().getColumn(1).setPreferredWidth(150);
        jt.getColumnModel().getColumn(2).setPreferredWidth(600);
        jt.getColumnModel().getColumn(3).setPreferredWidth(40);
        jt.getColumnModel().getColumn(4).setPreferredWidth(40);
        jt.setFont(new Font("Verdana", ITALIC, 12));

        jt.setDragEnabled(true);
        jt.setAutoCreateRowSorter(true);
        jt.setAutoscrolls(true);
        jt.setShowVerticalLines(true);

        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        jt.setRowSorter(sorter);


        filterBtn.addActionListener(e -> {
            String text = printingLabelsTxt.getText();
            if (text.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                try {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                } catch (PatternSyntaxException pse) {
                    System.err.println("Bad regex pattern");
                }
            }
        });


        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int column = 0;
                int column2 = 1;
                int row2 = jt.getSelectedRow();
                int row3 = jt.convertRowIndexToModel(row2);
                so = jt.getModel().getValueAt(row3, column).toString();
                part = jt.getModel().getValueAt(row3, column2).toString();

                jTable1MouseClicked();
            }
        });


        JScrollPane sp = new JScrollPane(jt);
        sp.setViewportView(jt);

        MigLayout layout = new MigLayout("fillx", "[grow,fill]");
        MigLayout layout2 = new MigLayout("fillx", "[grow,fill]");
        jt.setPreferredScrollableViewportSize(jt.getPreferredSize());
        jt.setFillsViewportHeight(true);
        JPanel top = new JPanel(layout2);
        this.setLayout(layout);


        top.add(salesOrderLabel);
        top.add(printingLabelsTxt);
        top.add(printLabelsBtn, "span");
        top.add(salesOrderModelLabel);
        top.add(printingLabelTxt);
        top.add(printLabelBtn);
        top.add(checkBox);

        top.add(filterBtn);
        top.add(checkBox);
        bottom.add(sp);
        this.add(top, " span, wrap");
        this.add(bottom, " span");
        printLabelListener(printLabelBtn);
        printLabelsListener(printLabelsBtn);
        filterListener(filterBtn);


    }

    public JButton GetDefaultButton() {
        return filterBtn;
    }


    private void filterListener(JButton filterBtn) {

    }

    private void printLabelsListener(JButton button) {
        button.addActionListener(arg0 -> {
            LabelsForSalesOrderDB labelsForSalesOrderDB = new LabelsForSalesOrderDB();
            labelsForSalesOrderDB.printLabels(String.valueOf(Integer.parseInt(printingLabelsTxt.getText().trim())));
        });
    }

    private void jTable1MouseClicked() {
        printingLabelsTxt.setText(so);
        printingLabelTxt.setText(part);
    }

    private void printLabelListener(JButton button) {
        button.addActionListener(arg0 -> {
            if (checkBox.isSelected()) {

                LabelsForSalesOrderDB labelsForSalesOrderDB = new LabelsForSalesOrderDB();
                labelsForSalesOrderDB.printOneLabel(
                        String.valueOf(Integer.parseInt(printingLabelsTxt.getText().trim())),
                        printingLabelTxt.getText().trim());
            } else {

                LabelsForSalesOrderDB labelsForSalesOrderDB = new LabelsForSalesOrderDB();
                labelsForSalesOrderDB.printLabel(
                        String.valueOf(Integer.parseInt(printingLabelsTxt.getText().trim())),
                        printingLabelTxt.getText().trim());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

}