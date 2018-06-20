package gui;

import engine.board.QuanCo;

public class GameConfig {
    public LoaiNguoiChoi loaiNguoiChoiX;
    public LoaiNguoiChoi loaiNguoiChoiO;
    public QuanCo.LoaiQuanCo quanCoDiTruoc;

    public enum  LoaiNguoiChoi {
        COMPUTER,
        HUMAN
    }

    public static GameConfig createDefaultGameConfig() {
        GameConfig gameConfig = new GameConfig();
        gameConfig.loaiNguoiChoiX = LoaiNguoiChoi.HUMAN;
        gameConfig.loaiNguoiChoiO = LoaiNguoiChoi.COMPUTER;
        gameConfig.quanCoDiTruoc = QuanCo.LoaiQuanCo.X;
        return gameConfig;
    }
}
