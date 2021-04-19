#!/bin/bash

REPOSITORY=/home/ubuntu/app/step2
PROJECT_NAME=recipe-v2  #1

echo "> Build 파일 복사"

cp $REPOSITORY/zip/*.war $REPOSITORY/

echo "> 현재 구동중인 앱 pid 확인"

CURRENT_PID=$(pgrep -fl recipe-v2 | grep jar | awk '{print $1}')

echo ">현재구동중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then   #7
        echo "> 현재구동중인 앱이 없으므로 종료하지 않습니다."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo ">새 어플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.war | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar -Dspring.config.location=classpath:/application.properties,/home/ubuntu/app/application-auth.properties -Dspring.profiles.active=real $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &