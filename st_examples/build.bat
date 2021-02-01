@echo off

call mvn dependency:copy-dependencies -DoutputDirectory=lib clean install

copy target\*.jar lib\