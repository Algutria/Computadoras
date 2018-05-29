package com.computadoras.computadoras;

import java.util.ArrayList;
import java.util.Random;

class Methods {
    public static int randomImage(ArrayList<Integer> images){
        int selectedImage;
        Random r = new Random();
        selectedImage = r.nextInt(images.size());
        return images.get(selectedImage);
    }
}
