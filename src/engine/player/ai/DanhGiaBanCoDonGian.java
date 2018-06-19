package engine.player.ai;

import engine.board.BanCo;
import engine.board.QuanCo;

public class DanhGiaBanCoDonGian implements DanhGiaBanCo {

    private static final int BONUS_THANG = 10000;
    private static final int BONUS_DUONG_2 = 200;
    private static final int BONUS_DUONG_3 = 300;
    private static final int BONUS_DUONG_4 = 800;
    private static final int BONUS_SU_TUONG_TAC = 10;
    private static final int BONUS_CHAN_QUAN_DICH = 10;

    @Override
    public int getScore(BanCo banCo, int depth) {
        return getScoreOPlayer(banCo, depth) - getScoreXPlayer(banCo, depth);
    }

    private int getScoreOPlayer(BanCo banCo, int depth) {
        int score = 0;
        if(banCo.kiemTraThangThua() == banCo.getOPlayer()) score += (depth + 1) * BONUS_THANG;
        score += BONUS_DUONG_2*banCo.tinhTongSoDuong(2, QuanCo.LoaiQuanCo.O);
        score += BONUS_DUONG_3*banCo.tinhTongSoDuong(3, QuanCo.LoaiQuanCo.O);
        score += BONUS_DUONG_4*banCo.tinhTongSoDuong(4, QuanCo.LoaiQuanCo.O);
        score += BONUS_SU_TUONG_TAC*banCo.tinhSuTuongTacGiuaCacQuanCo(QuanCo.LoaiQuanCo.O);
        score += BONUS_CHAN_QUAN_DICH*banCo.tinhTongSoLanChanQuanCuaDoiThu(QuanCo.LoaiQuanCo.O);
        return score;
    }

    private int getScoreXPlayer(BanCo banCo, int depth) {
        int score = 0;
        if(banCo.kiemTraThangThua() == banCo.getOPlayer()) score += (depth + 1) * BONUS_THANG;
        score += BONUS_DUONG_2*banCo.tinhTongSoDuong(2, QuanCo.LoaiQuanCo.X);
        score += BONUS_DUONG_3*banCo.tinhTongSoDuong(3, QuanCo.LoaiQuanCo.X);
        score += BONUS_DUONG_4*banCo.tinhTongSoDuong(4, QuanCo.LoaiQuanCo.X);
        score += BONUS_SU_TUONG_TAC*banCo.tinhSuTuongTacGiuaCacQuanCo(QuanCo.LoaiQuanCo.X);
        score += BONUS_CHAN_QUAN_DICH*banCo.tinhTongSoLanChanQuanCuaDoiThu(QuanCo.LoaiQuanCo.X);
        return score;
    }
}
