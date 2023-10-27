package com.example.api.service;

import com.example.api.domain.Coupon;
import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.AppliedUserRepository;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    private final CouponCreateProducer couponCreateProducer;

    private final AppliedUserRepository appliedUserRepository;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository, CouponCreateProducer couponCreateProducer, AppliedUserRepository appliedUserRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
        this.appliedUserRepository = appliedUserRepository;
    }

    //    public void apply(Long userId) {
//        long count = couponRepository.count();
//
//        if (count > 100) {
//            return;
//        }
//
//        couponRepository.save(new Coupon(userId));
//    }

    /**
     * redis 실행 : docker exec -it {container_id} redis-cli
     * redis를 통한 race condition 해결
     * - increment 기능을 통해 레디스에서 카운팅
     * - 레디스는 싱글 스레드로 동작하므로 순서를 보장한다
     *
     * - 단점
     *  - 쿠폰에 발급 개수가 많아짐에 따라 RDB에 많은 요청 몰릴 경우 DB부하가 몰려 서버 지연이 발생할 수 있음
     */
    public void applyByRedis(Long userId) {
        long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

        couponRepository.save(new Coupon(userId));
    }

    /**
     * redis를 통한 race condition 해결
     * - increment 기능을 통해 레디스에서 카운팅
     * - 레디스는 싱글 스레드로 동작하므로 순서를 보장한다
     *
     * - 단점
     *  - 쿠폰에 발급 개수가 많아짐에 따라 RDB에 많은 요청 몰릴 경우 DB부하가 몰려 서버 지연이 발생할 수 있음
     */
    public void applyByKafka(Long userId) {
        long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }

    /**
     * 쿠폰 개수를 1인 1개로 제어한다
     */
    public void applyForUnique(Long userId) {
        Long apply = appliedUserRepository.add(userId);

        if (apply != 1) {
            return;
        }

        long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }
}
