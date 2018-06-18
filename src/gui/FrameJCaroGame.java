package gui;

import javax.swing.*;
import java.awt.*;

public class FrameJCaroGame extends JFrame {
    public FrameJCaroGame() throws HeadlessException {
        super();
        this.setTitle("JCaro - Tạo bỏi nnthienbao");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        // Ban co
        BanCoPanel banCoPanel = new BanCoPanel();

        this.add(banCoPanel);

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
