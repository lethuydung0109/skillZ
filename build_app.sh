echo "1/3 -Creating docker image"
sudo docker build -t skillz-app .
echo "---- Docker image created."
sudo docker container rm -f skillz-app
echo "2/3 -Current container removed."
sudo docker run --name skillz-app-d -p 8081:8081 skillz-app
echo "4/4 -New container created."
