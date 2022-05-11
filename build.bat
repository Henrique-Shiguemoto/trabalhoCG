:: BUILD SYSTEM FOR JAVA FILES

:: Cleaning terminal
cls
@echo off

:: Cleaning all the pre-existing .class files
del *.class

:: Compiling all *.java files
javac *.java

:: Run main.class
java main

:: Cleaning the terminal again
:: cls