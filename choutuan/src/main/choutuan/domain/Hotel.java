package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Hotel implements Serializable {
    public Hotel() {
    }
    private ArrayList<HotelHome> arrayList=new ArrayList<>();

    public Hotel(ArrayList<HotelHome> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<HotelHome> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<HotelHome> arrayList) {
        this.arrayList = arrayList;
    }
    public void add(HotelHome hotelHome){
        arrayList.add(hotelHome);
    }
}
