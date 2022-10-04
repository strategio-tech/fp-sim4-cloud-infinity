
# Pokemon App (CI/CD)

This is a Pokemon app built with React.js and Spring Boot that allows you to look up any pokemon and their stats. This app is a great companion for those who love playing Pokemon games, searching for Pokemon using Pokemon GO, or who love watching the shows. 

This app was built to be deployed in a cloud envirnoment such as AWS.

<img width="306" alt="Screen Shot 2022-10-04 at 8 18 13 PM" src="https://user-images.githubusercontent.com/77266395/193948034-2e3c24f3-6778-4510-85a9-febee4e34cba.png">

## Features

- Search for pokemon by ID and name
- View pokemon info
  - ID
  - Name
  - Photo (if there is one)
  - Types
  - Description (currently in English only)
  - Stats
    - Height
    - Weight
    - Species
    - Etc...
- Directly link to pokemon
  - http://pokedex-bkt.s3-website-us-east-1.amazonaws.com/pokemon/244
  - http://pokedex-bkt.s3-website-us-east-1.amazonaws.com/pokemon/entei
- Mobile version


## Deployment

To deploy the project locally, there are a few steps you need to go through.

First, you need to clone this repo to your desktop. You will notice that the "applications" (backend and frontend) are seperated into two folders respectively.

You will need to download and install the dependencies for both applications. 

Start off by moving into the ```frontend``` directory and running

```bash
  npm install
```

This will install all of the dependencies for the frontend project. After that, you need to go into the ```backend``` folder. I would recommend using an IDE such as Intellij as it will detect the type of project and its build system (we are using Maven). It will most likely download and install the dependencies for you.

Second, after you install the dependencies, you need to change the configurations in the following files

```
App.js
application.properties
PokemonController.java
```

Each of these files need to be updated.

For ```App.js```

You need to change the ```baseUrl```

```javascript
  const navigate = useNavigate();
  const location = useLocation();
  const dispatch = useDispatch();

  const search = useSelector((state) => state.search);
  const searchBar = useSelector((state) => state.searchBar);

  const baseURL = "http://18.205.172.254/api/pokemon/";
```

to 

```javascript
  const navigate = useNavigate();
  const location = useLocation();
  const dispatch = useDispatch();

  const search = useSelector((state) => state.search);
  const searchBar = useSelector((state) => state.searchBar);

  const baseURL = "http://localhost:8080/api/pokemon/";
```

This will allow the ```frontend``` app to connect to the ```backend``` server correctly.

In ```application.properties```, you need to replace everything and add the following

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://<YOUR_INFO_FOR_LOCAL_MYSQL_DATABASE>:3306/<DATABASE_NAME>
spring.datasource.username=<YOUR_USERNAME>
spring.datasource.password=<YOUR_PASSWORD>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Finally, in ```PokemonController.java```, you need to change the ```@CrossOrigin``` annotation

```
@CrossOrigin(origins= "http://pokedex-bkt.s3-website-us-east-1.amazonaws.com")
@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
```

to 

```
@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
```

This will stop the ```CORS``` issue from showing up when the ```frontend``` app makes API request.

Third, you will now need to run the frontend app by moving into that folder and running ```npm start``` and also start up the backend server. Once you have done both you those things, you should be able to successfully use both applications. You are now running the project locally!

## Tech Stack

**Frontend:** React.js, Axios, React Router, Redux, AWS S3, Github Actions

**Backend:** Java, Spring Boot, Selenium, Maven, 3rd Party API, AWS EC2, Github Actions

**Database:** AWS RDS, MySQL

**API:** [PokeApi](https://pokeapi.co/)

## Authors

- [Clayton Brady](https://github.com/Yotigify)
- [Angelo Joudah](https://github.com/AngeloJ123)
- [Jordan Blount](https://github.com/JordanBlount)

