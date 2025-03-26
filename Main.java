package MicroProject;

import javax.swing.SwingUtilities;
// Removed unused import

public class Main {
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        ECommerceGUI gui = new ECommerceGUI();
        gui.setVisible(true);
    });
    }
}
