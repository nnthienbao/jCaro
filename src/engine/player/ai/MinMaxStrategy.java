package engine.player.ai;

import engine.board.BanCo;
import engine.board.QuanCo;
import engine.board.ToaDo;

public class MinMaxStrategy implements HitStrategy {

    private DanhGiaBanCo danhGiaBanCo;

    public MinMaxStrategy(DanhGiaBanCo danhGiaBanCo) {
        this.danhGiaBanCo = danhGiaBanCo;
    }

    @Override
    public QuanCo hit(BanCo banCo, int depth) {
        if(banCo.getDanhSachQuanCo().size() == 0) {
            return banCo.getCurrentPlayer().taoQuanCo(new ToaDo(banCo.getTotalColumn() / 2, banCo.getTotalRow() / 2));
        }

        QuanCo quanCoTotNhat = null;
        int diemThapNhat = Integer.MAX_VALUE;
        int diemCaoNhat = Integer.MIN_VALUE;
        for(int y = 0; y < banCo.getTotalRow(); y++) {
            for(int x = 0; x < banCo.getTotalColumn(); x++) {
                ToaDo toaDoDangXet = new ToaDo(x, y);
                if(banCo.getQuanCo(toaDoDangXet) == null) {
                    QuanCo quanCoDiThu = banCo.getCurrentPlayer().taoQuanCo(toaDoDangXet);
                    BanCo tryBanCo = tryHit(banCo, quanCoDiThu);
                    int currentScore = banCo.getCurrentPlayer() == banCo.getXPlayer()
                            ? min(tryBanCo, depth - 1)
                            : max(tryBanCo, depth - 1);
                    if(banCo.getCurrentPlayer() == banCo.getXPlayer() && currentScore <= diemThapNhat) {
                        diemThapNhat = currentScore;
                        quanCoTotNhat = quanCoDiThu;
                    } else if(banCo.getCurrentPlayer() == banCo.getOPlayer() && currentScore >= diemCaoNhat) {
                        diemCaoNhat = currentScore;
                        quanCoTotNhat = quanCoDiThu;
                    }
                }
            }
        }
        return quanCoTotNhat;
    }

    private int min(BanCo banCo, int depth) {
        if(depth == 0 || banCo.kiemTraThangThua() != null) {
            return danhGiaBanCo.getScore(banCo, depth);
        }

        int diemThapNhat = Integer.MAX_VALUE;
        for(int y = 0; y < banCo.getTotalRow(); y++) {
            for(int x = 0; x < banCo.getTotalColumn(); x++) {
                ToaDo toaDoDangXet = new ToaDo(x, y);
                if(banCo.getQuanCo(toaDoDangXet) == null) {
                    QuanCo quanCoDiThu = banCo.getCurrentPlayer().taoQuanCo(toaDoDangXet);
                    BanCo tryBanCo = tryHit(banCo, quanCoDiThu);
                    int score = max(tryBanCo, depth - 1);
                    if(score <= diemThapNhat) diemThapNhat = score;
                }
            }
        }
        return diemThapNhat;
    }

    private int max(BanCo banCo, int depth) {
        if(depth == 0 || banCo.kiemTraThangThua() != null) {
            return danhGiaBanCo.getScore(banCo, depth);
        }

        int diemCaoNhat = Integer.MIN_VALUE;
        for(int y = 0; y < banCo.getTotalRow(); y++) {
            for(int x = 0; x < banCo.getTotalColumn(); x++) {
                ToaDo toaDoDangXet = new ToaDo(x, y);
                if(banCo.getQuanCo(toaDoDangXet) == null) {
                    QuanCo quanCoDiThu = banCo.getCurrentPlayer().taoQuanCo(toaDoDangXet);
                    BanCo tryBanCo = tryHit(banCo, quanCoDiThu);
                    int score = min(tryBanCo, depth - 1);
                    if(score >= diemCaoNhat) diemCaoNhat = score;
                }
            }
        }
        return diemCaoNhat;
    }

    private BanCo tryHit(BanCo banCoCu, QuanCo quanCoMoi) {
        BanCo.Builder builder = new BanCo.Builder(banCoCu.getTotalRow(), banCoCu.getTotalColumn(), banCoCu.getTotalTileToWin());
        builder.setCurrentPlayer(banCoCu.getCurrentPlayer().getDoiThu());
        for(QuanCo quanCo : banCoCu.getDanhSachQuanCo()) {
            builder.setQuanCo(quanCo);
        }
        builder.setQuanCo(quanCoMoi);
        return builder.build();
    }
}
