package engine.player;

import engine.board.QuanCo;
import engine.board.BanCo;
import engine.board.ToaDo;

public class PlayerX extends Player {
    public PlayerX(BanCo banCo) {
        super(banCo);
    }

    @Override
    public QuanCo taoQuanCo(ToaDo toaDo) {
        return new QuanCo(toaDo, QuanCo.LoaiQuanCo.X);
    }

    @Override
    public String toString() {
        return "X";
    }
}
