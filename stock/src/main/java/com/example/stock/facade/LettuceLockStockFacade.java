package com.example.stock.facade;

import com.example.stock.repository.RedisLockRepository;
import com.example.stock.service.StockService;
import org.springframework.stereotype.Component;

@Component
public class LettuceLockStockFacade {
    private RedisLockRepository redisLockRepository;
    private StockService stockService;

    public LettuceLockStockFacade(RedisLockRepository redisLockRepository, StockService stockService) {
        this.redisLockRepository = redisLockRepository;
        this.stockService = stockService;
    }

    /**
     * 동시성 이슈 처리
     * Redis - Lettuce Lock 활용
     *  - setnx 명령어를 활요하여 분산락 구현
     *  - Spin lock 방식
     * 장점
     *  - 구현이 간단함
     * 단점 :
     *  - Spin lock 방식이라 Redis에 부하를 줄 수 있음
     */
    public void decrease(Long id, Long quantity) throws InterruptedException {
        while (!redisLockRepository.lock(id)) {
            Thread.sleep(100);
        }

        try {
            stockService.decrease(id, quantity);
        } finally {
            redisLockRepository.unlock(id);
        }
    }
}
