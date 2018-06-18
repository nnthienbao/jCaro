package engine.board;

public class QuanCo {
    private ToaDo toaDo;
    private LoaiQuanCo loaiQuanCo;

    public QuanCo(ToaDo toaDo, LoaiQuanCo loaiQuanCo) {
        this.toaDo = toaDo;
        this.loaiQuanCo = loaiQuanCo;
    }

    public ToaDo getToaDo() {
        return toaDo;
    }

    public void setToaDo(ToaDo toaDo) {
        this.toaDo = toaDo;
    }

    public LoaiQuanCo getLoaiQuanCo() {
        return loaiQuanCo;
    }

    public void setLoaiQuanCo(LoaiQuanCo loaiQuanCo) {
        this.loaiQuanCo = loaiQuanCo;
    }

    public enum LoaiQuanCo {
        X,
        O
    }
}
