public class Resource {
    String resourceID;
    int freeUnits;
    int maxUnits;

    public Resource(String rid, Integer units) {
        this.resourceID = rid;
        this.freeUnits = units;
        this.maxUnits = units;
    }
}
