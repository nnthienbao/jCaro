package engine.player;

import engine.board.QuanCo;
import engine.board.BanCo;
import engine.board.ToaDo;

public class PlayerO extends Player {
    public PlayerO(BanCo banCo) {
        super(banCo);
    }

    @Override
    public QuanCo taoQuanCo(ToaDo toaDo) {
        return new QuanCo(toaDo, QuanCo.LoaiQuanCo.O);
    }
}
