update:
	- ssh administrator@69.197.134.101
	- cd ~/source
	- git checkout develop
	- git pull
	- ./gradlew clean build -x test && sudo systemctl restart application
