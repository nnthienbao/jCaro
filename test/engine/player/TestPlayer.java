package engine.player;

import engine.board.BanCo;
import engine.board.ToaDo;
import engine.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPlayer {
    @Test
    public void TestLuatLuanPhienNguoiChoi() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player xPlayer = banCo.getXPlayer();
        Player oPlayer = banCo.getOPlayer();

        assertEquals(banCo.getCurrentPlayer(), null);

        banCo.setCurrentPlayer(xPlayer);
        assertEquals(banCo.getCurrentPlayer(), xPlayer);

        xPlayer.hit(new ToaDo(0, 0));
        assertEquals(banCo.getCurrentPlayer(), oPlayer);

        xPlayer.hit(new ToaDo(0, 1));
        assertEquals(banCo.getCurrentPlayer(), oPlayer);

        oPlayer.hit(new ToaDo(5, 6));
        assertEquals(banCo.getCurrentPlayer(), xPlayer);
    }
}
