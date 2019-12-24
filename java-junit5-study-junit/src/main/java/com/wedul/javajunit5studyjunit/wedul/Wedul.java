package com.wedul.javajunit5studyjunit.wedul;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/
@Getter
@NoArgsConstructor
public class Wedul {

    private long id;
    private int balance;

    public Wedul(long id) {
        this.id = id;
    }

    public Wedul(long id, int balance) {
        this.id = id;
        this.balance = balance;
    }
}
