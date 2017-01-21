#HackUCSC-2017

This project can be built by `docker-compose`. So to build the whole things, you only need to
* Install docker and docker-compose (follow [this](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-16-04) direction)
* Set some environment variables and dont forget to `source` it:
```
export DB_HOST="mysql"
export DB_USER="<your_user_name>"
export DB_PASSWORD="<your_db_password>"
```	
* simply run `docker-compose up` and everythings will be up and running.

###Credits
- Socket.io chat example 



