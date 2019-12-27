package com.wedul.javajunit5studyjunit.arch;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.wedul.JavaJunit5StudyJunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * java-junit5-study
 *
 * @author wedul
 * @since 2019/12/27
 **/
@AnalyzeClasses(packagesOf = JavaJunit5StudyJunit.class)
public class ArchJunitTest {

    @ArchTest
    ArchRule dominPackageRule = classes().that().resideInAPackage("..domain..")
        .should()
        .onlyBeAccessed()
        .byClassesThat()
        .resideInAnyPackage("..weduls..");

    @ArchTest
    ArchRule wedulDoNotNuseDockerPackageClass = noClasses().that().resideInAnyPackage("..weduls..")
        .should().accessClassesThat().resideInAnyPackage("..docker..");

    @ArchTest
    ArchRule wedulsPackageRule = noClasses().that().resideOutsideOfPackages("..weduls..")
        .should().accessClassesThat().resideInAnyPackage("..weduls..");

}
