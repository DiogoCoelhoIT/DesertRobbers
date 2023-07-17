package game;

import game.EnemySpawner;
import gameobjects.Enemy;
import gameobjects.Tower;

import map.Button;
import map.Floor;
import map.Position;
import map.Map;


import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Game implements MouseListener {

    private Map map;
    public static final int BORDERS_Y = 24;
    public static final int BORDERS_X = 0;
    private Picture background;
    private Picture title;
    private Button start;
    private boolean gameOver;

    private ArrayList<Tower> towers = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();

    private EnemySpawner spawner;
    private InfoPanel infoPanel;




    public Game() {
        introduction();
        gameOver = false;
    }

    //Cria o menu inicial, ainda vai ser editado
    public void introduction() {
        background = new Picture(Map.PADDING,Map.PADDING,"Startscreen.png");
        background.draw();
        title = new Picture(-40,100,"Title.png");
        title.grow(-150,-20);
        title.draw();

        start = new Button("startbutton.png",(Map.WIDTH/2)-100,Map.HEIGHT-100);
        start.drawButton();
        Canvas.getInstance().addMouseListener(this);
    }

    public void drawMap() throws FileNotFoundException {
        background.delete();
        title.delete();
        map = new Map();
        map.showMap();

        infoPanel = new InfoPanel();
        infoPanel.displayUI();

    }

    public void gameOver()
    {
        gameOver = true;
        Picture picture = new Picture(Map.PADDING,Map.PADDING,"gameOver.png");
        picture.draw();

    }

    public void startGame() throws FileNotFoundException {
        drawMap();

        synchronized (enemies) {
            spawner = new EnemySpawner(map, enemies, 5);
        }
        spawner.startSpawn(gameOver);


        Thread enemyThread = new Thread(this::enemyUpdate);
        Thread towerThread = new Thread(this::towerUpdate);
        enemyThread.start();
        towerThread.start();
    }

    private void enemyUpdate() {
        while (!gameOver) {

                synchronized (enemies) {
                    infoPanel.setWaveInfo(spawner.getNrEnemies(),spawner.getMaxEnemies(),spawner.getWaveCounter());
                    Iterator<Enemy> iterator = enemies.iterator();
                    while (iterator.hasNext()) {
                        Enemy enemy = iterator.next();
                        enemy.move();
                        if(enemy.isEnemyWon()){
                            infoPanel.looseHealth();
                            iterator.remove();
                            if(infoPanel.isDead())
                            {
                                gameOver();
                            }
                        }

                        if (enemy.isDead()) {
                                infoPanel.setScore(infoPanel.getNrScore()+enemy.getEnemyPoints());
                                infoPanel.setGold(infoPanel.getNrGold()+enemy.getEnemyPoints());

                            iterator.remove();
                        }
                    }
                }
            try {
                Thread.sleep(13);
            } catch (Exception e) {
                throw new IllegalThreadStateException();
            }
        }
    }

    private void towerUpdate() {
        while (!gameOver) {
            synchronized (towers) {
                for (Tower tower : towers) {
                    tower.shoot(enemies);
                }
            }
            try {
                    Thread.sleep(3);
                } catch (Exception e) {
                    throw new IllegalThreadStateException();
                }
            }
        }

    //add Nested array with block tiles
    //alterar



    // IMCOMPLETOOOOOOOOOOOO
    public  synchronized Tower generateTower(Position position, ArrayList<Floor> floorsArray) {
        synchronized (towers) {
            Tower tower = null;
            boolean canConstruct = true;
            for (Floor floor : floorsArray) {
                if (floor != null && position.isEqual(floor.getPosition())) {
                    if (towers.isEmpty()) {
                        tower = new Tower(position);
                        towers.add(tower);
                        infoPanel.setGold(infoPanel.getNrGold() - Tower.PRICE);
                    } else {
                        for (Tower t : towers) {
                            if (t.getPosition().equals(position) || infoPanel.getNrGold()< Tower.PRICE) {
                                canConstruct = false;
                            }
                        }
                            if (canConstruct) {

                                tower = new Tower(position);
                                towers.add(tower);
                                infoPanel.setGold(infoPanel.getNrGold() - Tower.PRICE);
                            }

                        }
                    }


            }
            return tower;
        }
    }




    // IMCOMPLETOOOOOOOOOOOO
    //Diogo: mudei o BORDERS_X e Y, porque as coordenadas nao estavam certas
    //mudei a funcao map.xToColumn e map.yToRow que estavam com a formula errada
    //movi as funcoes para mousePressedd Funciona melhor!
    @Override
    public void mouseClicked(MouseEvent event) {
    }


    @Override
    public void mousePressed(MouseEvent event) {
        int mouseX = event.getX() - BORDERS_X;
        int mouseY = event.getY() - BORDERS_Y;
        //System.out.println(mouseX + " " + mouseY);


        if(start.isClicked(mouseX,mouseY)) {
                start.buttonDelete();
            try {
                startGame();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        if(map != null) {
            int clickedX = map.xToColumn(mouseX-Map.PADDING);
            int clickedY = map.yToRow(mouseY-Map.PADDING);

            //System.out.println((mouseX-Map.PADDING) + " "+ (mouseY-Map.PADDING));
            //System.out.println("x- " + clickedX + " " + "y- " + clickedY);

            if(Tower.UPGRADEPRICE <= infoPanel.getNrGold()){
                for(Tower tower:towers){
                    if(!tower.isUpgraded()){
                    tower.upgrade(new Position(clickedX, clickedY),infoPanel);
                    }

                }
            }generateTower(new Position(clickedX, clickedY), map.getConstructsArray());
        }

    }

    public void getTileType(Position pos, Floor[] floorsArray){
        for(Floor floor : floorsArray){
            if(floor != null && pos.isEqual(floor.getPosition())){
                System.out.println(floor.getFloorType());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
