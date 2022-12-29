#!/bin/bash
cd ../../skypro
mvn -pl oop clean
mvn -pl oop compile exec:exec
