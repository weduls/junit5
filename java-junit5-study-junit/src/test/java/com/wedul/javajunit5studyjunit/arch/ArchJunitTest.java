package com.wedul.javajunit5studyjunit.arch;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
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
public class ArchJunitTest {

    @Test
    @DisplayName("Arch 아키텍처 테스트 검증")
    void arch_archtecture_test() {
        JavaClasses classes = new ClassFileImporter().importPackages("com.wedul.javajunit5studyjunit");

        // weduls domain 패키지에 있는 클래스들은 weduls 패키지 내에서에서 사용 가
        ArchRule dominPackageRule = classes().that().resideInAPackage("..domain..")
            .should()
            .onlyBeAccessed()
            .byClassesThat()
            .resideInAnyPackage("..weduls..");

        dominPackageRule.check(classes);

        // weduls에 있는 어떤 클래스도 docker에 있는 패키지에 있는 클래스에 접근할 수 없다.
        ArchRule wedulDoNotNuseDockerPackageClass = noClasses().that().resideInAnyPackage("..weduls..")
            .should().accessClassesThat().resideInAnyPackage("..docker..");
        wedulDoNotNuseDockerPackageClass.check(classes);

        // weduls 내에 있는 클래스는 weduls 내에 있는 클래스만 접근 가능
        ArchRule wedulsPackageRule = noClasses().that().resideOutsideOfPackages("..weduls..")
            .should().accessClassesThat().resideInAnyPackage("..weduls..");
        wedulsPackageRule.check(classes);

        // 패키지 사이에 순환참조 확인
        ArchRule freeOfCycles = slices().matching("..javajunit5studyjunit.(*)..")
            .should().beFreeOfCycles();
    }

}
