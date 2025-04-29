## 📘 API Endpoints Summary

### 🔹 Persons

| Method | Endpoint                    | Description                                      | Request Body                           | Response Status |
|--------|-----------------------------|--------------------------------------------------|----------------------------------------|------------------|
| POST   | `/api/persons`              | Create a new customer (with main address)        | `PersonDTO`                            | 201 Created      |
| GET    | `/api/persons/{id}`         | Get customer by ID                               | –                                      | 200 OK / 404     |
| GET    | `/api/persons/search`       | Search customer by name or ID number             | – (`?name=value&identification=123`)   | 200 OK / 404     |
| PUT    | `/api/persons/{id}`         | Update customer data                             | `PersonUpdateDTO`                      | 200 OK / 404     |
| DELETE | `/api/persons/{id}`         | Delete a customer                                | –                                      | 204 No Content   |

---

### 🔹 Addresses

| Method | Endpoint                          | Description                                | Request Body      | Response Status |
|--------|-----------------------------------|--------------------------------------------|-------------------|------------------|
| POST   | `/api/{personId}/addresses`       | Add a new address to a customer            | `AddressDTO`      | 201 Created      |
| GET    | `/api/{personId}/addresses`       | List all addresses for a customer          | –                 | 200 OK / 404     |

---

### 🔹 PersonDTO
```json
{
    "id": 1,
    "identificationType": "DNI",
    "identificationNumber": "17241697112",
    "names": "Jonathan Mejía",
    "email": "jonathan38m@gmail.com",
    "cellphone": "0963855715",
    "mainProvidence": "Pichincha",
    "mainAddress": "Main Street Oe1",
    "mainCity": "Quito"
}

### 🔹 PersonUpdateDTO
```json
{
    "identificationNumber": "1723162712",
    "name": "Jonathan Mejía Salazar",
    "email": "jonathan38m@gmail.com",
    "cellphone": "0963855725"
}

---

### 🔹 Error Responses

- `400 Bad Request`: Invalid input data
- `404 Not Found`: Resource not found
- `409 Conflict`: Duplicate entry (e.g. unique constraint)
- `500 Internal Server Error`: Unexpected server error
