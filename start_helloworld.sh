#!/bin/bash
# helloworld module startup
mvn -pl helloworld clean
mvn -pl helloworld compile exec:exec
