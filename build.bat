:: BUILD SYSTEM FOR JAVA FILES

:: Cleaning terminal
cls
@echo off

:: Cleaning all the pre-existing .class files
del *.class

:: Compiling main.java (this creates a main.class file)
javac main.java

:: Run main.class
java main

:: Cleaning the terminal again
cls