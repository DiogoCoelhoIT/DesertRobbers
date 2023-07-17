package gameobjects;

import map.*;

import java.util.ArrayList;

public class NormalEnemy extends Enemy {


    public NormalEnemy(ArrayList<Floor> road,int speed, int health){
        super(road, EnemyTypes.NORMAL.getColor(), EnemyTypes.NORMAL,speed, health+40, 10);
    }

}
