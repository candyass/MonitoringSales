package com.sps.monitoringsales.database.entity;

import android.arch.persistence.room.TypeConverter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.BitmapCompat;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.nio.Buffer;

/**
 * Created by sigit on 21/04/2018.
 */

public class BitmapConverter {

    @TypeConverter
    public static Bitmap byteArratToBitmap(byte[] bytes) {
        Bitmap bitmap = null;
        if(bytes != null) {
            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        return bitmap;
    }

    @TypeConverter
    public static byte[] BitmapToByteArray(Bitmap bitmap) {
        byte[] bytes = null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if(bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            bytes = stream.toByteArray();
        }
        return bytes;
    }
}
