#!/bin/bash
# helloworld module startup
mvn -pl loops clean
mvn -pl loops compile exec:exec
