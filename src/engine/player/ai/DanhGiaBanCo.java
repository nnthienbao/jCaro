package engine.player.ai;

import engine.board.BanCo;

public interface DanhGiaBanCo {
    int getScore(BanCo banCo, int depth);
}
