package engine.board;

import engine.player.Player;
import engine.player.PlayerO;
import engine.player.PlayerX;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BanCo {
    private final int TOTAL_ROW;
    private final int TOTAL_COLUMN;
    private final int TOTAL_TILE_TO_WIN;
    private QuanCo[][] toaDoBanCo = null;
    private List<QuanCo> dsQuanCo = new ArrayList<>();
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
        if(toaDoBanCo[quanCo.getToaDo().getY()][quanCo.getToaDo().getX()] == null) {
            toaDoBanCo[quanCo.getToaDo().getY()][quanCo.getToaDo().getX()] = quanCo;
            dsQuanCo.add(quanCo);
            this.setCurrentPlayer(currentPlayer.getDoiThu());
        }
    }

    public QuanCo getQuanCo(ToaDo toaDo) {
        if(toaDo.getX() < 0 || toaDo.getX() >= TOTAL_COLUMN || toaDo.getY() < 0 || toaDo.getY() >= TOTAL_ROW) return null;
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

    public int tinhTongSoDuong(int duong, QuanCo.LoaiQuanCo loaiQuanCo) {
        return tinhTongSoDuongHangDoc(duong, loaiQuanCo)
                + tinhTongSoDuongHangNgang(duong, loaiQuanCo)
                + tinhTongSoDuongHangCheoChinh(duong, loaiQuanCo)
                + tinhTongSoDuongHangCheoPhu(duong, loaiQuanCo);
    }

    private int tinhTongSoDuongHangDoc(int duong, QuanCo.LoaiQuanCo loaiQuanCo) {
        int count = 0;
        List<QuanCo> dsQuanCoDaXet = new ArrayList<>();
        for(QuanCo quanCoDangXet : dsQuanCo) {
            if(quanCoDangXet.getLoaiQuanCo() == loaiQuanCo) {
                if(dsQuanCoDaXet.contains(quanCoDangXet)) continue;
                int countDuong = 1;
                boolean kiemTraTren = true, kiemTraDuoi = true;
                for(int i = 1; kiemTraTren || kiemTraDuoi; i++) {
                    if(kiemTraTren) {
                        QuanCo quanCoTren = getQuanCo(new ToaDo(quanCoDangXet.getToaDo().getX(), quanCoDangXet.getToaDo().getY() - i));
                        if (quanCoTren == null) {
                            kiemTraTren = false;
                            break;
                        } else {
                            countDuong++;
                            dsQuanCoDaXet.add(quanCoTren);
                        }
                    }
                    if(kiemTraDuoi) {
                        QuanCo quanCoDuoi = getQuanCo(new ToaDo(quanCoDangXet.getToaDo().getX(), quanCoDangXet.getToaDo().getY() + i));
                        if (quanCoDuoi == null) {
                            kiemTraDuoi = false;
                            break;
                        } else {
                            countDuong++;
                            dsQuanCoDaXet.add(quanCoDuoi);
                        }
                    }
                }
                if(countDuong == duong) count++;
                dsQuanCoDaXet.add(quanCoDangXet);
            }
        }
        return count;
    }

    private int tinhTongSoDuongHangNgang(int duong, QuanCo.LoaiQuanCo loaiQuanCo) {
        int count = 0;
        List<QuanCo> dsQuanCoDaXet = new ArrayList<>();
        for(QuanCo quanCoDangXet : dsQuanCo) {
            if(quanCoDangXet.getLoaiQuanCo() == loaiQuanCo) {
                if(dsQuanCoDaXet.contains(quanCoDangXet)) continue;
                int countDuong = 1;
                boolean kiemTraTren = true, kiemTraDuoi = true;
                for(int i = 1; kiemTraTren || kiemTraDuoi; i++) {
                    if(kiemTraTren) {
                        QuanCo quanCoTren = getQuanCo(new ToaDo(quanCoDangXet.getToaDo().getX() - i, quanCoDangXet.getToaDo().getY()));
                        if (quanCoTren == null) {
                            kiemTraTren = false;
                            break;
                        } else {
                            countDuong++;
                            dsQuanCoDaXet.add(quanCoTren);
                        }
                    }
                    if(kiemTraDuoi) {
                        QuanCo quanCoDuoi = getQuanCo(new ToaDo(quanCoDangXet.getToaDo().getX() + i, quanCoDangXet.getToaDo().getY()));
                        if (quanCoDuoi == null) {
                            kiemTraDuoi = false;
                            break;
                        } else {
                            countDuong++;
                            dsQuanCoDaXet.add(quanCoDuoi);
                        }
                    }
                }
                if(countDuong == duong) count++;
                dsQuanCoDaXet.add(quanCoDangXet);
            }
        }
        return count;
    }

    private int tinhTongSoDuongHangCheoChinh(int duong, QuanCo.LoaiQuanCo loaiQuanCo) {
        int count = 0;
        List<QuanCo> dsQuanCoDaXet = new ArrayList<>();
        for(QuanCo quanCoDangXet : dsQuanCo) {
            if(quanCoDangXet.getLoaiQuanCo() == loaiQuanCo) {
                if(dsQuanCoDaXet.contains(quanCoDangXet)) continue;
                int countDuong = 1;
                boolean kiemTraCheoTren = true, kiemTraCheoDuoi = true;
                for(int i = 1; kiemTraCheoTren || kiemTraCheoDuoi; i++) {
                    if(kiemTraCheoTren) {
                        QuanCo quanCoCheoTren = getQuanCo(new ToaDo(quanCoDangXet.getToaDo().getX() + i, quanCoDangXet.getToaDo().getY() - i));
                        if (quanCoCheoTren == null) {
                            kiemTraCheoTren = false;
                            break;
                        } else {
                            countDuong++;
                            dsQuanCoDaXet.add(quanCoCheoTren);
                        }
                    }
                    if(kiemTraCheoDuoi) {
                        QuanCo quanCoCheoDuoi = getQuanCo(new ToaDo(quanCoDangXet.getToaDo().getX() - i, quanCoDangXet.getToaDo().getY() + i));
                        if (quanCoCheoDuoi == null) {
                            kiemTraCheoDuoi = false;
                            break;
                        } else {
                            countDuong++;
                            dsQuanCoDaXet.add(quanCoCheoDuoi);
                        }
                    }
                }
                if(countDuong == duong) count++;
                dsQuanCoDaXet.add(quanCoDangXet);
            }
        }
        return count;
    }

    private int tinhTongSoDuongHangCheoPhu(int duong, QuanCo.LoaiQuanCo loaiQuanCo) {
        int count = 0;
        List<QuanCo> dsQuanCoDaXet = new ArrayList<>();
        for(QuanCo quanCoDangXet : dsQuanCo) {
            if(quanCoDangXet.getLoaiQuanCo() == loaiQuanCo) {
                if(dsQuanCoDaXet.contains(quanCoDangXet)) continue;
                int countDuong = 1;
                boolean kiemTraCheoTren = true, kiemTraCheoDuoi = true;
                for(int i = 1; kiemTraCheoTren || kiemTraCheoDuoi; i++) {
                    if(kiemTraCheoTren) {
                        QuanCo quanCoCheoTren = getQuanCo(new ToaDo(quanCoDangXet.getToaDo().getX() - i, quanCoDangXet.getToaDo().getY() - i));
                        if (quanCoCheoTren == null) {
                            kiemTraCheoTren = false;
                            break;
                        } else {
                            countDuong++;
                            dsQuanCoDaXet.add(quanCoCheoTren);
                        }
                    }
                    if(kiemTraCheoDuoi) {
                        QuanCo quanCoCheoDuoi = getQuanCo(new ToaDo(quanCoDangXet.getToaDo().getX() + i, quanCoDangXet.getToaDo().getY() + i));
                        if (quanCoCheoDuoi == null) {
                            kiemTraCheoDuoi = false;
                            break;
                        } else {
                            countDuong++;
                            dsQuanCoDaXet.add(quanCoCheoDuoi);
                        }
                    }
                }
                if(countDuong == duong) count++;
                dsQuanCoDaXet.add(quanCoDangXet);
            }
        }
        return count;
    }

    public int tinhSuTuongTacGiuaCacQuanCo(QuanCo.LoaiQuanCo loaiQuanCo) {
        int suTuongTac = 0;
        for(QuanCo quanCoDangXet : dsQuanCo) {
            if(quanCoDangXet.getLoaiQuanCo() == loaiQuanCo) {
                for(ToaDo toaDo : quanCoDangXet.getToaDo().getToaDoXungQuanh()) {
                    QuanCo quanCo = getQuanCo(toaDo);
                    if (quanCo != null && quanCo.getLoaiQuanCo() == loaiQuanCo) {
                        suTuongTac += 1;
                    }
                }
            }
        }
        return suTuongTac;
    }

    public int tinhTongSoLanChanQuanCuaDoiThu(QuanCo.LoaiQuanCo loaiQuanCo) {
        int tongSoLanChan = 0;
        Set<QuanCo> quanCoBiChanDaXet = new HashSet<>();
        for(QuanCo quanCoDangXet : dsQuanCo) {
            if(quanCoDangXet.getLoaiQuanCo() == loaiQuanCo) {
                for(ToaDo toaDo : quanCoDangXet.getToaDo().getToaDoXungQuanh()) {
                    QuanCo quanCo = getQuanCo(toaDo);
                    if(quanCo != null && quanCoBiChanDaXet.contains(quanCo)) continue;
                    if(quanCo != null && quanCo.getLoaiQuanCo() != loaiQuanCo) {
                        tongSoLanChan += 1;
                        quanCoBiChanDaXet.add(quanCo);
                    }
                }
            }
        }
        return tongSoLanChan;
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

        public void setCurrentPlayer(Player player) {
             banCo.setCurrentPlayer(player);
        }

        public BanCo build() {
            return banCo;
        }
    }
}
