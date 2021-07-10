public class RadixSortable {
    private final Integer row;

    public RadixSortable(Integer row) {
        this.row = row;
    }

    public Integer getRow() {
        return row;
    }

    public int hashCode() {
        return row.intValue();
    }

    public boolean equals(Object o) {
        return ((RadixSortable) o ).getRow().equals(getRow());
    }
}
