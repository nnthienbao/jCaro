package engine.board;

import engine.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBanCo {
    @Test
    public void TestKiemTraThangThuaHangDoc() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerX.hit(new ToaDo(0,0));
        playerX.hit(new ToaDo(0,1));
        playerX.hit(new ToaDo(0,2));
        playerX.hit(new ToaDo(0,3));
        playerX.hit(new ToaDo(0,4));
        assertEquals(banCo.kiemTraThangThua(), playerX);
    }

    @Test
    public void TestKiemTraThangThuaHangDocChanHaiDau() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerO.hit(new ToaDo(0, 0));
        playerX.hit(new ToaDo(0,1));
        playerX.hit(new ToaDo(0,2));
        playerX.hit(new ToaDo(0,3));
        playerX.hit(new ToaDo(0,4));
        playerX.hit(new ToaDo(0,5));
        playerO.hit(new ToaDo(0, 6));
        assertEquals(banCo.kiemTraThangThua(), null);
    }

    @Test
    public void TestKiemTraThangThuaHangDocChanMotDau() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerX.hit(new ToaDo(0,1));
        playerX.hit(new ToaDo(0,2));
        playerX.hit(new ToaDo(0,3));
        playerX.hit(new ToaDo(0,4));
        playerX.hit(new ToaDo(0,5));
        playerO.hit(new ToaDo(0, 6));
        assertEquals(banCo.kiemTraThangThua(), playerX);
    }

    @Test
    public void TestKiemTraThangThuaHangDoc_6_QuanCo() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerX.hit(new ToaDo(0,7));
        playerX.hit(new ToaDo(0,8));
        playerX.hit(new ToaDo(0,9));
        playerX.hit(new ToaDo(0,10));
        playerX.hit(new ToaDo(0,11));
        playerX.hit(new ToaDo(0, 12));
        assertEquals(banCo.kiemTraThangThua(), playerX);
    }

    @Test
    public void TestKiemTraThangThuaHangNgang() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerX.hit(new ToaDo(5,0));
        playerX.hit(new ToaDo(6,0));
        playerX.hit(new ToaDo(7,0));
        playerX.hit(new ToaDo(8,0));
        playerX.hit(new ToaDo(9,0));
        assertEquals(banCo.kiemTraThangThua(), playerX);
    }

    @Test
    public void TestKiemTraThangThuaHangNgangChanHaiDau() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerO.hit(new ToaDo(0, 0));
        playerX.hit(new ToaDo(1,0));
        playerX.hit(new ToaDo(2,0));
        playerX.hit(new ToaDo(3,0));
        playerX.hit(new ToaDo(4,0));
        playerX.hit(new ToaDo(5,0));
        playerO.hit(new ToaDo(6, 0));
        assertEquals(banCo.kiemTraThangThua(), null);
    }

    @Test
    public void TestKiemTraThangThuaHangNgangChanMotDau() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerX.hit(new ToaDo(4,6));
        playerX.hit(new ToaDo(5,6));
        playerX.hit(new ToaDo(6,6));
        playerX.hit(new ToaDo(7,6));
        playerX.hit(new ToaDo(8,6));
        playerO.hit(new ToaDo(9, 6));
        assertEquals(banCo.kiemTraThangThua(), playerX);
    }

    @Test
    public void TestKiemTraThangThuaHangNgang_6_QuanCo() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerX.hit(new ToaDo(5,0));
        playerX.hit(new ToaDo(6,0));
        playerX.hit(new ToaDo(7,0));
        playerX.hit(new ToaDo(8,0));
        playerX.hit(new ToaDo(9,0));
        playerX.hit(new ToaDo(10,0));
        assertEquals(banCo.kiemTraThangThua(), playerX);
    }

    @Test
    public void TestKiemTraThangThuaDuongCheoChinh() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerX.hit(new ToaDo(1,10));
        playerX.hit(new ToaDo(2,9));
        playerX.hit(new ToaDo(3,8));
        playerX.hit(new ToaDo(4,7));
        playerX.hit(new ToaDo(5,6));
        assertEquals(banCo.kiemTraThangThua(), playerX);
    }

    @Test
    public void TestKiemTraThangThuaDuongCheoChinhChanHaiDau() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerO.hit(new ToaDo(0, 11));
        playerX.hit(new ToaDo(1,10));
        playerX.hit(new ToaDo(2,9));
        playerX.hit(new ToaDo(3,8));
        playerX.hit(new ToaDo(4,7));
        playerX.hit(new ToaDo(5,6));
        playerO.hit(new ToaDo(6, 5));
        assertEquals(banCo.kiemTraThangThua(), null);
    }

    @Test
    public void TestKiemTraThangThuaDuongCheoChinhChanMotDau() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerX.hit(new ToaDo(1,10));
        playerX.hit(new ToaDo(2,9));
        playerX.hit(new ToaDo(3,8));
        playerX.hit(new ToaDo(4,7));
        playerX.hit(new ToaDo(5,6));
        playerO.hit(new ToaDo(0, 11));
        assertEquals(banCo.kiemTraThangThua(), playerX);
    }

    @Test
    public void TestKiemTraThangThuaDuongCheoChinh_6_QuanCo() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerX.hit(new ToaDo(1,10));
        playerX.hit(new ToaDo(2,9));
        playerX.hit(new ToaDo(3,8));
        playerX.hit(new ToaDo(4,7));
        playerX.hit(new ToaDo(5,6));
        playerX.hit(new ToaDo(6,5));
        assertEquals(banCo.kiemTraThangThua(), playerX);
    }

    @Test
    public void TestKiemTraThangThuaDuongCheoPhu() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerX.hit(new ToaDo(1,8));
        playerX.hit(new ToaDo(2,9));
        playerX.hit(new ToaDo(3,10));
        playerX.hit(new ToaDo(4,11));
        playerX.hit(new ToaDo(5,12));
        assertEquals(banCo.kiemTraThangThua(), playerX);
    }

    @Test
    public void TestKiemTraThangThuaDuongCheoPhuChanHaiDau() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerO.hit(new ToaDo(0, 7));
        playerX.hit(new ToaDo(1,8));
        playerX.hit(new ToaDo(2,9));
        playerX.hit(new ToaDo(3,10));
        playerX.hit(new ToaDo(4,11));
        playerX.hit(new ToaDo(5,12));
        playerO.hit(new ToaDo(6, 13));
        assertEquals(banCo.kiemTraThangThua(), null);
    }

    @Test
    public void TestKiemTraThangThuaDuongCheoPhuChanMotDau() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerX.hit(new ToaDo(1,8));
        playerX.hit(new ToaDo(2,9));
        playerX.hit(new ToaDo(3,10));
        playerX.hit(new ToaDo(4,11));
        playerX.hit(new ToaDo(5,12));
        playerO.hit(new ToaDo(6, 13));
        assertEquals(banCo.kiemTraThangThua(), playerX);
    }

    @Test
    public void TestKiemTraThangThuaDuongCheoPhu_6_QuanCo() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerX.hit(new ToaDo(6,8));
        playerX.hit(new ToaDo(7,9));
        playerX.hit(new ToaDo(8,10));
        playerX.hit(new ToaDo(9,11));
        playerX.hit(new ToaDo(10,12));
        playerX.hit(new ToaDo(11,13));
        assertEquals(banCo.kiemTraThangThua(), playerX);
    }

    @Test
    public void TestKiemTraThangThuaDuongCheoPhu_6_QuanCo_Chan2Dau() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerO.hit(new ToaDo(5,7));
        playerX.hit(new ToaDo(6,8));
        playerX.hit(new ToaDo(7,9));
        playerX.hit(new ToaDo(8,10));
        playerX.hit(new ToaDo(9,11));
        playerX.hit(new ToaDo(10,12));
        playerX.hit(new ToaDo(11,13));
        playerO.hit(new ToaDo(12,14));
        assertEquals(banCo.kiemTraThangThua(), null);
    }

    @Test
    public void TestKiemTraThangThuaDuongCheoPhu_6_QuanCo_Chan1Dau() {
        BanCo banCo = BanCo.taoBanCoCoBan();
        Player playerX = banCo.getXPlayer(), playerO = banCo.getOPlayer();
        playerO.hit(new ToaDo(5,7));
        playerX.hit(new ToaDo(6,8));
        playerX.hit(new ToaDo(7,9));
        playerX.hit(new ToaDo(8,10));
        playerX.hit(new ToaDo(9,11));
        playerX.hit(new ToaDo(10,12));
        playerX.hit(new ToaDo(11,13));
        assertEquals(banCo.kiemTraThangThua(), playerX);
    }
}