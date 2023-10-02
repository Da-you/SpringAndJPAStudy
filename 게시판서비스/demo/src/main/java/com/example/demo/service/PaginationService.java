package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {

    private final static int BAR_LENGTH = 10;

    public List<Integer> getPaginationBarNumbers(int currentPage, int totalPage) {
        int startNumber = Math.max(currentPage - (BAR_LENGTH / 2),0);
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPage);
        return IntStream.range(startNumber, endNumber)
                .boxed()
                .toList();
    }


    public int currentBarLength() {
        return BAR_LENGTH;
    }
}
