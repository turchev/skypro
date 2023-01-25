#!/bin/bash
cd ../../skypro
mvn -pl coursework2 clean
mvn -pl coursework2 compile exec:exec
