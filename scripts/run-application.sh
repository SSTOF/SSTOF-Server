sudo pkill -6 java
source /home/ec2-user/.env
SPRING_PROFILES_ACTIVE=production nohup java -jar /home/ec2-user/sstof/build/libs/*.jar 1>>/home/ec2-user/log/spring-log.log 2>>/home/ec2-user/log/spring-error.log &