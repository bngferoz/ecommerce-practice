sudo docker insptect imageid
sudo docker run --name redis-latest -p6379:6379 -d redis-latest
(d means deamon mood)
sudo docker inspect cId
sudo docker logs cId
sudo docker exec -it cId /bin/sh
(it means interactive mood)
ls
pwd
cd ..
ls
redis cli

keys *
exit
exit
docker stop cid
docker rm
docker rmi 
docker system prune -a
mvn clean
mvn package

docker build -t bngferoz/vip-service-registry:0.0.1 . 

docker images
docker run -d -p8761:8761 --name serviceregistry imageid
sudo touch Dockerfile
sudo chmod a+rwx /path/to/file
mvn clean install
docker build -t bngferoz/vip-config-server:0.0.1 .
docker run -d -p9296:9296 -e EUREKA_SERVER_ADDRESS:http://host.docker.internal:8761/eureka --name configserver imageid
docker logs cId
docker stop cId

docker rm cId
sudo ifconfig -a
http://192.168.31.53:8761/

