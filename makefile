update:
	- git pull
	- sudo ./gradlew clean build -x test && sudo systemctl restart application
