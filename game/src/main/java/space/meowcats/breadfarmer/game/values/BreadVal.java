package space.meowcats.breadfarmer.game.values;

public class BreadVal {
    private long breads = 0;
    private long breadCost = 10;
    private int breadCount = 1;

    public void addBreads(long count) {
        breads += count;
    }

    public boolean buyBread() {
        if (breads >= breadCost) {
            breads -= breadCost;
            breadCount++;
            breadCost = (long) (breadCost * 1.5);
            return true;
        }
        return false;
    }

    public long getBreads() {
        return breads;
    }

    public long getBreadCost() {
        return breadCost;
    }

    public int getBreadCount() {
        return breadCount;
    }
}
