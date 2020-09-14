package com.peetz.devops

import static com.lesfurets.jenkins.unit.global.lib.LibraryConfiguration.library
import static com.lesfurets.jenkins.unit.global.lib.LocalSource.localSource

import org.junit.Assert
import org.junit.Before
import org.junit.Test

import com.lesfurets.jenkins.unit.BasePipelineTest

class TestBuildJob extends BasePipelineTest {

    @Override
    @Before
    void setUp() throws Exception {
        scriptRoots += 'jobs/template/pipeline'
        super.setUp()
        def library = library()
            .name('ibtp-devops')
            .defaultVersion("master")
            .allowOverride(true)
            .implicit(true)
            .targetPath('build/libs')
            .retriever(localSource('build/libs'))
            .build()
        helper.registerSharedLibrary(library)
        helper.registerAllowedMethod("getCause", [Class.class], null)
        binding.setVariable('currentBuild', [result: 'UNSTABLE'])
        binding.getVariable('currentBuild').rawBuild = new RawBuild()
    }

    @Test
    void "check isTriggeredBySCM"() {
        def script = runScript('build_infos.groovy')
        Assert.assertTrue(script.buildJob.isTriggeredByScm())
    }

    @Test
    void "check is triggered manually"() {
        binding.getVariable('currentBuild').rawBuild.userCause = "Builduser"
        def script = runScript('build_infos.groovy')
        Assert.assertFalse(script.buildJob.isTriggeredByScm())
    }
}
