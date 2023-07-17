package gameobjects;

import map.*;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.util.ArrayList;

public class Enemy extends GameObjects {

    //Propriedades
    private int speed;
    private double health;
    private boolean isDead = false;
    private int enemyPoints;
    private EnemyTypes enemyType;
    private ArrayList<Floor> road;
    private int x;
    private int y;
    private int currentIndex;
    private Rectangle life;
    private boolean enemyWon = false;
    private int imgIndex = 0;
    private double maxHealth;


    //Construtor
    public Enemy(ArrayList<Floor> road, Color color, EnemyTypes enemyType, int speed, int health, int enemyPoints) {
        super(road.get(0).getPosition(), color,"monsterR1.png");
        x = road.get(0).getPosition().getX();
        y = road.get(0).getPosition().getY();
        this.road = road;
        this.enemyType = enemyType;
        this.speed=speed;
        this.speed = 2+((int)(Math.random()*(speed)));
        if(this.speed == 3)
        {
            this.speed = 2;
        }
        this.health = health+(40/speed);
        this.enemyPoints = enemyPoints;
        life = new Rectangle(getX(), getY(), Map.CELLSIZE, Map.CELLSIZE / 6);
        life.setColor(Color.RED);
        life.fill();
        currentIndex = 0;
        maxHealth = this.health;

    }



    //Métodos
    public synchronized void move() {
        if (!isDead) {
            if (currentIndex < road.size()) {
                int nextIndex = currentIndex + 1;
                if (nextIndex < road.size()) {
                    Floor nextFloor = road.get(nextIndex);
                    if (x == nextFloor.getX() && y == nextFloor.getY()) {
                        currentIndex++;
                    }
                    synchronized (getRectangle()) {
                        synchronized (getPicture()) {
                            if (x < nextFloor.getX()) {
                                if (imgIndex < 15) {
                                    setPicture("monsterR1.png");
                                    imgIndex++;
                                } else if (imgIndex < 30) {
                                    setPicture("monsterR2.png");
                                    imgIndex++;
                                }
                                if (imgIndex >= 30) {
                                    imgIndex = 0;
                                }
                                x += speed;
                                getPicture().translate(speed, 0);
                                life.translate(speed, 0);

                            } else if (x > nextFloor.getX()) {
                                if (imgIndex < 15) {
                                    setPicture("monsterL1.png");
                                    imgIndex++;
                                } else if (imgIndex < 30) {
                                    setPicture("monsterL2.png");
                                    imgIndex++;
                                }
                                if (imgIndex >= 30) {
                                    imgIndex = 0;
                                }
                                x -= speed;
                                getPicture().translate(-speed, 0);
                                life.translate(-speed, 0);
                            } else if (y < nextFloor.getY()) {
                                if(imgIndex < 15){
                                setPicture("frontEnemy1.png");
                                imgIndex++;
                                }
                                else if(imgIndex <30) {
                                    setPicture("frontEnemy2.png");
                                    imgIndex++;
                                } if(imgIndex >= 30 ){
                                    imgIndex = 0;
                                }

                                y += speed;
                                getPicture().translate(0, speed);
                                life.translate(0, speed);
                            } else if (y > nextFloor.getY()) {
                                y -= speed;
                                getPicture().translate(0, -speed);
                                life.translate(0, -speed);
                            }
                        }
                        if (currentIndex == road.size() - 1 && !enemyWon) {
                            System.out.println("Road End");
                            life.delete();
                            getPicture().delete();
                            enemyWon = true;
                            return;
                        }
                    }
                }
            }
        }

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public synchronized void getHit(int damage) {
        {
            double scaleDamage = ((double)damage/(double)maxHealth) * 40;

            double halfDamage = scaleDamage / 2;
            health -= damage;
            life.translate(-halfDamage, 0);
            life.grow(-halfDamage, 0);
            if (health <= 0) {
                isDead = true;
                getPicture().delete();
                getRectangle().delete();
                life.delete();
                System.out.println("Enemy dead");
            }
        }

    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isEnemyWon() {
        return enemyWon;
    }
    public int getEnemyPoints(){
        return enemyPoints;
    }
}
