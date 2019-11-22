package start;

import javax.swing.*;
import java.awt.*;

public class MainPrintShippingLabels {
    private final static String p1 = "ShippingPanel";
    private final static String p2 = "ProductPanel";
    private final static String p3 = "BlackPanel";
    private final static String p4 = "RedPanel";
    private ImageIcon img = new ImageIcon("MT LOGO.JPG");
    private CardLayout cardLayout = new CardLayout();
    private JPanel contentPane = new JPanel(cardLayout);

    private MainPrintShippingLabels() {
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        SwingUtilities.invokeLater(PrintShippingLabelPanel::new);


        SwingUtilities.invokeLater(() -> new MainPrintShippingLabels().displayGUI());
    }

    private void displayGUI() {

        PrintShippingLabelPanel print = new PrintShippingLabelPanel();
        JScrollPane printLabels = new JScrollPane(print);

        PrintProductLabelPanel printProductLabelPanel = new PrintProductLabelPanel();
        JScrollPane productLabels = new JScrollPane(printProductLabelPanel);

        MenuBar menuBar = new MenuBar(this);
        JFrame frame = new JFrame("Labels");
        frame.setAutoRequestFocus(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane.add(productLabels, p2);
        contentPane.add(printLabels, p1);
        contentPane.add(createPanel(Color.BLACK), p3);
        contentPane.add(createPanel(Color.BLUE), p4);

        frame.setIconImage(img.getImage());


        frame.setContentPane(contentPane);
        frame.setJMenuBar(menuBar.getMenuBar());
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setSize(1200, 800);
        frame.isResizable();

    }

    private JPanel createPanel(Color color) {
        JPanel panel = new JPanel();
        panel.setBackground(color);

        return panel;
    }

    void printLabelsCard() {
        //   System.out.println("Selected Print Labels Item ");
        ((CardLayout) contentPane.getLayout()).show(contentPane, p1);

    }

    void printProductLabels() {
        //    System.out.println("Print Product Labels");
        ((CardLayout) contentPane.getLayout()).show(contentPane, p2);
    }

    void blackCard() {
        //    System.out.println("Selected Black Item ");
        ((CardLayout) contentPane.getLayout()).show(contentPane, p3);
    }

    void about() {
        //    System.out.println("About");
        ((CardLayout) contentPane.getLayout()).show(contentPane, p4);
    }
}