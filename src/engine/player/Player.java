package engine.player;

import engine.board.QuanCo;
import engine.board.BanCo;
import engine.board.ToaDo;

public abstract class Player {
    private BanCo banCo;
    protected Player doiThu;

    public Player(BanCo banCo) {
        this.banCo = banCo;
    }

    public abstract QuanCo taoQuanCo(ToaDo toaDo);

    public void hit(ToaDo toaDo) {
        banCo.setQuanCo(taoQuanCo(toaDo));
        banCo.setCurrentPlayer(doiThu);
    }

    public Player getDoiThu() {
        return doiThu;
    }

    public void setDoiThu(Player doiThu) {
        this.doiThu = doiThu;
    }
}
