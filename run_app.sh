sudo docker pull lethuydung0109/skillz-app
sudo docker container stop -f skillz-app
sudo docker container rm -f skillz-app
sudo docker run --name skillz-app -d -p 8081:8081 lethuydung0109/skillz-app
