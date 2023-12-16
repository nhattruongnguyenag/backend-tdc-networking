update:
	- git pull
	- sudo rm -rf build
	- ./gradlew clean build -x test && sudo systemctl restart application
