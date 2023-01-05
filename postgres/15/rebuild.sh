IMG_NAME=rudiantoni/postgres:15
CONT_NAME=pg-additional

sudo docker stop $CONT_NAME
sudo docker rm $CONT_NAME
sudo docker rmi $IMG_NAME
sudo docker build -t $IMG_NAME .

sudo docker run \
    --name $CONT_NAME \
    -p 5432:5432 \
    -e POSTGRES_DB=double \
    -e POSTGRES_USER=double \
    -e POSTGRES_PASSWORD=double \
    -e POSTGRES_ADDITIONAL_DBS="another,even_other" \
    -d $IMG_NAME \
    && sudo docker logs --tail 500 --follow $CONT_NAME

#     -e POSTGRES_ADDITIONAL_DBS_USER=true \