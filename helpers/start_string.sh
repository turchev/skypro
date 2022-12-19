#!/bin/bash
cd ../../skypro
mvn -pl string clean
mvn -pl string compile exec:exec
