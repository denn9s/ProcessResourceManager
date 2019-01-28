public class Resource {
    String resourceID;
    int freeUnits;
    int maxUnits;

    public Resource(String rid, Integer units) {
        resourceID = rid;
        freeUnits = units;
        maxUnits = units;
    }
}
