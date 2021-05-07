echo "1/4 - Build gradle"
./gradlew clean build
echo "---- Build gradle has been finished successfully!"
echo "2/4 - Creating docker image"
sudo docker build -t skillz-app .
echo "---- Docker image created."
sudo docker container rm -f skillz-app
echo "3/4 - Current container removed."
sudo docker run --name skillz-app -d -p 8081:8081 skillz-app
echo "4/4 -New container created."
echo "---- Login and upload to docker hub"
sudo docker login
sudo docker tag skillz-app lethuydung0109/skillz-app
sudo docker push lethuydung0109/skillz-app
echo "---- Repo is updated"