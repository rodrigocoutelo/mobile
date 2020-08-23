package com.example.recoin.Retrofit.Service;

public interface RequestResult {
    <T> void returnSuccess(T requestResult);
    void returnError(String message);
}
