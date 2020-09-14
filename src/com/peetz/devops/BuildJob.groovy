package com.peetz.devops

/**
 * BuildJob is a helper class for job related information.
 */
class BuildJob implements Serializable {

    def script

    /**
     * Constructor to retrieve reference to calling script.
     */
    BuildJob(script) {
        this.script = script
    }

    /**
     * Return true if job is triggered by SCM change.
     * @return Returns true, if job is triggered by SCM, false otherwise.
     */
    boolean isTriggeredBySCM() {
        def cause = this.script.currentBuild.rawBuild.getCause(hudson.model.Cause$UserIdCause)
        return (cause == null)
    }
}