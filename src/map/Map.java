package map;

import org.academiadecodigo.simplegraphics.graphics.Line;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {

    //Propriedades
    public static final int WIDTH = 880;
    public static final int HEIGHT = 600;

    public static final int COLS = 22;
    public static final int ROWS = 15;
    public static final int CELLSIZE = 40;

    public static final int PADDING = 10; // margem do lado direito e em baixo já têm 10px...

    private Line line;
    private Picture window;

    private ArrayList<Floor> roadsArray;
    private ArrayList<Floor> constructsArray;
    private ArrayList<Picture> pictures;

    //Construtor
    public Map() throws FileNotFoundException {
        window = new Picture(PADDING,PADDING,"background.png");
        window.draw();
        drawMap();

        //window = new Rectangle(PADDING, PADDING, WIDTH, HEIGHT);
       // window.setColor(Color.GREEN);
       // window.fill();

    }

    public void drawMap()  {
        Scanner sc = new Scanner(getClass().getClassLoader().getResourceAsStream("map2.txt"));
        int line = 0;
        int counter =0;
        Floor floor;
        roadsArray = new ArrayList<>();
        constructsArray = new ArrayList<>();
        pictures = new ArrayList<>();
        while (sc.hasNext())
        {
            String tiles = sc.nextLine();
            String[] tile = tiles.split("");
            if(line == 0 || (counter>2 && counter<=4))
            {
                for(int i = 0; i<tile.length; i++)
                {
                    String currentTile = tile[i];
                    switch (currentTile)
                    {
                        case "0":
                            break;
                        case "1":
                            roadsArray.add(new Floor(FloorTypes.ROAD_TILE, new Position(i,line)));
                            break;
                        case "2":
                            constructsArray.add(new Floor(FloorTypes.CONSTRUCT_TILE, new Position(i, line)));
                            break;
                        case "3":
                            pictures.add(new Picture(CELLSIZE*i,CELLSIZE*line,"cactus.png"));
                            break;
                        case "4":
                            pictures.add(new Picture(CELLSIZE*i,CELLSIZE*line,"goldchest.png"));

                            break;



                    }
                }
                line++;
                counter++;
                if(counter == 5)
                {
                    counter = 1;
                }

            }else if(counter<=2){
                for (int i = tile.length - 1; i >= 0; i--) {
                    String currentTile = tile[i];
                    switch (currentTile) {
                        case "0":
                            break;
                        case "1":
                            roadsArray.add(new Floor(FloorTypes.ROAD_TILE, new Position(i, line)));
                            break;
                        case "2":
                            constructsArray.add(new Floor(FloorTypes.CONSTRUCT_TILE, new Position(i, line)));
                            break;
                        case "3":
                            pictures.add(new Picture(CELLSIZE*i,CELLSIZE*line,"cactus.png"));
                            break;
                        case "4":
                            pictures.add(new Picture(CELLSIZE*i,CELLSIZE*line,"goldchest.png"));
                            break;
                    }

                }  line++;
                counter++;
            }



        }

    }

    //Métodos

    public void showMap(){
        for (Floor floor:roadsArray)
        {
            floor.paintFloor();
        }
        for (Floor floor:constructsArray)
        {
            floor.paintFloor();
        }
        for(Picture picture: pictures)
        {
            picture.draw();
        }

    }

    public int columnToX(int col){
        return (col*CELLSIZE) + PADDING;
    }

    // possivelmente: public int XtoColumn(int x)
    public int xToColumn(int x){

        if(x <0)
        {
            return -1;
        }
        return (x)/CELLSIZE;
    }

    public int rowToY(int row){
        return (row*CELLSIZE) + PADDING;
    }

    // possivelmente: public int YtoColumn(int y)
    public int yToRow(int y){
        if(y<0)
        {
            return -1;
        }
        return (y)/CELLSIZE;
    }








    public ArrayList<Floor> getConstructsArray() {
        return constructsArray;
    }



    public ArrayList<Floor> getRoadsArray() {
        return roadsArray;
    }
}
