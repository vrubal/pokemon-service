# pokemon-service

Prerequisites:
Installed Jdk 1.8 or 11. Maven 3+, Git and Docker.

Steps to run the service

1. Clone the service using git.
   ```
   $ cd pokemon-service
   $ git clone https://github.com/vrubal/pokemon-service.git 
   $ git checkout master
   ```
2. Maven build the project.
   ```
   $ mvn clean install
   ```
3. Build docker image
   ```
   $ docker build --tag=pokemon-service:latest .
   ```
4. Run docker container
   ```
   $ docker run -p8080:8080 pokemon-service:latest
   ```
5. Service should be up, you can check service health using actuator url
   http://localhost:8080/actuator/health

6. API details:
   
   GET - API :getPokemonByName 
   http://localhost:8080/pokemons/<name>
  
   GET - API :getTranslatedPokemon
   http://localhost:8080/pokemons/translated/<name>

   
