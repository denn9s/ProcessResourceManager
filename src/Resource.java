public class Resource {
    String resourceID;
    int freeUnits;
    int maxUnits;

    public Resource(String rid, Integer units) {
        this.resourceID = rid;
        this.freeUnits = units;
        this.maxUnits = units;
    }

    public int getFreeUnits() {
        return this.freeUnits;
    }

    public int getMaxUnits() {
        return this.maxUnits;
    }
}
