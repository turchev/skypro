#!/bin/bash
cd ../../skypro
mvn -pl helloworld clean
mvn -pl helloworld compile exec:exec
