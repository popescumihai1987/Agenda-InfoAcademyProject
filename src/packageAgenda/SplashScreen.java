/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageAgenda;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {

    Image img = Toolkit.getDefaultToolkit().getImage("mojo.jpg");
    ImageIcon imgicon = new ImageIcon(img);

    public SplashScreen() {
        try {
            setSize(633, 300);
            setLocationRelativeTo(null);
            setIconImage(img);
            setVisible(true);
            Thread.sleep(2000);
            dispose();
//            javax.swing.JOptionPane.showMessageDialog((java.awt.Component) null, "Welcome", "Welcome Screen:", javax.swing.JOptionPane.DEFAULT_OPTION);
        } catch (HeadlessException | InterruptedException exception) {
            javax.swing.JOptionPane.showMessageDialog((java.awt.Component) null, "Error" + exception.getMessage(), "Error:", javax.swing.JOptionPane.DEFAULT_OPTION);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }

}

