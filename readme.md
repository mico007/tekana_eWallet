# Tekana  eWallet

#### As a leader of the engineers team, here is my step-by-step strategy for rebuilding the back-end solution of the "Tekana-eWallet" legacy platform:

**Phase 1: Project Initiation and Planning**

1. **Project Kick-off:**
    - Assemble the cross-functional team, including back-end engineers, front-end developers, UI/UX designers, the product owner, Scrum Master, and business experts.
    - Introduce the team to the mission and objectives.

2. **Requirements Gathering:**
    - Collaborate closely with the business team to gather detailed requirements, including functional and non-functional requirements.
    - Define user stories and acceptance criteria.

3. **Technology Stack Selection:**
    - Evaluate the current technology stack and identify areas that need upgrading or replacement.
    - Consider modern, scalable, and secure technologies and frameworks for the new back-end stack.

**Phase 2: Design and Architecture**

4. **System Architecture Design:**
    - Collaborate with back-end engineers and architects to design a scalable, robust, and flexible system architecture.
    - Plan for high availability, disaster recovery, and data security.

5. **Front-End Integration Planning:**
    - Work closely with front-end developers to ensure alignment with the back-end architecture.
    - Define APIs and data structures that will be exposed to the front end.

6. **Data Migration Strategy:**
    - Develop a plan for migrating data from the old system to the new back-end.
    - Ensure data integrity and minimal downtime during migration.

**Phase 3: Development and Testing**

7. **Agile Development Practices:**
    - Implement Agile methodologies with the Scrum Master's guidance to ensure regular sprints, feedback, and adaptation.

8. **Coding and Implementation:**
    - Back-end engineers start building the new system, following best coding practices.
    - Conduct code reviews to ensure quality and consistency.

9. **Automated Testing:**
    - Integrate automated testing into the development process for continuous integration and continuous delivery (CI/CD).
    - Perform unit, integration, and end-to-end testing.

**Phase 4: Deployment and Go-Live**

10. **Pilot System Development:**
    - Develop a pilot system that represents a scaled-down version of the final product.
    - This pilot system will undergo rigorous testing and refinement.

11. **User Acceptance Testing (UAT):**
    - Engage with customers and conduct UAT on the pilot system to gather feedback and validate functionality.

12. **Scalability and Performance Testing:**
    - Test the system's scalability and performance under real-world conditions to ensure it can handle 1 million customers.

13. **Deployment and Monitoring:**
    - Deploy the pilot system and closely monitor its performance in a production-like environment.
    - Make necessary adjustments based on monitoring data.

14. **Training and Documentation:**
    - Train support teams and create comprehensive documentation for the operations team to manage and maintain the system.

15. **Go-Live and Post-Implementation Support:**
    - Gradually transition customers to the new system while maintaining support for the old one.
    - Provide post-implementation support to address any issues and ensure a smooth transition.

**Phase 5: Continuous Improvement**

16. **Feedback Loop:**
    - Establish a feedback loop with customers and internal stakeholders to continuously improve the system.
    - Prioritize new features and enhancements based on feedback and evolving business needs.

This strategy outlines the key steps from project initiation to go-live and emphasizes collaboration with the business team and front-end developers to ensure a successful transition to a top-notch back-end stack for "Tekana-eWallet."

### API Reference
Base url: /api/v1

| Endpoints                    | Method | Request Params                                 | Query Params              | Description                                                    | Authorization |
|------------------------------|--------|------------------------------------------------|---------------------------|----------------------------------------------------------------|---------------|
| /users                       | POST   | fullName, email, password                      |                           | Create new user                                                | No            |
| /users/login-user            | POST   | email, password                                |                           | Login user                                                     | No            |
| /customers                   | POST   | fullName, phone, email, sex, address, age      |                           | Create new                                                     | No            |
| /customers                   | GET    |                                                |                           | Get all customers                                              | Yes           |
| /customers/:customerPublicId | GET    |                                                |                           | Get customer by customerId                                     | No            |
| /transactions                | POST   | amount, transactionType, description, walletId |                           | Create new transaction                                         | No            |
| /transactions                | GET    |                                                | transactionType           | Get transactions by type and/or all transactions               | Yes           |
| /transactions/:walletId      | GET    |                                                | walletId, transactionType | Get customer transactions and/or customer transactions by type | Yes           |
| /wallets                     | POST   | customerId                                     |                           | Create new wallet                                              | No            |
| /wallets                     | GET    |                                                |                           | Get all wallets                                                | Yes           |
| /wallets/:customerId         | GET    |                                                |                           | Get customer wallets                                           | Yes           |

API Documentation can be found at swagger link: /api/v1/swagger-ui.html

### Project Structure

 **Programming language used:**
   Java

 **Framework used:**
   Spring Boot

```
.
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── readme.md
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── dev
│   │   │           └── TekanaeWallet
│   │   │               ├── exeptions
│   │   │               │   ├── ErrorMessage.java
│   │   │               │   ├── TekanaEWalletExceptionHandler.java
│   │   │               │   └── TekanaEWalletException.java
│   │   │               ├── io
│   │   │               │   ├── entity
│   │   │               │   │   ├── CustomerEntity.java
│   │   │               │   │   ├── TransactionEntity.java
│   │   │               │   │   ├── UserEntity.java
│   │   │               │   │   └── WalletEntity.java
│   │   │               │   └── repository
│   │   │               │       ├── CustomerRepository.java
│   │   │               │       ├── TransactionRepository.java
│   │   │               │       ├── UserRepository.java
│   │   │               │       └── WalletRepository.java
│   │   │               ├── security
│   │   │               │   ├── AppProperties.java
│   │   │               │   ├── AuthenticationFilter.java
│   │   │               │   ├── AuthorizationFilter.java
│   │   │               │   ├── SecurityConstants.java
│   │   │               │   ├── WebCorsMapping.java
│   │   │               │   └── WebSecurity.java
│   │   │               ├── service
│   │   │               │   ├── CustomerService.java
│   │   │               │   ├── impl
│   │   │               │   │   ├── CustomerServiceImpl.java
│   │   │               │   │   ├── TransactionServiceImpl.java
│   │   │               │   │   ├── UserServiceImpl.java
│   │   │               │   │   └── WalletServiceImpl.java
│   │   │               │   ├── TransactionService.java
│   │   │               │   ├── UserService.java
│   │   │               │   └── WalletService.java
│   │   │               ├── shared
│   │   │               │   ├── dto
│   │   │               │   │   ├── CustomerDto.java
│   │   │               │   │   └── UserDto.java
│   │   │               │   ├── TransactionType.java
│   │   │               │   └── Utils.java
│   │   │               ├── SpringApplicationContext.java
│   │   │               ├── Swagger2UiConfiguration.java
│   │   │               ├── TekanaEWalletApplication.java
│   │   │               └── ui
│   │   │                   ├── controller
│   │   │                   │   ├── CustomerController.java
│   │   │                   │   ├── TransactionController.java
│   │   │                   │   ├── UserController.java
│   │   │                   │   └── WalletController.java
│   │   │                   └── model
│   │   │                       ├── request
│   │   │                       │   ├── CustomerRequestModel.java
│   │   │                       │   ├── TransactionRequestModel.java
│   │   │                       │   ├── UserLoginRequestModel.java
│   │   │                       │   ├── UserRequestModel.java
│   │   │                       │   └── WalletRequestModel.java
│   │   │                       └── response
│   │   │                           └── UserRes.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── dev
│                   └── TekanaeWallet
│                       └── TekanaEWalletApplicationTests.java
└── target
    ├── classes
    │   ├── application.properties
    │   └── com
    │       └── dev
    │           └── TekanaeWallet
    │               ├── exeptions
    │               │   ├── ErrorMessage.class
    │               │   ├── TekanaEWalletException.class
    │               │   └── TekanaEWalletExceptionHandler.class
    │               ├── io
    │               │   ├── entity
    │               │   │   ├── CustomerEntity.class
    │               │   │   ├── TransactionEntity.class
    │               │   │   ├── UserEntity.class
    │               │   │   └── WalletEntity.class
    │               │   └── repository
    │               │       ├── CustomerRepository.class
    │               │       ├── TransactionRepository.class
    │               │       ├── UserRepository.class
    │               │       └── WalletRepository.class
    │               ├── security
    │               │   ├── AppProperties.class
    │               │   ├── AuthenticationFilter.class
    │               │   ├── AuthorizationFilter.class
    │               │   ├── SecurityConstants.class
    │               │   ├── WebCorsMapping.class
    │               │   └── WebSecurity.class
    │               ├── service
    │               │   ├── CustomerService.class
    │               │   ├── impl
    │               │   │   ├── CustomerServiceImpl.class
    │               │   │   ├── TransactionServiceImpl.class
    │               │   │   ├── UserServiceImpl.class
    │               │   │   └── WalletServiceImpl.class
    │               │   ├── TransactionService.class
    │               │   ├── UserService.class
    │               │   └── WalletService.class
    │               ├── shared
    │               │   ├── dto
    │               │   │   ├── CustomerDto.class
    │               │   │   └── UserDto.class
    │               │   ├── TransactionType.class
    │               │   └── Utils.class
    │               ├── SpringApplicationContext.class
    │               ├── Swagger2UiConfiguration.class
    │               ├── TekanaEWalletApplication.class
    │               └── ui
    │                   ├── controller
    │                   │   ├── CustomerController.class
    │                   │   ├── TransactionController.class
    │                   │   ├── UserController.class
    │                   │   └── WalletController.class
    │                   └── model
    │                       ├── request
    │                       │   ├── CustomerRequestModel.class
    │                       │   ├── TransactionRequestModel.class
    │                       │   ├── UserLoginRequestModel.class
    │                       │   ├── UserRequestModel.class
    │                       │   └── WalletRequestModel.class
    │                       └── response
    │                           └── UserRes.class
    └── generated-sources
        └── annotations

```

### Database Design

Database used: MySQL
![Database-design](https://github.com/mico007/tekana_eWallet/assets/58144795/55e474e2-8dbe-479b-bfa1-2ded4754a677)



