package gameobjects;

import map.Map;
import game.InfoPanel;
import map.Position;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

import java.util.ArrayList;
import java.util.Iterator;

public class Tower extends GameObjects {

    //Propriedades
    private int shotDamage;

    public static final int PRICE = 40;
    public static final int UPGRADEPRICE = 60;
    private boolean upgraded = false;
    private int range = Map.CELLSIZE*5; // exemplo: tem 3 "cellsizes" de range
    private int centerX;
    private int centerY;
    private Ellipse visualRange;
    private ArrayList<Projectile>bullets;
    private long lastTime;
    int counter = 0;



    //Construtor
    public Tower(Position pos) {
        super(pos, Color.BLACK,"tower.png");
        centerX = (pos.getX()-(2*Map.CELLSIZE));
        centerY = (pos.getY()-(2*Map.CELLSIZE));
       // visualRange = new Ellipse(centerX,centerY,range,range);
      //  visualRange.setColor(Color.WHITE);
      //  visualRange.draw();
        bullets = new ArrayList<>();
        lastTime = 0;
        shotDamage = 4;
    }


    //Métodos
    public synchronized void shoot(ArrayList<Enemy> enemies){
        synchronized (enemies) {
            Enemy enemy = enemyInRange(enemies);

        long currentTime = System.currentTimeMillis();
        int speed = 750;
        synchronized (bullets) {
            if (enemy != null && !enemy.isDead()) {
                if (currentTime - lastTime >= speed) {
                    lastTime = currentTime;
                    bullets.add(new Projectile(this, enemy, shotDamage));
                }
            }
            Iterator<Projectile> iterator = bullets.iterator();
            while (iterator.hasNext()) {
                Projectile projectile = iterator.next();
                projectile.move();
                if (enemy == null) {
                    projectile.getBullet().delete();
                    iterator.remove();
                }
                if (projectile.checkCollision()) {

                    if (enemy != null ) {
                        //System.out.println(projectile.getDamage());
                        enemy.getHit(projectile.getDamage());
                        iterator.remove();
                    }
                }
            }
        }
        }

    }
    public void upgrade(Position position,InfoPanel infoPanel){
        if(this.getPosition().equals(position)){
            upgraded = true;
            infoPanel.setGold(infoPanel.getNrGold()-UPGRADEPRICE);
            shotDamage *= 2;
            System.out.println("teste");
            setPicture("towerUpgraded.png");
        }

    }

    public ArrayList<Projectile> getBullets() {
        return bullets;
    }

    public Enemy enemyInRange(ArrayList<Enemy> enemies)
    {
        for(Enemy enemy: enemies)
        {

            int dx = (getX()+Map.CELLSIZE/2)-(enemy.getX()+Map.CELLSIZE/2);
            int dy = (getY()+Map.CELLSIZE/2)-(enemy.getY()+Map.CELLSIZE/2);
            int dist =(int) Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
            if(dist<(range/2)+Map.CELLSIZE)
            {
                return enemy;
            }
        }
        return  null;
    }

    public int getPRICE() {
        return PRICE;
    }

    public boolean isUpgraded() {
        return upgraded;
    }
}
