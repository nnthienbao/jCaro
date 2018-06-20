package gui;

import engine.board.QuanCo;

import javax.swing.*;

public class GameSettingPanel extends JPanel {
    private JRadioButton rdComputerPlayerX;
    private JRadioButton rdHumanPlayerX;
    private JRadioButton rdComputerPlayerO;
    private JRadioButton rdHumanPlayerO;
    private JRadioButton rdODiTruoc;
    private JRadioButton rdXDiTruoc;

    public GameSettingPanel() {
        super(true);
        initComponent();
    }

    private void initComponent() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Setting for player X
        JPanel xPlayerPanel = new JPanel();
        xPlayerPanel.setBorder(BorderFactory.createTitledBorder("Player X"));
        rdComputerPlayerX = new JRadioButton("Computer");
        rdHumanPlayerX = new JRadioButton("Human");
        ButtonGroup groupPlayerX = new ButtonGroup();
        groupPlayerX.add(rdComputerPlayerX);
        groupPlayerX.add(rdHumanPlayerX);
        xPlayerPanel.add(rdComputerPlayerX);
        xPlayerPanel.add(rdHumanPlayerX);

        // Setting for player O
        JPanel oPlayerPanel = new JPanel();
        oPlayerPanel.setBorder(BorderFactory.createTitledBorder("Player O"));
        rdComputerPlayerO = new JRadioButton("Computer");
        rdHumanPlayerO = new JRadioButton("Human");
        ButtonGroup groupPlayerO = new ButtonGroup();
        groupPlayerO.add(rdComputerPlayerO);
        groupPlayerO.add(rdHumanPlayerO);
        oPlayerPanel.add(rdComputerPlayerO);
        oPlayerPanel.add(rdHumanPlayerO);

        // Setting nguoi choi di truoc
        JPanel chonNguoiDiTruocPanel = new JPanel();
        chonNguoiDiTruocPanel.setBorder(BorderFactory.createTitledBorder("Người đi trước"));
        ButtonGroup groupDiTruoc = new ButtonGroup();
         rdODiTruoc = new JRadioButton("Player O");
        rdXDiTruoc = new JRadioButton("Player X");
        groupDiTruoc.add(rdXDiTruoc);
        groupDiTruoc.add(rdODiTruoc);
        chonNguoiDiTruocPanel.add(rdXDiTruoc);
        chonNguoiDiTruocPanel.add(rdODiTruoc);

        this.add(xPlayerPanel);
        this.add(oPlayerPanel);
        this.add(chonNguoiDiTruocPanel);
    }

    public void fillConfig(GameConfig gameConfig) {
        if(gameConfig.loaiNguoiChoiX == GameConfig.LoaiNguoiChoi.HUMAN) {
            rdHumanPlayerX.setSelected(true);
        } else {
            rdComputerPlayerX.setSelected(true);
        }

        if(gameConfig.loaiNguoiChoiO == GameConfig.LoaiNguoiChoi.HUMAN) {
            rdHumanPlayerO.setSelected(true);
        } else {
            rdComputerPlayerO.setSelected(true);
        }

        if(gameConfig.quanCoDiTruoc == QuanCo.LoaiQuanCo.X) {
            rdXDiTruoc.setSelected(true);
        } else {
            rdODiTruoc.setSelected(true);
        }
    }

    public GameConfig getConfig() {
        GameConfig newGameConfig = new GameConfig();
        newGameConfig.loaiNguoiChoiX = rdHumanPlayerX.isSelected()
                ? GameConfig.LoaiNguoiChoi.HUMAN
                : GameConfig.LoaiNguoiChoi.COMPUTER;
        newGameConfig.loaiNguoiChoiO = rdHumanPlayerO.isSelected()
                ? GameConfig.LoaiNguoiChoi.HUMAN
                : GameConfig.LoaiNguoiChoi.COMPUTER;
        newGameConfig.quanCoDiTruoc = rdXDiTruoc.isSelected()
                ? QuanCo.LoaiQuanCo.X
                : QuanCo.LoaiQuanCo.O;

        return newGameConfig;
    }
}









