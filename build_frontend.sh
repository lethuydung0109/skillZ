cd src/main/java/miage/skillz/frontend
echo "1/4 - Building project …"
sudo ng build --prod
echo "---- Build finished."
echo "2/4 -Creating docker image"
sudo docker build -t skillz-front .
echo "---- Docker image created."
sudo docker container rm -f skillz-front
echo "3/4 -Current container removed."
sudo docker run --name skillz-front -d -p 4200:80 skillz-front
echo "4/4 -New container created."
echo "---- Login and upload to docker hub"
sudo docker login
sudo docker tag skillz-front lethuydung0109/skillz-front
sudo docker push lethuydung0109/skillz-front
echo "---- Repo is updated"