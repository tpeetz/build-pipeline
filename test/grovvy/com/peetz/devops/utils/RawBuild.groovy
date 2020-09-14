package com.peetz.devops

/**
 * RawBuild is a utility class to mock the rawBuild property of the currentBuild. The rawBuild property is used to
 * determine wether a build is started manually or triggered by SCM.
 */
class RawBuild {
  def userCause = null

  /**
   *
   */
  RawBuild() { }

  /**
   *
   * @param userCause
   */
  RawBuild(userCause) {
    this.userCause = userCause
  }

  /**
   *
   * @param causeClass
   * @return
   */
  def getCause(causeClass) {
    return this.userCause
  }
}
