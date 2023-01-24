#!/bin/bash
cd ../../skypro
mvn -pl hashmap clean
mvn -pl hashmap compile exec:exec
