package engine.player.ai;

import engine.board.BanCo;
import engine.board.QuanCo;
import engine.board.ToaDo;

public interface HitStrategy {
    QuanCo hit(BanCo banCo, int depth);
}
