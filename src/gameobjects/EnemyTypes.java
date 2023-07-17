package gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;

public enum EnemyTypes {

    NORMAL(Color.BLUE);
    // MAIS POR SER ADD



    private Color color;

    EnemyTypes(Color color){
        this.color = color;
    }

    //Getter
    public Color getColor(){
        return this.color;
    }

}
