package game;

import gameobjects.Enemy;
import gameobjects.NormalEnemy;
import map.Map;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class EnemySpawner {
    private Map map;
    private ArrayList<Enemy> enemies;
    private int nrEnemies;

    private int maxNrEnemies;

    private  int waveCounter=1;

    private boolean forceSpawn = false;
    Timer timer;
    private int delay = 5000;
        public EnemySpawner(Map map, ArrayList<Enemy> enemies, int nrEnemies)
        {
            this.enemies = enemies;
            this.map  = map;
            this.nrEnemies = 0;
            this.maxNrEnemies = nrEnemies;
        }

    public void startSpawn(boolean gameOver)
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (gameOver)
                {
                    timer.cancel();
                }
                    spawn();
            }
        },0,delay);
    }
    public void spawn()
    {
        synchronized (enemies) {
            if(nrEnemies==maxNrEnemies && enemies.isEmpty()) {
                nextWave();
            }
            if(nrEnemies<maxNrEnemies) {
                Enemy enemy = new NormalEnemy(map.getRoadsArray(),4,waveCounter * 10);
                enemies.add(enemy);
                nrEnemies++;
            }
        }

    }
    public void nextWave()
    {
        if(nrEnemies ==maxNrEnemies) {
            System.out.println("Next Wave");
            nrEnemies = 0;
            waveCounter ++;
            maxNrEnemies++;
        }
    }

    public int getWaveCounter() {
        return waveCounter;
    }

    public int getNrEnemies(){
            return nrEnemies;
    }
    public int getMaxEnemies(){
            return maxNrEnemies;
    }
}
