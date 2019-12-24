package com.wedul.javajunit5studyjunit.wedul;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
