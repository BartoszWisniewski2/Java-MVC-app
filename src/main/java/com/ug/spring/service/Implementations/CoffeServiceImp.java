package com.ug.spring.service.Implementations;

import com.ug.spring.domain.Coffe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeServiceImp implements com.ug.spring.service.Interfaces.CoffeService {
    public CoffeServiceImp() {addStartingData();}

    private static List<Coffe> coffes = new ArrayList<>();
    private static long coffesId=0;

    public Iterable<Coffe> getCoffes() {return coffes;}

    public Coffe delete(long productId){
        int check = -1;
        Coffe coffe = null;
        for (int i=0; i< coffes.size();i++){
            if(coffes.get(i).getCoffeId() == productId){
                coffe = coffes.get(i);
                check=i;
                break;
            }
        }
        if(check>-1){
            coffes.remove(check);
            return coffe;
        }
        return coffe;
    }

    public void save(Coffe coffe){
        int check = -1;
        for (int i=0; i< coffes.size();i++){
            if(coffes.get(i).getCoffeId() == coffe.getCoffeId()){
                coffes.set((int)coffe.getCoffeId(), coffe);
            }
        }
    }

    public void add (Coffe coffe){
        coffe.setCoffeId(coffesId);
        coffesId++;
        coffes.add(coffe);
    }

    public void addStartingData(){
        coffes.add(new Coffe(0, "Senseo mild", 12.22, 10, "Senseo", 1));
        coffes.add(new Coffe(1, "Senseo dark", 13.22, 30, "Senseo", 2));
        coffes.add(new Coffe(2, "Senseo regular", 11.22, 79, "Senseo", 6));
        coffes.add(new Coffe(3, "DALLMAYR PRODOMO 500g", 17.98, 9, "Mielona", 75));
        coffes.add(new Coffe(4, "Kawa ziarnista LAVAZZA CREMA E AROMA BLUE 1 kg", 46.90, 37, "Ziarnista", 44));
        coffes.add(new Coffe(5, "Kawa ziarnista LAVAZZA CREMA E AROMA 1 kg", 41.90, 32, "Ziarnista", 88));
        coffes.add(new Coffe(6, "Dallmayr Crema d'oro 1kg", 38.99, 68, "Ziarnista", 91));
        coffes.add(new Coffe(7, "Kapsułki TASSIMO Jacobs Morning Cafe XL 21 szt", 21.90, 30, "Tassimo", 11));
        coffes.add(new Coffe(8, "Kapsułki Tchibo Cafissimo White Choc&Coconut", 14.50, 3, "Cafissimo", 98));
        coffes.add(new Coffe(9, "Senseo", 12.22, 10, "Senseo", 345));
        coffesId=10;
    }
}
