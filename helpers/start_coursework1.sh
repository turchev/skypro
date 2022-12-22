#!/bin/bash
cd ../../skypro
mvn -pl coursework1 clean
mvn -pl coursework1 compile exec:exec
