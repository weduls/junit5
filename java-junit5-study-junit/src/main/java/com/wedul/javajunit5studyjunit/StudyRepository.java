package com.wedul.javajunit5studyjunit;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/
@Repository
public class StudyRepository {

    private static final List<Study> studies;

    static {
        studies = new ArrayList<>();
        studies.add(new Study(1L));
        studies.add(new Study(2L));
    }

    public Study getStudy(long studyId) {
        return studies.stream()
            .filter(t -> t.getStudyId() == studyId)
            .findFirst().orElse(null);
    }

    public void validate() {

    }

}
