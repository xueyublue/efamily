@ECHO OFF

CD /D C:\efamily\tomcat\bin
CALL catalina.bat stop

CD /D C:\efamily\tomcat\bin\
CALL catalina.bat start

EXIT