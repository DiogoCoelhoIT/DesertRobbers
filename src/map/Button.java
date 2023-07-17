package map;

import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Button {

    private String filepath;
    private int x,y;
    private boolean isPressed = false;
    private Picture picture;



    public Button(String filepath,int x,int y) {
        this.filepath = filepath;
        this.x = x;
        this.y = y;
        picture = new Picture(x,y,filepath);
        picture.grow(100,20);

    }





    public void drawButton()
    {
        picture.draw();
    }

    public boolean isClicked(int mouseX, int mouseY) {
        if(((mouseX > x ) && (mouseX < picture.getMaxX())
                && (mouseY > y) && (mouseY< picture.getMaxY()))&&!isPressed)
        {
            isPressed = true;
            return true;
        }
        return false;
    }

    public void buttonDelete()
    {
        picture.delete();
    }
    public void notPressed()
    {
        isPressed = false;
    }
}
