package com.example.stock.facade;

import com.example.stock.repository.LockRepository;
import com.example.stock.service.StockService;
import org.springframework.stereotype.Component;

@Component
public class NamedLockStockFacade {

    private final LockRepository lockRepository;
    private final StockService stockService;

    public NamedLockStockFacade(LockRepository lockRepository, StockService stockService) {
        this.lockRepository = lockRepository;
        this.stockService = stockService;
    }

    /**
     * 동시성 이슈 처리
     * Named Lock 활용
     * 장점
     *  - 주로 분산락을 구현할 때 사용
     *  - 타임아웃 구현하기 용이
     * 단점 :
     *  - 트랜잭션 종료시에 락 해제, 세션 관리에 주의가 필요함
     *  - 구현 방법이 복잡
     */
    public void decrease(Long id, Long quantity) {
        try {
            lockRepository.getLock(id.toString());
            stockService.decrease(id, quantity);
        } finally {
            lockRepository.releaseLock(id.toString());
        }
    }
}
