package com.mygdx.game.helper;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class MapHelper {
    public static Array<Rectangle> getMapCollisionBoundaries(MapObjects objects){
        Array<Rectangle> boundaries = new Array<>();
        for(MapObject m: objects){
            RectangleMapObject tempRect = (RectangleMapObject) m;
            Rectangle bounds = tempRect.getRectangle();
            boundaries.add(bounds);
        }
        return boundaries;
    }
}
