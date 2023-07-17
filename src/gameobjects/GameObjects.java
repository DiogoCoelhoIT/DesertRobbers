package gameobjects;

import map.Position;
import map.Map;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public abstract class GameObjects {

    private Position position;
    private Color color;

    private Rectangle rectangle;
    private String filePath;
    private Picture picture;

    public GameObjects(Position pos, Color color, String filePath){
        this.position = pos;
        this.color = color;

        rectangle = new Rectangle(pos.getX(), pos.getY(), Map.CELLSIZE, Map.CELLSIZE);
        rectangle.setColor(color);
       // rectangle.fill();
        picture = new Picture(pos.getX(),pos.getY(),filePath);
        picture.draw();
    }

    //Getters
    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }



    public Rectangle getRectangle() {
        return rectangle;
    }

    //Setters


    public Position getPosition() {
        return position;
    }

    public Picture getPicture() {
        return picture;
    }

    public synchronized void setPicture(String s) {
        picture.load(s);
    }
}
