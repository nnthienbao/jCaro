package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FrameJCaroGame extends JFrame {
    private GameConfig gameConfig = GameConfig.createDefaultGameConfig();
    private GameSettingPanel gameSettingPanel;
    public FrameJCaroGame() throws HeadlessException {
        super();
        this.setTitle("JCaro - Tạo bỏi nnthienbao");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        // Tao thanh menu
        createMenuBar();
        // Component for ui
        gameSettingPanel = new GameSettingPanel();
        // Ban co
        this.add(new BanCoPanel());
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        JMenuItem settingGameMenuItem = createSettingGameMemuItem();
        menu.add(settingGameMenuItem);

        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    private JMenuItem createSettingGameMemuItem() {
        JMenuItem menuItem = new JMenuItem("Setting");
        menuItem.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                showGameSettingDialog();
            }
        });

        return menuItem;
    }

    private void showGameSettingDialog() {
        gameSettingPanel.fillConfig(gameConfig);
        int result = JOptionPane.showConfirmDialog(this, gameSettingPanel, "Setting", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(result == JOptionPane.OK_OPTION) {
            gameConfig = gameSettingPanel.getConfig();
        }
    }
}









