package engine.player.ai;

import engine.board.BanCo;
import engine.board.QuanCo;
import engine.board.ToaDo;
import engine.player.Player;
import org.junit.Test;

import org.junit.Assert;

public class TestMinMaxStrategy {
    private static final int DEPTH = 2;
    @Test
    public void TestNuocDi() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        banCo.setCurrentPlayer(banCo.getXPlayer());
        Player xPlayer = banCo.getXPlayer(), oPlayer = banCo.getOPlayer();
        HitStrategy minMaxStrategy = new MinMaxStrategy(new DanhGiaBanCoDonGian());

        int i = 0;
        while(banCo.kiemTraThangThua() == null) {
            QuanCo quanCo = minMaxStrategy.hit(banCo, DEPTH);
            Assert.assertTrue(banCo.getQuanCo(quanCo.getToaDo()) == null);
            System.out.println("Nuoc di thu " + (i++) + ": " + quanCo.toString());
            banCo.getCurrentPlayer().hit(quanCo.getToaDo());
        }
        System.out.println("Nguoi choi chien thang la: " + banCo.kiemTraThangThua().toString());
    }
}
