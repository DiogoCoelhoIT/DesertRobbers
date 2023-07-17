package map;

public class Position {

    private int x;
    private int col;
    private int y;
    private int row;


    //Contrutor
    public Position(int x, int y){
        this.col=x;
        this.row=y;
        this.x = x* Map.CELLSIZE+ Map.PADDING;
        this.y = y* Map.CELLSIZE+ Map.PADDING;
    }

    //Métodos

    public boolean isEqual(Position pos){
        return x == pos.getX() && y == pos.getY();
    }

    public int getCol(){
        return col;
    }

    public int getRow() {
        return row;
    }

    //Getters
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean equals(Position pos)
    {
        if(x == pos.getX() && y == pos.getY())
        {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return x+" "+y;
    }
}
