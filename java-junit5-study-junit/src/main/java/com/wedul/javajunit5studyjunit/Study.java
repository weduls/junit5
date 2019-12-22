package com.wedul.javajunit5studyjunit;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Study {

    public Study(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("수강인원 제한 수는 0보다 커야한다.");
        }

        this.limit = limit;
    }

    private StudyStatus status = StudyStatus.DRAFT;
    private int limit;
}

