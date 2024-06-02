#!/bin/bash

set -eu

CONTAINER=api-gateway-keycloak-1
BASE=/opt/keycloak

docker exec $CONTAINER mkdir $BASE/realm
docker exec $CONTAINER $BASE/bin/kc.sh export --dir $BASE/realm --realm crm --users different_files
docker cp $CONTAINER:$BASE/realm .
docker exec $CONTAINER rm -r $BASE/realm
