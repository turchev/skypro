#!/bin/bash
cd ../../skypro
mvn -pl loops clean
mvn -pl loops compile exec:exec
