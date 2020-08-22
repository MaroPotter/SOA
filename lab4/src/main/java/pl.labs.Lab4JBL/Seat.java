package pl.labs.Lab4JBL;

public class Seat {
    public Seat(int row,int column)
    {
        this.row = row; this.column = column;
    }
    public Seat(int row,int column,int cost)
    {
        this.row = row; this.column = column; this.cost = cost;
    }
    public int row;
    public int column;
    public boolean booked;
    public void book(){ booked = true; }

    private int cost = 10;

    public int getCost() {
        return cost;
    }
}
