package com.wedul.javajunit5studyjunit.wedul;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/
@Repository
public class WedulRepository {

    Map<Long, Wedul> weduls = new HashMap<>();

    public Wedul getWedul(long id) {
        return weduls.get(id);
    }

    public void validate() {
    }
}
