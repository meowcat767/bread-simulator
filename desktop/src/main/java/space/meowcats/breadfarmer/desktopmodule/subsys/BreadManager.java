package space.meowcats.breadfarmer.desktopmodule.subsys;

public class BreadManager {
    private int breads = 0;
    private int breadCost = 10;

    private void calculateBreadCost() {
        if (breads > 0) {
            breadCost = breads * 10;
        }
    }
}
