package gui;

import engine.board.BanCo;
import engine.board.QuanCo;
import engine.board.ToaDo;
import engine.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class BanCoPanel extends JPanel implements MouseListener{
    public static final int WIDTH = 601;
    public static final int HEIGHT = 601;
    public static final int CELL_SIZE = 30;
    private static final Image xIcon , oIcon;

    static {
        try {
            xIcon = ImageIO.read(BanCoPanel.class.getResource("/X.png"));
            oIcon = ImageIO.read(BanCoPanel.class.getResource("/O.png"));
        } catch (IOException e) {
            throw new RuntimeException("Không tìm thấy icon");
        }
    }

    private BanCo banCo;

    public BanCoPanel() {
        super();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setSize(WIDTH, HEIGHT);
        this.addMouseListener(this);
        resetGame();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int totaRowlLine = banCo.getTotalRow() + 1, totalColumnLine = banCo.getTotalColumn() + 1;

        for(int i = 0; i < totaRowlLine; i++) {
            g2.drawLine(0, i*CELL_SIZE, WIDTH, i*CELL_SIZE);
        }
        for(int i = 0; i < totalColumnLine; i++) {
            g2.drawLine(i*CELL_SIZE, 0,  i*CELL_SIZE, HEIGHT);
        }

        for(QuanCo quanCo : banCo.getDanhSachQuanCo()) {
            g2.drawImage(quanCo.getLoaiQuanCo() == QuanCo.LoaiQuanCo.X ? xIcon : oIcon,
                    CELL_SIZE*quanCo.getToaDo().getX(),
                    CELL_SIZE*quanCo.getToaDo().getY(),
                        CELL_SIZE,
                        CELL_SIZE,
                    null);
        }
    }

    private void resetGame() {
        banCo = BanCo.taoBanCoCoBan();
        banCo.setCurrentPlayer(banCo.getXPlayer());
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        ToaDo toaDoBanCo = convertToaDoManhinhThanhToaDoBanCo(mouseEvent.getX(), mouseEvent.getY());
        banCo.getCurrentPlayer().hit(toaDoBanCo);
        repaint();
        Player playerWin = banCo.kiemTraThangThua();
        if(playerWin == banCo.getXPlayer()) {
            JOptionPane.showMessageDialog(this, "X thắng");
            resetGame();
        } else if(playerWin == banCo.getOPlayer()) {
            JOptionPane.showMessageDialog(this, "O thắng");
            resetGame();
        }
    }

    private ToaDo convertToaDoManhinhThanhToaDoBanCo(int x, int y) {
        return new ToaDo(x / CELL_SIZE, y / CELL_SIZE);
    }

    // Khog dung
    @Override
    public void mouseClicked(MouseEvent mouseEvent) { }
    @Override
    public void mouseReleased(MouseEvent mouseEvent) { }
    @Override
    public void mouseEntered(MouseEvent mouseEvent) { }
    @Override
    public void mouseExited(MouseEvent mouseEvent) { }
}









