#!/bin/bash
cd ../../skypro
mvn -pl hashset clean
mvn -pl hashset compile exec:exec
