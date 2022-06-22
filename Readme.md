## Getting Started

First, install postgresql database:

```bash
#ubuntu
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo systemctl start postgresql.service
```

## Environment file setup
1. Let's create database name with test-database.
2. Change the spring.datasource.username & spring.datasource.password.
3. Run the application