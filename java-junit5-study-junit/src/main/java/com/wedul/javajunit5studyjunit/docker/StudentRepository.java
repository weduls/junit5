package com.wedul.javajunit5studyjunit.docker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/24
 **/
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
