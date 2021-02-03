#!/usr/bin/env bash

base_dir=$PWD
services=("config-server" "Customer-Shop" "EurekaServer" "shop-api" "ShopProduct" "ZullGateway")

function build_services(){
for service in ${services[@]}; do
	cd $service
	mvn clean install
	cd $base_dir
done
}

function create_images(){
for service in ${services[@]}; do
	cd $service
	docker build . -t $service
	cd $base_dir
done
}

case $1 in
	build-services)
		build_services ;;
	create-images)
		create_images;;
esac
