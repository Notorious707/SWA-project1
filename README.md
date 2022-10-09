# SWA-project1
Once you successfully go through the authorization, you can go the url “/trigger” which starts the batch and data from the csv file will be written to the database. Then you can check the data though “/getall” url.

# SWA-project1 commands to start application:

You should have installed docker to use this command:

```
docker-compose up --build
```

# SWA-project1 default admin user credentials:

username: webper <br />
password: sawebper

# Base url

```
(http://localhost:8080)
```

- Auth

  - [Register](#Register)
  - [Login](#Login)
  
- Students
  - [Batch Job](#batch-job) 
  - [All data](#all-data)

# SWA-project1 API Document:

# Auth

## Login

### Parameters

| Name     | Mandatory | Example    |
| -------- | --------- | ---------- |
| username | yes       | "webper"   |
| password | yes       | "sawebper" |

### Responses

| HTTP Code | Code | Message                                 | Data  |
| --------- | ---- | --------------------------------------- | ----- |
| 200       | 0    | Success                                 | true  |
| 400       | -1   | User with this username already exists! | false |

### Example requests/responses

#### Request:

```shell script
curl -L -X POST '{{host}}/auth/signup'
--data-raw {
    "username":"webper",
    "password":"sawebper"
}
```

#### Success response:

```json
{
    "code": 0,
    "message": "Success",
    "data": {
        "success": true
    }
}
```

## Login

### Parameters

| Name     | Mandatory | Example    |
| -------- | --------- | ---------- |
| username | yes       | "webper"   |
| password | yes       | "sawebper" |

### Responses

| HTTP Code | Code | Message         | Data  |
| --------- | ---- | --------------- | ----- |
| 200       | 0    | Success         | token |
| 400       | -1   | Bad credentials | false |

### Example requests/responses

#### Request:

```shell script
curl -L -X POST '{{host}}/auth/signin'
--data-raw {
    "username":"webper",
    "password":"sawebper"
}
```

#### Success response:

```json
{
    "code": 0,
    "message": "Success",
    "data": {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3ZWJwZXIiLCJpYXQiOjE2NjUyOTU0OTMsImV4cCI6MTY2NTI5NjM5M30.lZjK3kWsoVf168a43DP8ZJXaxx5v4mQPJOc2MLinhKVMC6pX49L0BbffX4DHe-zHpFwWFlw3EHbjrdrQ1XzumA"
    }
}
```


## Assign admin

### Parameters

| Name   | Mandatory | Example |
| ------ | --------- | ------- |
| userId | yes       | 1       |

### Responses

| HTTP Code | Code | Message                           | Data  |
| --------- | ---- | --------------------------------- | ----- |
| 200       | 0    | Success                           | token |
| 400       | -1   | User with this id does not exist! | false |

### Example requests/responses

#### Request:

```shell script
curl -L -X POST '{{host}}/auth/assign-admin'
--data-raw {
   "userId":2
}
```

#### Success response:

```json
{
    "code": 0,
    "message": "Success",
    "data": true
}
```


# Batch Job

## Students data save to DB

### Parameters

### Responses

| HTTP Code | Code | Message | Data |
| --------- | ---- | ------- | ---- |
| 200       | 0    | Success |      |

### Example requests/responses

#### Request:

```shell script
curl -L -X GET '{{host}}/trigger'
```

#### Success response:

```
1
```

# Students

## All Data

### Parameters

### Responses

| HTTP Code | Code | Message | Data |
| --------- | ---- | ------- | ---- |
| 200       | 0    | Success | []   |

### Example requests/responses

#### Request:

```shell script
curl -L -X GET '{{host}}/getall'
```

#### Success response:

```json
[
    {
        "id": 1,
        "first": "John",
        "last": "Cena",
        "gpa": 3.8,
        "dob": "2002-01-01"
    },
    {
        "id": 2,
        "first": "Rocco",
        "last": "Cena",
        "gpa": 3.8,
        "dob": "2001-01-01"
    },
    {
        "id": 3,
        "first": "John",
        "last": "Cena",
        "gpa": 3.8,
        "dob": "2000-01-01"
    },
    {
        "id": 4,
        "first": "John",
        "last": "Cena",
        "gpa": 3.8,
        "dob": "1999-01-01"
    },
    {
        "id": 5,
        "first": "John",
        "last": "Cena",
        "gpa": 3.8,
        "dob": "1998-01-01"
    },
    {
        "id": 6,
        "first": "John",
        "last": "Cena",
        "gpa": 3.8,
        "dob": "1997-01-01"
    }
]
```