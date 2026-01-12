# URL Shortening Service

Solution for **a** URL Shortener API that helps shorten long URLs.
Challenge from [roadmap.sh](https://roadmap.sh/projects/url-shortening-service)

## Requirements

* **Java 21**
* **Spring Boot 3.5.9**

## Database

The project uses an **H2 in-memory database** but can be replaced by any other relational database that implements the
**JPA standard**

## API Endpoints

| Method   | Endpoint                     | Description                                                 |
|:---------|:-----------------------------|:------------------------------------------------------------|
| `GET`    | `/shorten`                   | Get all short URL.                                          |
| `POST`   | `/shorten`                   | Create a new short URL                                      |
| `GET`    | `/shorten/{shortCode}`       | Retrieve an original URL from a short URL.                  |
| `PUT`    | `/shorten/{shortCode}`       | Update an existing short URL.                               |
| `DELETE` | `/shorten/{shortCode}`       | Delete an existing short URL.                               |
| `GET`    | `/shorten/{shortCode}/stats` | Get statistics on the short URL (number of times accessed). |

## How to run

1. **Clone the [repository](https://github.com/SanabriaDDi/UrlShortening.git):**
    ```bash
   git clone https://github.com/SanabriaDDi/UrlShortening.git
   cd UrlShortening
    ```
2. **Build the project:**
    ```bash
   ./mvnw clean install
    ```
3. **Run the application:**
    ```bash
   ./mvnw spring-boot:run
    ```

## API Testing

You can find the API documentation and test collections in the `/Collections` folder.
This project uses **Apidog** for API design and testing.

To test the endpoints:
1. Import the files from the `/Collections` folder into [Apidog](https://apidog.com) or any compatible tool.
2. Ensure the application is running at `http://localhost:8080`.