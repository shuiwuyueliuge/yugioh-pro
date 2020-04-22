#!/bin/bash

firewall-cmd --add-port=22/tcp

systemctl start docker

docker start redis

docker start mongodb

docker start rabbitmq

docker start mysql

docker start nexus3
