@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.
@REM Maven Wrapper Windows Script
@REM ----------------------------------------------------------------------------

@IF "%__MVNW_ARG0_NAME__%"=="" (SET "__MVNW_ARG0_NAME__=%~nx0")

@setlocal
@set MAVEN_WRAPPER_JAR="%~dp0.mvn\wrapper\maven-wrapper.jar"
@set MAVEN_WRAPPER_PROPERTIES="%~dp0.mvn\wrapper\maven-wrapper.properties"
@set DOWNLOAD_URL=
@set USER_HOME=%USERPROFILE%
@set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

@for /F "tokens=1,2 delims==" %%A in (%MAVEN_WRAPPER_PROPERTIES%) do (
    @if "%%A"=="distributionUrl" set DISTRIBUTION_URL=%%B
)

@set WRAPPER_JAR="%~dp0.mvn\wrapper\maven-wrapper.jar"
@java -cp %WRAPPER_JAR% %WRAPPER_LAUNCHER% %MAVEN_WRAPPER_PROPERTIES% "--mvn" "%~dp0" %*
