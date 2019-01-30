public class ResourceList {
    Resource firstResource;
    Resource secondResource;
    Resource thirdResource;
    Resource fourthResource;

    public ResourceList() {
        firstResource = new Resource("R1", 1);
        secondResource = new Resource("R2", 2);
        thirdResource = new Resource("R3", 3);
        fourthResource = new Resource("R4", 4);
    }

    public Resource getResource(String rid) {
        if (rid.equals("R1")) {
            return firstResource;
        } else if (rid.equals("R2")) {
            return secondResource;
        } else if (rid.equals("R3")) {
            return thirdResource;
        } else if (rid.equals("R4")) {
            return fourthResource;
        } else {
            return null;
        }
    }
}