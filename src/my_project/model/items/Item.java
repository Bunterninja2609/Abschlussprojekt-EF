package my_project.model.items;

public abstract class Item {

    protected int amount;
    protected String name;

    public Item() {
        amount = 1;
    }

    public String getName() {
        return name;
    }

    public void changeAmount(int amount) {
        this.amount += amount;
    }
    public int getAmount() {
        return amount;
    }
}
