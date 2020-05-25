package com.ug.spring.service.Interfaces;

import com.ug.spring.domain.Coffe;

public interface CoffeService {
    Iterable<Coffe> getCoffes();
    Coffe delete(long coffeId);
    void save(Coffe coffe);
    void add(Coffe coffe);
    void addStartingData();
}
