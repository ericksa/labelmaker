package start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Inner MenuActionListener class
class MenuActionListener implements ActionListener {

    private MainPrintShippingLabels main;

    MenuActionListener(MainPrintShippingLabels main) {
        this.main = main;
    }

    private void PrintProductLabelsPerformed() {
        // Call the printProductLabels() method in outer object.
        main.printProductLabels();
    }

    private void helpActionPerformed() {
        // Call the printProductLabels() method in outer object.
        main.blackCard();
    }

    private void printLabelsActionPerformed() {
        // Call the printProductLabels() method in outer object.
        main.printLabelsCard();
    }

    private void about() {
        // Call the printProductLabels() method in outer object.
        main.about();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);

        switch (command) {

            case "Print Product Labels":
                PrintProductLabelsPerformed();
                break;
            case "Help":
                helpActionPerformed();
                break;
            case "Print Shipping Labels":
                printLabelsActionPerformed();
                break;
            case "About":
                about();
                break;
            default:
        }
    }
}
