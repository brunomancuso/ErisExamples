@echo off

set PATH=%JAVA_HOME%\bin\;%PATH%;
start javaw -cp ..\lib\* org.threewaves.eris.terminal.Terminal"