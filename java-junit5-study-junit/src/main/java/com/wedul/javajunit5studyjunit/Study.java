package com.wedul.javajunit5studyjunit;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Study {

    private long studyId;

    public Study(long studyId) {
        this.studyId = studyId;
    }

    public Study(long studyId, StudyStatus status, int limit) {
        this.studyId = studyId;
        this.status = status;
        this.limit = limit;
    }

    public Study(int limit, StudyStatus status) {
        validate(limit);
        this.limit = limit;
        this.status = status;
    }

    public Study(int limit) {
        validate(limit);

        this.limit = limit;
    }

    public void validate(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("수강인원 제한 수는 0보다 커야한다.");
        }
    }

    private StudyStatus status = StudyStatus.DRAFT;
    private int limit;
}

