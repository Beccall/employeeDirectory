# Employee Directory

REST API that allows clients to add, update, edit and delete employees from their directory.

## Functionality

- POST /employees
    - role must be Manager, Developer or QA Tester
    - ManagerId is required for Developer and QA Tester employees
- GET /employees?lowRange={low}&highRange={high}
    - Gets a list of employees whose salary falls within a set range
- PUT /employees/{id}
- DELETE /employees/{id}

```
{
  "id": 1,
  "name": "Billy Bob",
  "role": "Manager",
  "dateOfBirth": "01/01/2001",
  "emailAddress": "123@gmail.com",
  "salary": 100000,
  "managerId": null
}
```