package com.example1.demo1.service;

import com.example1.demo1.entity.City;

public interface CityTransactionService {

    void doTransactionTest1(City city1, City city2);

    void doTransactionTest2(City city1, City city2, boolean isThrowsException);

    void doTransactionTest3(City city1, City city2) throws Exception;
    
}
