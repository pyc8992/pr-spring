package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessimisticLockStockService {

    private final StockRepository stockRepository;

    public PessimisticLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * 동시성 이슈 처리
     * DB Pessimistic Lock 활용
     * 장점
     *  - 충돌이 빈번하다면 Optimistic Lock보다 성능이 좋음
     *  - Lock을 통해 업데이트를 제어하기 때문에 데이터 정합성이 보장
     * 단점 :
     *  - 별도의 락을 잡기 때문에 성능 감소가 불가피
     */
    @Transactional
    public void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findByIdWithPessimisticLock(id);
        stock.decrease(quantity);
        stockRepository.save(stock);
    }
}
