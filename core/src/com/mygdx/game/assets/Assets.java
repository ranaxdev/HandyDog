package com.mygdx.game.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public AssetManager manager = new AssetManager();
    //Dog assets
    public static final AssetDescriptor<Texture> dog_walkdown =
            new AssetDescriptor<Texture>("dog/walkdown.png",Texture.class);
    public static final AssetDescriptor<Texture> dog_walkup =
            new AssetDescriptor<Texture>("dog/walkup.png",Texture.class);
    public static final AssetDescriptor<Texture> dog_walkleft =
            new AssetDescriptor<Texture>("dog/walkleft.png",Texture.class);
    public static final AssetDescriptor<Texture> dog_walkright =
            new AssetDescriptor<Texture>("dog/walkright.png",Texture.class);
    public static final AssetDescriptor<Texture> bigdog =
            new AssetDescriptor<Texture>("dog/bigdog.png",Texture.class);
    //House assets
    public static final AssetDescriptor<Texture> house_start =
            new AssetDescriptor<Texture>("house/house_start.png",Texture.class);
    public static final AssetDescriptor<Texture> house_complete =
            new AssetDescriptor<Texture>("house/house_complete.png",Texture.class);
    public static final AssetDescriptor<Texture> house_middle =
            new AssetDescriptor<Texture>("house/house_middle.png",Texture.class);
    public static final AssetDescriptor<Texture> house_almost =
            new AssetDescriptor<Texture>("house/house_almost.png",Texture.class);
    //Repair assets
    public static final AssetDescriptor<Texture> plank =
            new AssetDescriptor<Texture>("repair/plank.png",Texture.class);
    public static final AssetDescriptor<Texture> hammer =
            new AssetDescriptor<Texture>("repair/hammer.png",Texture.class);
    public static final AssetDescriptor<Texture> bighammer =
            new AssetDescriptor<Texture>("repair/bighammer.png",Texture.class);
    public static final AssetDescriptor<Texture> wall =
            new AssetDescriptor<Texture>("repair/wall.png",Texture.class);
    public static final AssetDescriptor<Texture> brush =
            new AssetDescriptor<Texture>("repair/brush.png",Texture.class);
    public static final AssetDescriptor<Texture> picture =
            new AssetDescriptor<Texture>("repair/picture.png",Texture.class);

    //Collectibles
    public static final AssetDescriptor<Texture> wood =
            new AssetDescriptor<Texture>("collectibles/wood.png",Texture.class);
    public static final AssetDescriptor<Texture> flower =
            new AssetDescriptor<Texture>("collectibles/flower.png",Texture.class);
    public static final AssetDescriptor<Texture> food =
            new AssetDescriptor<Texture>("collectibles/food.png",Texture.class);

    //HUD
    public static final AssetDescriptor<Texture> hudWood =
            new AssetDescriptor<Texture>("HUD/hudWood.png",Texture.class);
    public static final AssetDescriptor<Texture> hudFlower =
            new AssetDescriptor<Texture>("HUD/hudFlower.png",Texture.class);
    public static final AssetDescriptor<Texture> hudFood =
            new AssetDescriptor<Texture>("HUD/hudFood.png",Texture.class);
    public static final AssetDescriptor<Texture> background =
            new AssetDescriptor<Texture>("background.png",Texture.class);
    //special
    public static final AssetDescriptor<Texture> sad =
            new AssetDescriptor<Texture>("dog/sad2.png",Texture.class);
    public static final AssetDescriptor<Texture> memory1 =
            new AssetDescriptor<Texture>("memories/memory1.png",Texture.class);
    public static final AssetDescriptor<Texture> memory2 =
            new AssetDescriptor<Texture>("memories/memory2.png",Texture.class);
    public static final AssetDescriptor<Texture> memory3 =
            new AssetDescriptor<Texture>("memories/memory3.png",Texture.class);
    public static final AssetDescriptor<Texture> memory4 =
            new AssetDescriptor<Texture>("memories/memory4.png",Texture.class);
    //Sund
    public static final AssetDescriptor<Music> gamemusic1 =
            new AssetDescriptor<Music>("Sound/gamemusic.mp3",Music.class);
    public static final AssetDescriptor<Music> gamemusic2 =
            new AssetDescriptor<Music>("Sound/gamemusic2.mp3",Music.class);
    public static final AssetDescriptor<Sound> footstep =
            new AssetDescriptor<Sound>("Sound/footstep.wav",Sound.class);
    public static final AssetDescriptor<Sound> hammersound =
            new AssetDescriptor<Sound>("Sound/hammer.wav",Sound.class);
    public static final AssetDescriptor<Sound> woodsound =
            new AssetDescriptor<Sound>("Sound/wood.wav",Sound.class);
    public static final AssetDescriptor<Sound> pluck =
            new AssetDescriptor<Sound>("Sound/pluck.wav",Sound.class);
    public static final AssetDescriptor<Sound> brushsound =
            new AssetDescriptor<Sound>("Sound/brush.wav",Sound.class);
    public static final AssetDescriptor<Sound> eat =
            new AssetDescriptor<Sound>("Sound/eat.wav",Sound.class);

    public void load(){
        manager.load(dog_walkdown);
        manager.load(dog_walkup);
        manager.load(dog_walkleft);
        manager.load(dog_walkright);
        manager.load(wood);
        manager.load(house_start);
        manager.load(house_complete);
        manager.load(house_middle);
        manager.load(plank);
        manager.load(hammer);
        manager.load(bighammer);
        manager.load(bigdog);
        manager.load(flower);
        manager.load(wall);
        manager.load(brush);
        manager.load(house_almost);
        manager.load(food);
        manager.load(picture);
        manager.load(hudWood);
        manager.load(hudFlower);
        manager.load(hudFood);
        manager.load(background);
        manager.load(gamemusic1);
        manager.load(gamemusic2);
        manager.load(sad);
        manager.load(memory1);
        manager.load(memory2);
        manager.load(memory4);
        manager.load(footstep);
        manager.load(woodsound);
        manager.load(hammersound);
        manager.load(pluck);
        manager.load(brushsound);
        manager.load(memory3);
        manager.load(eat);
    }
    public void dispose(){
        manager.dispose();
    }
}
