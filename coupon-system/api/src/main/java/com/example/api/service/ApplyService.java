package com.example.api.service;

import com.example.api.domain.Coupon;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
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
     * redis를 통한 race condition 해결
     * - increment 기능을 통해 레디스에서 카운팅
     * - 레디스는 싱글 스레드로 동작하므로 순서를 보장한다
     *
     * - 단점
     *  - 쿠폰에 발급 개수가 많아짐에 따라 RDB에 많은 요청 몰릴 경우 DB부하가 몰려 서버 지연이 발생할 수 있음
     */
    public void apply(Long userId) {
        long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

        couponRepository.save(new Coupon(userId));
    }
}
