package gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

public class Projectile {


    private Ellipse bullet;
    private int x;
    private int y;
    private Tower tower;
    private Enemy enemy;
    private int enemyX;
    private int enemyY;
    private int damage;
    private boolean hit = false;


    public Projectile(Tower tower, Enemy enemy, int damage)
    {
        x = tower.getX()+(tower.getRectangle().getWidth()/2);
        y = tower.getY()+(tower.getRectangle().getHeight()/2);

        this.tower = tower;
        this.enemy = enemy;
        this.damage = damage+ (int)(Math.random()*damage);
        enemyX = enemy.getX();
        enemyY = enemy.getY();
        bullet = new Ellipse(x, y, 12,12);
        bullet.setColor(Color.RED);
        bullet.fill();


    }

    public int getDamage() {
        return damage;
    }

    public synchronized void move() {
        enemyX = enemy.getX();
        enemyY = enemy.getY();
        synchronized (enemy) {
            {
                if (!isHit()) {
                    if (x < enemyX) {
                        x += 2;
                        bullet.translate(2, 0);
                    }
                    if (x > enemyX) {
                        x -= 2;
                        bullet.translate(-2, 0);
                    }
                    if (y < enemyY) {
                        y += 2;
                        bullet.translate(0, 2);
                    }
                    if (y > enemyY) {
                        y -= 2;
                        bullet.translate(0, -2);
                    }

                }
            }
        }
    }

    public Ellipse getBullet() {
        return bullet;
    }

    public boolean isHit() {
        return hit;
    }
    public boolean checkCollision()
    {
        if (x == enemy.getX() && y == enemy.getY() && !hit) {
            bullet.delete();
            hit = true;
            return true;
        }
        return false;
    }
}
