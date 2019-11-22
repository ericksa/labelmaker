package start;

import javax.swing.*;

// Inner Menu Bar class
class MenuBar {

    private JMenuBar menuBar;

    MenuBar(MainPrintShippingLabels main) {

        MenuActionListener mal = new MenuActionListener(main);
        System.out.println("menuBar");


        menuBar = new JMenuBar();


        JMenu mainMenu = new JMenu("File");
        JMenu aboutMenu = new JMenu("About");
        menuBar.add(mainMenu);
        menuBar.add(aboutMenu);

        //Define addMenu items
        JMenuItem addProductLabelItem = new JMenuItem("Print Product Labels");
        JMenuItem addShippingLabelItem = new JMenuItem("Print Shipping Labels");


        JMenuItem addHelpItem = new JMenuItem("Help");
        JMenuItem addAboutItem = new JMenuItem("Info");


        addShippingLabelItem.addActionListener(mal);
        addHelpItem.addActionListener(mal);
        addProductLabelItem.addActionListener(mal);
        addAboutItem.addActionListener(mal);

        //Add main menu items/menu
        mainMenu.add(addProductLabelItem);
        mainMenu.add(addShippingLabelItem);

        aboutMenu.add(addAboutItem);
        aboutMenu.add(addHelpItem);
    }

    JMenuBar getMenuBar() {
        return menuBar;
    }

}
