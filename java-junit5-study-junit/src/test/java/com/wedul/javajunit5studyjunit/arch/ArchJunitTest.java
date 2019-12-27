package com.wedul.javajunit5studyjunit.arch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.wedul.JavaJunit5StudyJunit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

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
