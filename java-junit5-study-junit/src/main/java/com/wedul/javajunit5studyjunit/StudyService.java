package com.wedul.javajunit5studyjunit;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/23
 **/
@Service
public class StudyService {

    private final StudyRepository studyRepository;

    public StudyService(StudyRepository studyRepository) {
        assert studyRepository != null;
        this.studyRepository = studyRepository;
    }

    public Study getStudy(long studyId) {
        return studyRepository.getStudy(studyId);
    }

}
