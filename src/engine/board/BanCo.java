package engine.board;

import engine.player.Player;
import engine.player.PlayerO;
import engine.player.PlayerX;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BanCo {
    private final int TOTAL_ROW;
    private final int TOTAL_COLUMN;
    private final int TOTAL_TILE_TO_WIN;
    private QuanCo[][] toaDoBanCo = null;
    private Set<QuanCo> dsQuanCo = new HashSet<>();
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;

    private BanCo(int totalRow, int toalColumn, int totalTileToWin) {
        this.TOTAL_ROW = totalRow;
        this.TOTAL_COLUMN = toalColumn;
        this.TOTAL_TILE_TO_WIN = totalTileToWin;
        initBanCo();
        playerX = new PlayerX(this);
        playerO = new PlayerO(this);
        playerX.setDoiThu(playerO);
        playerO.setDoiThu(playerX);
    }

    private void initBanCo() {
        toaDoBanCo = new QuanCo[TOTAL_ROW][TOTAL_COLUMN];
        for(int y = 0; y < TOTAL_ROW; y++) {
            for(int x = 0; x < TOTAL_COLUMN; x++) {
                toaDoBanCo[y][x] = null;
            }
        }
    }

    public void setQuanCo(QuanCo quanCo) {
        toaDoBanCo[quanCo.getToaDo().getY()][quanCo.getToaDo().getX()] = quanCo;
        dsQuanCo.add(quanCo);
    }

    public QuanCo getQuanCo(ToaDo toaDo) {
        return toaDoBanCo[toaDo.getY()][toaDo.getX()];
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getXPlayer() {
        return playerX;
    }

    public Player getOPlayer() {
        return playerO;
    }

    public int getTotalRow() {
        return TOTAL_ROW;
    }

    public int getTotalColumn() {
        return TOTAL_COLUMN;
    }

    public int getTotalTileToWin() {
        return TOTAL_TILE_TO_WIN;
    }

    public Collection<QuanCo> getDanhSachQuanCo() {
        return Collections.unmodifiableCollection(dsQuanCo);
    }

    public Player kiemTraThangThua() {
        for(QuanCo quanCoDangXet : dsQuanCo) {
            // Kiem tra hang doc, ngang, duong cheo chinh, duong cheo phu
            boolean kqKiemTra = kiemTraThangThuaHangDoc(quanCoDangXet) ||
                                kiemTraThangThuaHangNgang(quanCoDangXet) ||
                                kiemTraThangThuaHangCheoChinh(quanCoDangXet) ||
                                kiemTraThangThuaHangCheoPhu(quanCoDangXet);
            if(kqKiemTra) return quanCoDangXet.getLoaiQuanCo() == QuanCo.LoaiQuanCo.X ? playerX : playerO;
        }
        return null;
    }

    private boolean kiemTraThangThuaHangDoc(QuanCo quanCoDangXet) {
        QuanCo quanCoChanTren = null, quanCoChanDuoi = null;
        boolean kiemTraTren = true, kiemTraDuoi = true;
        int count = 0;
        ToaDo toaDoQuanCoDangXet = quanCoDangXet.getToaDo();
        for(int i = 1;kiemTraTren || kiemTraDuoi;i++) {
            if(kiemTraTren) {
                int toaDoYKiemTra = toaDoQuanCoDangXet.getY() - i;
                if(toaDoYKiemTra >= 0) {
                    QuanCo quanCoKiemTra = toaDoBanCo[toaDoYKiemTra][toaDoQuanCoDangXet.getX()];
                    if(quanCoKiemTra == null) {
                        kiemTraTren = false;
                    } else if(quanCoKiemTra.getLoaiQuanCo() == quanCoDangXet.getLoaiQuanCo()) {
                        count++;
                    } else {
                        kiemTraTren = false;
                        quanCoChanTren = quanCoKiemTra;
                    }
                } else {
                    kiemTraTren = false;
                }
            }
            if(kiemTraDuoi) {
                int toaDoYKiemTra = toaDoQuanCoDangXet.getY() + i;
                if(toaDoYKiemTra < TOTAL_ROW) {
                    QuanCo quanCoKiemTra = toaDoBanCo[toaDoYKiemTra][toaDoQuanCoDangXet.getX()];
                    if(quanCoKiemTra == null) {
                        kiemTraDuoi = false;
                    } else if(quanCoKiemTra.getLoaiQuanCo() == quanCoDangXet.getLoaiQuanCo()) {
                        count++;
                    } else {
                        kiemTraDuoi = false;
                        quanCoChanDuoi = quanCoKiemTra;
                    }
                } else {
                    kiemTraDuoi = false;
                }
            }
        }

        return (count >= TOTAL_TILE_TO_WIN - 1 && (quanCoChanTren == null || quanCoChanDuoi == null));
    }

    private boolean kiemTraThangThuaHangNgang(QuanCo quanCoDangXet) {
        QuanCo quanCoChanTrai = null, quanCoChanPhai = null;
        boolean kiemTraTrai = true, kiemTraPhai = true;
        int count = 0;
        ToaDo toaDoQuanCoDangXet = quanCoDangXet.getToaDo();
        for(int i = 1;kiemTraTrai || kiemTraPhai;i++) {
            if(kiemTraTrai) {
                int toaDoXKiemTra = toaDoQuanCoDangXet.getX() - i;
                if(toaDoXKiemTra >= 0) {
                    QuanCo quanCoKiemTra = toaDoBanCo[toaDoQuanCoDangXet.getY()][toaDoXKiemTra];
                    if(quanCoKiemTra == null) {
                        kiemTraTrai = false;
                    } else if(quanCoKiemTra.getLoaiQuanCo() == quanCoDangXet.getLoaiQuanCo()) {
                        count++;
                    } else {
                        kiemTraTrai = false;
                        quanCoChanTrai = toaDoBanCo[toaDoQuanCoDangXet.getY()][toaDoXKiemTra];
                    }
                } else {
                    kiemTraTrai = false;
                }
            }
            if(kiemTraPhai) {
                int toaDoXKiemTra = toaDoQuanCoDangXet.getX() + i;
                if(toaDoXKiemTra < TOTAL_COLUMN) {
                    QuanCo quanCoKiemTra = toaDoBanCo[toaDoQuanCoDangXet.getY()][toaDoXKiemTra];
                    if(quanCoKiemTra == null) {
                        kiemTraPhai = false;
                    } else if(quanCoKiemTra.getLoaiQuanCo() == quanCoDangXet.getLoaiQuanCo()) {
                        count++;
                    } else {
                        kiemTraPhai = false;
                        quanCoChanPhai = toaDoBanCo[toaDoQuanCoDangXet.getY()][toaDoXKiemTra];
                    }
                } else {
                    kiemTraPhai = false;
                }
            }
        }

        return (count >= TOTAL_TILE_TO_WIN - 1 && (quanCoChanTrai == null || quanCoChanPhai == null));
    }

    private boolean kiemTraThangThuaHangCheoChinh(QuanCo quanCoDangXet) {
        QuanCo quanCoChanCheoTren = null, quanCoChanCheoDuoi = null;
        boolean kiemTraCheoTren = true, kiemTraCheoDuoi = true;
        int count = 0;
        ToaDo toaDoQuanCoDangXet = quanCoDangXet.getToaDo();
        for(int i = 1;kiemTraCheoTren || kiemTraCheoDuoi;i++) {
            if(kiemTraCheoTren) {
                int toaDoXKiemTra = toaDoQuanCoDangXet.getX() + i;
                int toaDoYKiemTra = toaDoQuanCoDangXet.getY() - i;

                if(toaDoXKiemTra < TOTAL_COLUMN && toaDoYKiemTra >= 0) {
                    QuanCo quanCoKiemTra = toaDoBanCo[toaDoYKiemTra][toaDoXKiemTra];
                    if(quanCoKiemTra == null) {
                        kiemTraCheoTren = false;
                    } else if(quanCoKiemTra.getLoaiQuanCo() == quanCoDangXet.getLoaiQuanCo()) {
                        count++;
                    } else {
                        kiemTraCheoTren = false;
                        quanCoChanCheoTren = toaDoBanCo[toaDoYKiemTra][toaDoXKiemTra];
                    }
                } else {
                    kiemTraCheoTren = false;
                }
            }
            if(kiemTraCheoDuoi) {
                int toaDoXKiemTra = toaDoQuanCoDangXet.getX() - i;
                int toaDoYKiemTra = toaDoQuanCoDangXet.getY() + i;
                if(toaDoXKiemTra >= 0 && toaDoYKiemTra < TOTAL_ROW) {
                    QuanCo quanCoKiemTra = toaDoBanCo[toaDoYKiemTra][toaDoXKiemTra];
                    if(quanCoKiemTra == null) {
                        kiemTraCheoDuoi = false;
                    } else if(quanCoKiemTra.getLoaiQuanCo() == quanCoDangXet.getLoaiQuanCo()) {
                        count++;
                    } else {
                        kiemTraCheoDuoi = false;
                        quanCoChanCheoDuoi = toaDoBanCo[toaDoYKiemTra][toaDoXKiemTra];
                    }
                } else {
                    kiemTraCheoDuoi = false;
                }
            }
        }

        return (count >= TOTAL_TILE_TO_WIN - 1 && (quanCoChanCheoTren == null || quanCoChanCheoDuoi == null));
    }

    private boolean kiemTraThangThuaHangCheoPhu(QuanCo quanCoDangXet) {
        QuanCo quanCoChanCheoTren = null, quanCoChanCheoDuoi = null;
        boolean kiemTraCheoTren = true, kiemTraCheoDuoi = true;
        int count = 0;
        ToaDo toaDoQuanCoDangXet = quanCoDangXet.getToaDo();
        for(int i = 1;kiemTraCheoTren || kiemTraCheoDuoi;i++) {
            if(kiemTraCheoTren) {
                int toaDoXKiemTra = toaDoQuanCoDangXet.getX() - i;
                int toaDoYKiemTra = toaDoQuanCoDangXet.getY() - i;
                if(toaDoXKiemTra >= 0 && toaDoYKiemTra >= 0) {
                    QuanCo quanCoKiemTra = toaDoBanCo[toaDoYKiemTra][toaDoXKiemTra];
                    if(quanCoKiemTra == null) {
                        kiemTraCheoTren = false;
                    } else if(quanCoKiemTra.getLoaiQuanCo() == quanCoDangXet.getLoaiQuanCo()) {
                        count++;
                    } else {
                        kiemTraCheoTren = false;
                        quanCoChanCheoTren = toaDoBanCo[toaDoYKiemTra][toaDoXKiemTra];
                    }
                } else {
                    kiemTraCheoTren = false;
                }
            }
            if(kiemTraCheoDuoi) {
                int toaDoXKiemTra = toaDoQuanCoDangXet.getX() + i;
                int toaDoYKiemTra = toaDoQuanCoDangXet.getY() + i;
                if(toaDoXKiemTra < TOTAL_COLUMN && toaDoYKiemTra < TOTAL_ROW) {
                    QuanCo quanCoKiemTra = toaDoBanCo[toaDoYKiemTra][toaDoXKiemTra];
                    if(quanCoKiemTra == null) {
                        kiemTraCheoDuoi = false;
                    } else if(quanCoKiemTra.getLoaiQuanCo() == quanCoDangXet.getLoaiQuanCo()) {
                        count++;
                    } else {
                        kiemTraCheoDuoi = false;
                        quanCoChanCheoDuoi = toaDoBanCo[toaDoYKiemTra][toaDoXKiemTra];
                    }
                } else {
                    kiemTraCheoDuoi = false;
                }
            }
        }

        return (count >= TOTAL_TILE_TO_WIN - 1 && (quanCoChanCheoTren == null || quanCoChanCheoDuoi == null));
    }

    public static BanCo taoBanCoCoBan() {
        Builder builder = new Builder(20, 20, 5);
        return builder.build();
    }


    public static class Builder {
        private BanCo banCo;

        public Builder(int totalRow, int totalColumn, int totalTileToWin) {
            if(totalRow < totalTileToWin || totalColumn < totalTileToWin) {
                throw new RuntimeException("Bàn cờ không hợp lệ");
            }
            banCo = new BanCo(totalRow, totalColumn, totalTileToWin);
        }

        public void setQuanCo(QuanCo quanCo) {
            banCo.setQuanCo(quanCo);
        }

        public BanCo build() {
            return banCo;
        }
    }
}
