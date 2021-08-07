package ha.hoclaptrinhweb.sort;

public class Sorter {
    private String sortBy;
    private String sortName;

    public Sorter(String sortName, String sortBy) {
        this.sortName = sortName;
        this.sortBy = sortBy;
    }

    public String getSortName() {
        return this.sortName;
    }

    public String getSortBy() {
        return this.sortBy;
    }

}
