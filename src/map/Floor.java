package map;//import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Floor {

    private FloorTypes floorType;
    private Position position;
    private Rectangle rectangle;
    private Picture picture;

    public Floor(FloorTypes floorType, Position position) {
        this.floorType = floorType;
        this.position = position;
        paintFloor();
    }


    public int getX()
    {
        return position.getX();
    }
    public int getY()
    {
        return position.getY();
    }
    //Getters
    public FloorTypes getFloorType(){
        return floorType;
    }

    public Position getPosition(){
        return position;
    }

    //Setters

    //Métodos
    public void paintFloor(){
        rectangle = new Rectangle(position.getX(), position.getY(), Map.CELLSIZE, Map.CELLSIZE);
        rectangle.setColor(floorType.getColor());
        rectangle.fill();

        picture = new Picture(position.getX(), position.getY(), floorType.getImagePath());
        picture.draw();
        picture.grow(-Map.CELLSIZE, -Map.CELLSIZE); //works. don't know why. but works.

    }


}

