IMG_NAME=rudiantoni/postgres:15
CONT_NAME=pg-double

sudo docker stop $CONT_NAME
sudo docker rm $CONT_NAME
sudo docker rmi $IMG_NAME
sudo docker build --rm -t $IMG_NAME .

sudo docker run \
    --name $CONT_NAME \
    -p 5432:5432 \
    -e POSTGRES_DB=double \
    -e POSTGRES_USER=double \
    -e POSTGRES_PASSWORD=double \
    -e POSTGRES_ADDITIONAL_DBS="another" \
    -v .../init_files:/docker-entrypoint-initdb.d \
    -d $IMG_NAME \
    && sudo docker logs --tail 500 --follow $CONT_NAME

    # -e POSTGRES_ADDITIONAL_DBS="another,even_other" \
    # -e POSTGRES_ADDITIONAL_DBS_USER=true \
# ARG example
#sudo docker build --rm --build-arg ARG_UNUSED_EXAMPLE=new_value -t $IMG_NAME .