@echo off

IF "%1" == "" (
	echo Usage: executer.bat ^<command^>
	echo Commands:
	echo run	- clean, build, and deploy code, and launch in Tomcat
	echo start	- start Tomcat container
	echo stop	- stop Tomcat container
	EXIT /b
)

pushd "%~dp0"
set COMMAND=%1
set TARGET="%CD%\target"
set WEBAPPS="%CATALINA_HOME%\webapps"

IF "%COMMAND%" == "run" (
	call mvn clean package
	DEL %WEBAPPS%\struts2-ref.war
	RMDIR %WEBAPPS%\struts2-ref /s/q
	XCOPY %TARGET%\struts2-ref.war %WEBAPPS%
	call %CATALINA_HOME%\bin\catalina.bat start
) 

IF "%COMMAND%" == "start" (
	%CATALINA_HOME%\bin\catalina.bat start
)

IF "%COMMAND%" == "stop" (
	%CATALINA_HOME%\bin\catalina.bat stop
)	
popd
