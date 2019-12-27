package com.wedul.javajunit5studyjunit.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.wedul.JavaJunit5StudyJunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/27
 **/
@AnalyzeClasses(packagesOf = JavaJunit5StudyJunit.class)
public class ArchClassTest {

    @ArchTest
    ArchRule controllerClassRule = classes().that().haveSimpleNameEndingWith("Controller")
        .should().accessClassesThat().haveSimpleNameEndingWith("service")
        .orShould().accessClassesThat().haveSimpleNameEndingWith("repository");

    @ArchTest
    ArchRule repositorylassRule = classes().that().haveSimpleNameEndingWith("Repository")
        .should().accessClassesThat().haveSimpleNameEndingWith("Service");
}
