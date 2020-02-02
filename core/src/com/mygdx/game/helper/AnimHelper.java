package com.mygdx.game.helper;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimHelper {
    public static TextureRegion[] getFrames(Texture texture, int ROWS, int COLS){
        TextureRegion[] frames = new TextureRegion[ROWS*COLS];
        TextureRegion[][] tmp = TextureRegion.split(texture,texture.getWidth()/COLS,texture.getHeight()/ROWS);
        int index=0;
        for(int i=0; i<ROWS; i++){
            for(int j=0; j<COLS; j++){
                frames[index++] = tmp[i][j];
            }
        }
        return frames;
    }
}
