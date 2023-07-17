package map;

import org.academiadecodigo.simplegraphics.graphics.Color;

public enum FloorTypes{
    ROAD_TILE(Color.ORANGE, "road_tile_scaled.png"), // em que se anda
    CONSTRUCT_TILE(Color.LIGHT_GRAY, "cement_tile_scaled.png"),
    BLOCK_TILE(Color.GREEN, "block_tile_scaled.png");


    private Color color;
    private String imagePath;

    FloorTypes(Color color, String imagePath){
        this.color = color;
        this.imagePath = imagePath;
    }

    public Color getColor(){
        return color;
    }

    public String getImagePath() {
        return imagePath;
    }
}

