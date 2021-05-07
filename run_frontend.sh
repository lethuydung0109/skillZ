sudo docker pull lethuydung0109/skillz-front
sudo docker container stop -f skillz-front
sudo docker container rm -f skillz-front
sudo docker run --name skillz-front -d -p 4200:80 lethuydung0109/skillz-front