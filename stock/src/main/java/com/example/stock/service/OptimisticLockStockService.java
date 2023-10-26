package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OptimisticLockStockService {

    private final StockRepository stockRepository;

    public OptimisticLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * 동시성 이슈 처리
     * DB Optimistic Lock 활용
     * 장점
     *  - 별도의 락을 잡지않아 성능 상 이점이 있음
     * 단점 :
     *  - 업데이트가 실패했을 때 방어 로직을 개발자가 직접 작성해줘야함
     */
    @Transactional
    public void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findByIdWithOptimisticLock(id);
        stock.decrease(quantity);
        stockRepository.save(stock);
    }
}
