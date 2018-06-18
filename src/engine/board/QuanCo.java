package engine.board;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuanCo quanCo = (QuanCo) o;
        return Objects.equals(toaDo, quanCo.toaDo) &&
                loaiQuanCo == quanCo.loaiQuanCo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toaDo, loaiQuanCo);
    }

    public enum LoaiQuanCo {
        X,
        O
    }
}
