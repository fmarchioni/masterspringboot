#!/bin/bash

set -o errexit

http POST localhost:9080/application < cardApplication.json


# curl -X POST -H "Content-Type: application/json" -d @cardApplication.json http://localhost:9080/application