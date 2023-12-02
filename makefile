update:
	- git pull
	- ./gradlew clean build -x test && sudo systemctl restart application
