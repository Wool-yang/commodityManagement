This project is a Java EE web application designed for commodity management. It originated as a software course project for the Taiyuan University of Technology (TYUT) in 2021.

## Backend Architecture

The backend of this commodity management system is built with the following architecture and technologies:

*   **Servlet-based Request Handling:** Java Servlets are the core components for receiving and processing client requests.
*   **Data Access Object (DAO) Pattern:** Database interactions are encapsulated within DAO classes, promoting separation of concerns and maintainability.
*   **Key Libraries:**
    *   **Apache Commons DBUtils:** Simplifies JDBC operations, reducing boilerplate code for database interactions.
    *   **Apache Commons DBCP:** Provides efficient database connection pooling, enhancing performance and resource management.
    *   **Fastjson:** Used for high-performance parsing and serialization of JSON data.
*   **Servlet Mapping:** Request URLs are mapped to their respective Servlets primarily through the use of `@WebServlet` annotations, minimizing the need for extensive XML configuration in `web.xml`.

## Frontend Architecture

The frontend of the application is developed using modern web technologies to provide a responsive and interactive user experience:

*   **Vue.js Framework:** The core of the frontend is built with Vue.js, a progressive JavaScript framework that enables the creation of dynamic user interfaces.
*   **Element UI Library:** For a rich set of pre-built UI components, the project leverages Element UI, which helps in quickly assembling a consistent and aesthetically pleasing user interface.
*   **AJAX for Backend Communication:** Asynchronous JavaScript and XML (AJAX) calls are used to communicate with the backend servlets. Data is exchanged in JSON format, allowing for efficient and lightweight data transfer between the client and server.

## Core Functionalities

The commodity management system provides the following key features:

*   **Commodity Management:**
    *   Full CRUD (Create, Read, Update, Delete) operations for commodities.
    *   Ability to search for commodities by name and price range.
*   **Category Management:**
    *   Functionality to manage commodity categories. (This typically includes operations like adding, viewing, updating, and deleting categories).
*   **User Authentication:**
    *   A login system to authenticate users.
    *   Access control is enforced using a login filter to protect routes.

## Database

The application utilizes a relational database to store and manage its data. Based on the inclusion of the `mysql-connector-java` library, the database system is MySQL. The primary data entities, represented as tables in the database, are expected to include:

*   **`Commodity`**: Stores detailed information about each product or commodity.
*   **`Categories`**: Used to organize commodities into different categories for better management and navigation.
*   **`User`**: Manages user accounts, credentials, and potentially roles for authentication and authorization purposes.

## Project Structure

The project is organized into the following main directories and follows an MVC-like pattern for its Java backend:

*   **`src/`**: Contains all the Java source code.
    *   `cn/commodityManagement/controller/`: Houses the Servlets, which act as controllers, handling incoming HTTP requests and orchestrating responses.
    *   `cn/commodityManagement/dao/`: Includes Data Access Objects (DAOs) responsible for all database interactions, abstracting the persistence logic.
    *   `cn/commodityManagement/domain/`: Contains Plain Old Java Objects (POJOs) that represent the data models or entities of the application (e.g., Commodity, User).
    *   `cn/commodityManagement/filter/`: Stores request filters, such as those used for user authentication and ensuring that protected resources are accessed only by logged-in users.
    *   `cn/commodityManagement/utils/`: Provides utility classes for common functionalities, like database connection management (e.g., `DBCPUtils`).
*   **`web/`**: Contains all web application resources that are served to the client.
    *   `WEB-INF/`: A standard Java EE directory containing:
        *   `web.xml`: The deployment descriptor for the web application.
        *   `lib/`: Stores all external library JAR files required by the project (e.g., `mysql-connector-java.jar`, `commons-dbutils.jar`, `fastjson.jar`).
        *   `classes/`: Contains the compiled Java classes from the `src/` directory. This is typically managed by the IDE and build process.
    *   Static web content such as HTML files (e.g., `index.html`, `login.html`, `list.html`, `categories.html`), CSS, and JavaScript files.
    *   `js/`: Contains JavaScript files, including libraries like `vue.js` and `axios.min.js`.
    *   `element-ui/`: Contains the assets for the Element UI library, which provides Vue.js components.
    *   `img/`: Stores images used in the web application.
*   **`.idea/`**: This directory contains project-specific settings for the IntelliJ IDEA IDE. It's generally specific to the development environment and is often included in `.gitignore` to prevent it from being tracked by version control.

**Overall Architectural Pattern:**

The Java backend code in `src/` is structured following an MVC-like (Model-View-Controller) pattern:
*   **Model:** Represented by the domain objects in `cn/commodityManagement/domain/` and managed by the DAOs in `cn/commodityManagement/dao/`.
*   **View:** The frontend, built with Vue.js and HTML files in the `web/` directory, is responsible for presenting data to the user and capturing user input.
*   **Controller:** The Servlets in `cn/commodityManagement/controller/` handle user requests, interact with the models (via DAOs), and select the appropriate view to render.
