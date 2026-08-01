package space.meowcats.breadfarmer.game.values;

public class BreadVal {
    private int breads = 0;
    private int breadCost = 10;
    private int breadMultiplier = 1;

    public void addBreads(int count) {
        breads += count;
    }

    public void removeBreads(int count) {
        breads -= count;
    }

    public void modMultiplier(int count) {
        breadMultiplier += count;
    }

    public void modCost(int count) {
        breadCost += count;
    }

    public int getBreads() {
        return breads;
    }

    public int getBreadCost() {
        return breadCost;
    }

    public int getBreadMultiplier() {
        return breadMultiplier;
    }
}
