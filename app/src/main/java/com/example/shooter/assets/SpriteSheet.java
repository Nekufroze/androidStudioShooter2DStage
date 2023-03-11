package com.example.shooter.assets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.shooter.R;

public class SpriteSheet {
    private final Bitmap bitmap;

    public SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.male01_spritesheet, bitmapOptions);

    }

    public Sprite getJoueurSprite(){
        return new Sprite(this, new Rect(0,0,32,32));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
