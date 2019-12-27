package com.wedul.javajunit5studyjunit.weduls;

import com.wedul.javajunit5studyjunit.weduls.domain.Wedul;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/
@Service
@RequiredArgsConstructor
public class WedulService {

    private final WedulRepository wedulRepository;

    public Wedul getWedul(long id) {
        return wedulRepository.getWedul(id);
    }

}
