#!/bin/bash

REPOSITORY=/home/ubuntu/app/step2
PROJECT_NAME=recipe-v2  #1

cd $REPOSITORY/$PROJECT_NAME/ #2

echo "> Git pull"
git pull #3

echo "> 프로젝트 Build 시작"
./mvnw package #4

echo "> step1 디렉토리로 이동"

cd $REPOSITORY

echo "> Build 파일복사"

cp $REPOSITORY/$PROJECT_NAME/target/*.war $REPOSITORY/ #5

echo "> 현재구동중인 앱pid 확인"

CURRENT_PID=$(pgrep -f ${PROJECT_NAME}*.jar) #6

echo ">현재구동중인 애플리케이션pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then   #7
        echo "> 현재구동중인 앱이 없으므로 종료하지 않습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo ">새 어플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/ | grep *.war | tail -n 1)

echo "> JAR Name: $JAR_NAME"

nohup java -jar -Dspring.config.location=classpath:/application.properties,/home/ubuntu/app/application-auth.properties $REPOSITORY/$JAR_NAME  2>&1 &