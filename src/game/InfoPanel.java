package game;

import map.Map;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class InfoPanel {
    private Picture[] health;
    private Text score;
    private Picture scoreIcon;
    private Text gold;

    private Text currentWave;
    private boolean dead = false;

    private Rectangle rectangle;
    private int maxHealth;
    private int nrScore = 0;
    private int nrGold = 100;
    private Text waveInfo;

    private Picture goldIcon;

    private Picture enemyIcon;


    public void displayUI(){
        maxHealth = 3;
        rectangle = new Rectangle(Map.PADDING,Map.HEIGHT+Map.PADDING,Map.WIDTH,Map.CELLSIZE);
        rectangle.draw();
        health = new Picture[maxHealth];
        for(int i =0; i<health.length; i++) {
           health[i] = new Picture((Map.PADDING + 2)+(i*Map.CELLSIZE),(Map.HEIGHT + 2)+Map.PADDING,"heart_scaled.png");
           health[i].draw();
        }

        score = new Text(Map.WIDTH - 45,(Map.HEIGHT + 14)+Map.PADDING," " + nrScore);
        score.grow(15, 10);
        scoreIcon = new Picture(Map.WIDTH - 160, (Map.HEIGHT+Map.PADDING), "score_scaled.png");
        scoreIcon.draw();
        gold = new Text(((Map.WIDTH/2) + 180),(Map.HEIGHT + 14)+Map.PADDING," " + nrGold);
        gold.grow(15, 10);
        goldIcon = new Picture(((Map.WIDTH/2) +130),(Map.HEIGHT + 2)+Map.PADDING, "gold_scaled.png");
        goldIcon.draw();


        score.draw();
        gold.draw();
        waveInfo = new Text((Map.WIDTH/2) - 100,Map.HEIGHT + 14 + Map.PADDING,"0/5  Wave: 1");
        waveInfo.grow(75,10);
        waveInfo.draw();

        enemyIcon = new Picture(Map.WIDTH/2 - 220,Map.HEIGHT + Map.PADDING,"enemieIcon.png");
        enemyIcon.draw();


    }



    public int getNrGold() {
        return nrGold;
    }

    public int getNrScore() {
        return nrScore;
    }

    public void setScore(int x)
    {
        nrScore = x;
        score.setText("" + x);
    }
    public void setGold(int x)
    {
        nrGold = x;
        gold.setText(" " + x);
    }
    public synchronized void looseHealth()
    {
        if(maxHealth>=1) {
            maxHealth--;
            health[maxHealth].delete();
        }if(maxHealth == 0)
    {
        dead = true;
    }
    }

    public boolean isDead() {
        return dead;
    }

    public synchronized void setWaveInfo(int currentEnemy,int MaxEnemies,int currentWave) {
        waveInfo.setText(currentEnemy+"/"+ MaxEnemies+"  Wave: " +currentWave);
    }
}

