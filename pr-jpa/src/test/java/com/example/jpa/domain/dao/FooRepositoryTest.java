package com.example.jpa.domain.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class FooRepositoryTest {

    @Autowired
    private FooRepository fooRepository;

    @Test
    public void save() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Foo foo = new Foo();
            foo.setBar1("num_" + i);
            foo.setBar2("num_" + i + 1);
            foo.setBar3("num_" + i + 2);
            foo.setCreatedAt(LocalDateTime.now());
            foo.setUpdatedAt(LocalDateTime.now());
            fooRepository.save(foo);
        }

        // 1만개 : 13638ms
        // 10만개 : 136282ms
        System.out.println("elapsed time : " + (System.currentTimeMillis() - start) + "ms");
    }

    @Test
    public void saveAll() {
        List<Foo> foos = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Foo foo = new Foo();
            foo.setBar1("num_" + i);
            foo.setBar2("num_" + i + 1);
            foo.setBar3("num_" + i + 2);
            foo.setCreatedAt(LocalDateTime.now());
            foo.setUpdatedAt(LocalDateTime.now());
            foos.add(foo);
        }

        fooRepository.saveAll(foos);

        // 1만개 : 3446ms
        // 10만개 : 19976ms
        System.out.println("elapsed time : " + (System.currentTimeMillis() - start) + "ms");
    }
}
