package com.example.requestswithretrofit;

public interface RequestResult {
    <T> void returnSuccess(T cep);
    void returnError(String message);
}
