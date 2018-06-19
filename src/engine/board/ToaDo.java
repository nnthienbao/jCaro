package engine.board;

import java.util.*;

public class ToaDo {
    private int x;
    private int y;

    public ToaDo() {}
    public ToaDo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Collection<ToaDo> getToaDoXungQuanh() {
        List<ToaDo> dsToaDo = new ArrayList<>();
        dsToaDo.add(new ToaDo(x-1, y-1));
        dsToaDo.add(new ToaDo(x, y-1));
        dsToaDo.add(new ToaDo(x+1, y-1));
        dsToaDo.add(new ToaDo(x+1, y));
        dsToaDo.add(new ToaDo(x+1, y+1));
        dsToaDo.add(new ToaDo(x, y+1));
        dsToaDo.add(new ToaDo(x-1, y+1));
        dsToaDo.add(new ToaDo(x-1, y));

        return Collections.unmodifiableCollection(dsToaDo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToaDo toaDo = (ToaDo) o;
        return x == toaDo.x &&
                y == toaDo.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public static double tinhKhoangCach(ToaDo toaDoA, ToaDo toaDoB) {
        return Math.sqrt(Math.pow(toaDoA.getX() - toaDoB.getX(), 2) + Math.pow(toaDoA.getY() - toaDoB.getY(), 2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
