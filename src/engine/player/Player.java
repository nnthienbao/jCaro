package engine.player;

import engine.board.QuanCo;
import engine.board.BanCo;
import engine.board.ToaDo;

import java.util.Objects;

public abstract class Player {
    private BanCo banCo;
    protected Player doiThu;

    public Player(BanCo banCo) {
        this.banCo = banCo;
    }

    public abstract QuanCo taoQuanCo(ToaDo toaDo);

    public void hit(ToaDo toaDo) {
        banCo.setQuanCo(taoQuanCo(toaDo));
    }

    public Player getDoiThu() {
        return doiThu;
    }

    public void setDoiThu(Player doiThu) {
        this.doiThu = doiThu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return this.toString().equals(player.toString());
    }

    @Override
    public int hashCode() {

        return Objects.hash(banCo, doiThu);
    }
}
