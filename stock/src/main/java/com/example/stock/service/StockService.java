package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

//    @Transactional
//    public void decrease(Long id, Long quantity) {
//        // Stock 조회
//        // 재고 감소
//        // 갱신된 값 저장
//        Stock stock = stockRepository.findById(id).orElseThrow();
//        stock.decrease(quantity);
//        stockRepository.saveAndFlush(stock);
//    }

    /**
     * 동시성 이슈 처리
     * synchronized keyword 활용
     * - 자바의 기본 키워드인 synchronized를 활용하면 하나의 프로세스에 하나의 스레드만 동작하도록 보장
     * 문제점
     * - 보통의 application 서버들은 2대 이상을 구축하는데 다른 서버에서는 해당 키워드를 알 수 없기 때문에 race condition 이슈는 여전히 존재
     */
    @Transactional
    public synchronized void decrease(Long id, Long quantity) {
        // Stock 조회
        // 재고 감소
        // 갱신된 값 저장
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
