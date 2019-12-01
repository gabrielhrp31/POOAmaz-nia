@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  AmazoniaAnalysis startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and AMAZONIA_ANALYSIS_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\AmazoniaAnalysis-1.1.jar;%APP_HOME%\lib\firebase-admin-6.10.0.jar;%APP_HOME%\lib\google-cloud-storage-1.85.0.jar;%APP_HOME%\lib\javafx-fxml-13-win.jar;%APP_HOME%\lib\javafx-controls-13-win.jar;%APP_HOME%\lib\javafx-controls-13.jar;%APP_HOME%\lib\javafx-graphics-13-win.jar;%APP_HOME%\lib\javafx-graphics-13-linux.jar;%APP_HOME%\lib\javafx-graphics-13-mac.jar;%APP_HOME%\lib\javafx-graphics-13.jar;%APP_HOME%\lib\javafx-base-13-win.jar;%APP_HOME%\lib\javafx-base-13.jar;%APP_HOME%\lib\firebase-core-17.0.0.aar;%APP_HOME%\lib\google-api-client-gson-1.30.1.jar;%APP_HOME%\lib\google-http-client-gson-1.30.1.jar;%APP_HOME%\lib\google-cloud-core-http-1.85.0.jar;%APP_HOME%\lib\gax-httpjson-0.64.1.jar;%APP_HOME%\lib\google-cloud-firestore-1.9.0.jar;%APP_HOME%\lib\google-cloud-core-grpc-1.79.0.jar;%APP_HOME%\lib\google-cloud-core-1.85.0.jar;%APP_HOME%\lib\gax-grpc-1.46.1.jar;%APP_HOME%\lib\grpc-alts-1.21.0.jar;%APP_HOME%\lib\grpc-grpclb-1.21.0.jar;%APP_HOME%\lib\protobuf-java-util-3.7.1.jar;%APP_HOME%\lib\grpc-netty-shaded-1.21.0.jar;%APP_HOME%\lib\opencensus-contrib-grpc-util-0.21.0.jar;%APP_HOME%\lib\grpc-core-1.21.0.jar;%APP_HOME%\lib\gson-2.8.5.jar;%APP_HOME%\lib\firebase-storage-18.0.0.aar;%APP_HOME%\lib\google-api-services-storage-v1-rev20190624-1.30.1.jar;%APP_HOME%\lib\google-api-client-1.30.2.jar;%APP_HOME%\lib\gax-1.47.1.jar;%APP_HOME%\lib\google-auth-library-oauth2-http-0.16.2.jar;%APP_HOME%\lib\google-http-client-appengine-1.30.1.jar;%APP_HOME%\lib\google-http-client-jackson2-1.30.1.jar;%APP_HOME%\lib\google-oauth-client-1.30.1.jar;%APP_HOME%\lib\google-http-client-1.30.1.jar;%APP_HOME%\lib\proto-google-cloud-firestore-admin-v1-1.9.0.jar;%APP_HOME%\lib\proto-google-cloud-firestore-v1-1.9.0.jar;%APP_HOME%\lib\proto-google-cloud-firestore-v1beta1-0.62.0.jar;%APP_HOME%\lib\proto-google-iam-v1-0.12.0.jar;%APP_HOME%\lib\api-common-1.8.1.jar;%APP_HOME%\lib\opencensus-contrib-http-util-0.21.0.jar;%APP_HOME%\lib\grpc-protobuf-1.21.0.jar;%APP_HOME%\lib\grpc-stub-1.21.0.jar;%APP_HOME%\lib\grpc-auth-1.21.0.jar;%APP_HOME%\lib\grpc-protobuf-lite-1.21.0.jar;%APP_HOME%\lib\grpc-api-1.21.0.jar;%APP_HOME%\lib\guava-28.0-android.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\netty-codec-http-4.1.34.Final.jar;%APP_HOME%\lib\netty-handler-4.1.34.Final.jar;%APP_HOME%\lib\netty-codec-4.1.34.Final.jar;%APP_HOME%\lib\netty-transport-4.1.34.Final.jar;%APP_HOME%\lib\firebase-analytics-17.0.0.aar;%APP_HOME%\lib\firebase-auth-interop-18.0.0.aar;%APP_HOME%\lib\play-services-measurement-api-17.0.0.aar;%APP_HOME%\lib\firebase-iid-19.0.0.aar;%APP_HOME%\lib\firebase-common-18.0.0.aar;%APP_HOME%\lib\firebase-iid-interop-17.0.0.aar;%APP_HOME%\lib\play-services-base-17.0.0.aar;%APP_HOME%\lib\play-services-measurement-17.0.0.aar;%APP_HOME%\lib\play-services-measurement-sdk-17.0.0.aar;%APP_HOME%\lib\play-services-tasks-17.0.0.aar;%APP_HOME%\lib\play-services-measurement-impl-17.0.0.aar;%APP_HOME%\lib\play-services-measurement-sdk-api-17.0.0.aar;%APP_HOME%\lib\play-services-measurement-base-17.0.0.aar;%APP_HOME%\lib\play-services-stats-17.0.0.aar;%APP_HOME%\lib\firebase-measurement-connector-18.0.0.aar;%APP_HOME%\lib\play-services-ads-identifier-17.0.0.aar;%APP_HOME%\lib\play-services-basement-17.0.0.aar;%APP_HOME%\lib\fragment-1.0.0.aar;%APP_HOME%\lib\legacy-support-core-ui-1.0.0.aar;%APP_HOME%\lib\legacy-support-core-utils-1.0.0.aar;%APP_HOME%\lib\loader-1.0.0.aar;%APP_HOME%\lib\viewpager-1.0.0.aar;%APP_HOME%\lib\coordinatorlayout-1.0.0.aar;%APP_HOME%\lib\drawerlayout-1.0.0.aar;%APP_HOME%\lib\slidingpanelayout-1.0.0.aar;%APP_HOME%\lib\customview-1.0.0.aar;%APP_HOME%\lib\swiperefreshlayout-1.0.0.aar;%APP_HOME%\lib\asynclayoutinflater-1.0.0.aar;%APP_HOME%\lib\core-1.0.0.aar;%APP_HOME%\lib\versionedparcelable-1.0.0.aar;%APP_HOME%\lib\collection-1.0.0.jar;%APP_HOME%\lib\lifecycle-runtime-2.0.0.aar;%APP_HOME%\lib\lifecycle-viewmodel-2.0.0.aar;%APP_HOME%\lib\documentfile-1.0.0.aar;%APP_HOME%\lib\localbroadcastmanager-1.0.0.aar;%APP_HOME%\lib\print-1.0.0.aar;%APP_HOME%\lib\lifecycle-livedata-2.0.0.aar;%APP_HOME%\lib\lifecycle-livedata-core-2.0.0.aar;%APP_HOME%\lib\lifecycle-common-2.0.0.jar;%APP_HOME%\lib\core-runtime-2.0.0.aar;%APP_HOME%\lib\core-common-2.0.0.jar;%APP_HOME%\lib\interpolator-1.0.0.aar;%APP_HOME%\lib\cursoradapter-1.0.0.aar;%APP_HOME%\lib\annotation-1.0.0.jar;%APP_HOME%\lib\httpclient-4.5.8.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\j2objc-annotations-1.3.jar;%APP_HOME%\lib\opencensus-contrib-grpc-metrics-0.21.0.jar;%APP_HOME%\lib\opencensus-api-0.21.0.jar;%APP_HOME%\lib\javax.annotation-api-1.3.2.jar;%APP_HOME%\lib\google-auth-library-credentials-0.16.2.jar;%APP_HOME%\lib\netty-buffer-4.1.34.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.34.Final.jar;%APP_HOME%\lib\netty-common-4.1.34.Final.jar;%APP_HOME%\lib\auto-value-annotations-1.6.3.jar;%APP_HOME%\lib\httpcore-4.4.11.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-codec-1.11.jar;%APP_HOME%\lib\grpc-context-1.21.0.jar;%APP_HOME%\lib\proto-google-common-protos-1.16.0.jar;%APP_HOME%\lib\protobuf-java-3.7.1.jar;%APP_HOME%\lib\threetenbp-1.3.3.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\checker-compat-qual-2.5.5.jar;%APP_HOME%\lib\error_prone_annotations-2.3.2.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.17.jar;%APP_HOME%\lib\commons-lang3-3.5.jar;%APP_HOME%\lib\annotations-4.1.1.4.jar;%APP_HOME%\lib\jackson-core-2.9.9.jar

@rem Execute AmazoniaAnalysis
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %AMAZONIA_ANALYSIS_OPTS%  -classpath "%CLASSPATH%" analysis.Launcher %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable AMAZONIA_ANALYSIS_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%AMAZONIA_ANALYSIS_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
