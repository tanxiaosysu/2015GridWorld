public class OccupantInCol {
    private Object occupant;
    private int col;

    public OccupantInCol(Object obj, int colnum) {
        occupant = obj;
        col = colnum;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int colnum) {
        col = colnum;
    }

    public Object getOccupant() {
        return occupant;
    }

    public void setOccupant(Object obj) {
        occupant = obj;
    }
}
