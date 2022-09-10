## Blog(API)
A blog Restful APIs which would be consumed by a client application.

### Tools
* Spring Boot 
* PostgreSQL 
* Git 
* Spring Data JPA 
* JUnit/Mockito
---
### Testing the API endpoints

### Register a new user
* Endpoint: `http://localhost:8080/api/user/register`

### Payload

```
curl --location --request POST 'http://localhost:8080/api/user/register' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=FBE4EE081F0223C114A7C0FFA3F967D3' \
--data-raw '{
    "name" : "Chioma",
    "email" : "chommy@gmail.com",
    "role" : "user",
    "password" : "1234"
}'
```
### Response
```json
{
    "message": "success",
    "timeStamp": "2022-09-10T23:18:33.251124",
    "user": {
        "id": 10,
        "name": "Chioma",
        "email": "chommy@gmail.com",
        "role": "user",
        "password": "1234",
        "createdAt": "2022-09-10T23:18:33.16656",
        "updatedAt": "2022-09-10T23:18:33.166599",
        "posts": [],
        "comments": [],
        "likes": []
    }
}
```
---
### Login a user
* Endpoint: `http://localhost:8080/api/user/login`

### Payload
```
curl --location --request GET 'http://localhost:8080/api/user/login' \
--header 'Cookie: JSESSIONID=FBE4EE081F0223C114A7C0FFA3F967D3'
```
### Response
```json
{
    "message": "success",
    "timeStamp": "2022-09-10T23:31:45.615399"
}
```
---
### Create a post
* Endpoint: `http://localhost:8080/api/post/create_post`

### Payload
```
curl --location --request POST 'http://localhost:8080/api/user/create_post' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=FBE4EE081F0223C114A7C0FFA3F967D3' \
--data-raw '{
    "title" : "Code reviews",
    "content" : "Do your code reviews with Emmanuel",
    "featuredImage" : "code.png",
    "user_id" : 3
}'
```
### Response
```json
{
    "message": "success",
    "timeStamp": "2022-09-10T23:33:54.360884",
    "post": {
        "id": 5,
        "title": "Code reviews",
        "content": "Do your code reviews with Emmanuel",
        "slug": "code-reviews",
        "featuredImage": "code.png",
        "createdAt": "2022-09-10T23:33:54.293408",
        "updatedAt": "2022-09-10T23:33:54.293473",
        "comments": [],
        "likes": []
    }
}
```
---
### Comment on a post
* Endpoint: `http://localhost:8080/api/user/comment/3/4`

### Payload
```
curl --location --request POST 'http://localhost:8080/api/user/comment/3/4' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=FBE4EE081F0223C114A7C0FFA3F967D3' \
--data-raw '{
    "comment" : "call SA"
}'
```
### Response
```json
{
    "message": "success",
    "comment": {
        "id": 3,
        "comment": "call SA",
        "createdAt": "2022-09-10T23:36:19.361702",
        "updatedAt": "2022-09-10T23:36:19.361857"
    },
    "timeStamp": "2022-09-10T23:36:19.440764",
    "post": {
        "id": 4,
        "title": "Code reviews",
        "content": "Do your code reviews with Emmanuel",
        "slug": "code-reviews",
        "featuredImage": "code.png",
        "createdAt": "2022-09-07T23:36:03.612049",
        "updatedAt": "2022-09-07T23:36:03.612094",
        "comments": [
            {
                "id": 2,
                "comment": "clean code",
                "createdAt": "2022-09-08T00:06:53.154589",
                "updatedAt": "2022-09-08T00:06:53.154679"
            },
            {
                "id": 3,
                "comment": "Interesting algorithm",
                "createdAt": "2022-09-10T23:36:19.361702",
                "updatedAt": "2022-09-10T23:36:19.361857"
            }
        ],
        "likes": []
    }
}
```
---
### Like a post
* Endpoint: `http://localhost:8080/api/user/like/1/2`

### Payload
```
curl --location --request POST 'http://localhost:8080/api/user/like/1/2' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=FBE4EE081F0223C114A7C0FFA3F967D3' \
--data-raw '{
    "liked" : true
}'
```
### Response
```json
{
  "message": "success",
  "timeStamp": "2022-09-10T23:39:30.881377",
  "post": {
    "id": 2,
    "title": "Code review",
    "content": "All about how to cook soup, stew and sauce",
    "slug": "code-review",
    "featuredImage": "chommy.png",
    "createdAt": "2022-09-07T18:35:23.844478",
    "updatedAt": "2022-09-07T18:35:23.844603",
    "comments": [],
    "likes": [
      {
        "id": 2,
        "createdAt": "2022-09-10T23:39:30.78183",
        "updatedAt": "2022-09-10T23:39:30.781892",
        "liked": true
      }
    ]
  },
  "like": {
    "id": 6,
    "createdAt": "2022-09-10T23:39:30.78183",
    "updatedAt": "2022-09-10T23:39:30.781892",
    "liked": true
  },
  "totalLikes": 1
}
```
---
### Search for a post
* Endpoint: `http://localhost:8080/api/user/searchPost/review`

### Payload
```
curl --location --request GET 'http://localhost:8080/api/user/searchPost/review' \
--header 'Cookie: JSESSIONID=FBE4EE081F0223C114A7C0FFA3F967D3'
```

### Response
```json
{
  "message": "success",
  "timeStamp": "2022-09-10T23:42:30.456149",
  "posts": [
    {
      "id": 2,
      "title": "Code review",
      "content": "All about how to cook soup, stew and sauce",
      "slug": "code-review",
      "featuredImage": "chommy.png",
      "createdAt": "2022-09-07T18:35:23.844478",
      "updatedAt": "2022-09-07T18:35:23.844603",
      "comments": [],
      "likes": [
        {
          "id": 6,
          "createdAt": "2022-09-10T23:39:30.78183",
          "updatedAt": "2022-09-10T23:39:30.781892",
          "liked": true
        },
        {
          "id": 11,
          "createdAt": "2022-09-10T23:41:12.193859",
          "updatedAt": "2022-09-10T23:41:12.193904",
          "liked": true
        }
      ]
    },
    {
      "id": 3,
      "title": "Code review",
      "content": "All about how to cook soup, stew and sauce",
      "slug": "code-review",
      "featuredImage": "chommy.png",
      "createdAt": "2022-09-07T18:41:00.475155",
      "updatedAt": "2022-09-07T18:41:00.475251",
      "comments": [],
      "likes": [
        {
          "id": 7,
          "createdAt": "2022-09-10T23:40:43.649901",
          "updatedAt": "2022-09-10T23:40:43.649964",
          "liked": true
        }
      ]
    },
    {
      "id": 4,
      "title": "Code reviews",
      "content": "Do your code reviews with Emmanuel",
      "slug": "code-reviews",
      "featuredImage": "code.png",
      "createdAt": "2022-09-07T23:36:03.612049",
      "updatedAt": "2022-09-07T23:36:03.612094",
      "comments": [
        {
          "id": 2,
          "comment": "call SA",
          "createdAt": "2022-09-08T00:06:53.154589",
          "updatedAt": "2022-09-08T00:06:53.154679"
        },
        {
          "id": 3,
          "comment": "call SA",
          "createdAt": "2022-09-10T23:36:19.361702",
          "updatedAt": "2022-09-10T23:36:19.361857"
        }
      ],
      "likes": [
        {
          "id": 8,
          "createdAt": "2022-09-10T23:40:50.846908",
          "updatedAt": "2022-09-10T23:40:50.846949",
          "liked": true
        }
      ]
    },
    {
      "id": 5,
      "title": "Code reviews",
      "content": "Do your code reviews with Emmanuel",
      "slug": "code-reviews",
      "featuredImage": "code.png",
      "createdAt": "2022-09-10T23:33:54.293408",
      "updatedAt": "2022-09-10T23:33:54.293473",
      "comments": [],
      "likes": [
        {
          "id": 9,
          "createdAt": "2022-09-10T23:40:57.5149",
          "updatedAt": "2022-09-10T23:40:57.514945",
          "liked": true
        }
      ]
    }
  ]
}
```
---
### Get comments for a post

* Endpoint: `http://localhost:8080/api/user/searchComment/yummy`
### Payload
```
curl --location --request GET 'http://localhost:8080/api/user/searchComment/yum' \
--header 'Cookie: JSESSIONID=FBE4EE081F0223C114A7C0FFA3F967D3'
```

### Response
```json
{
  "message": "success",
  "timeStamp": "2022-09-10T23:45:56.443282",
  "comments": [
    {
      "id": 1,
      "comment": "Mmmmm, Yummy",
      "createdAt": "2022-09-07T03:30:53.690146",
      "updatedAt": "2022-09-07T03:30:53.690195"
    }
  ]
}
```
---
### Get posts by id

* Endpoint: `http://localhost:8080/api/user/post/4`
### Payload
```
curl --location --request GET 'http://localhost:8080/api/user/post/4' \
--header 'Cookie: JSESSIONID=FBE4EE081F0223C114A7C0FFA3F967D3'
```

### Response
```json
{
  "id": 4,
  "title": "Code reviews",
  "content": "Do your code reviews with Emmanuel",
  "slug": "code-reviews",
  "featuredImage": "code.png",
  "createdAt": "2022-09-07T23:36:03.612049",
  "updatedAt": "2022-09-07T23:36:03.612094",
  "comments": [
    {
      "id": 2,
      "comment": "call SA",
      "createdAt": "2022-09-08T00:06:53.154589",
      "updatedAt": "2022-09-08T00:06:53.154679"
    },
    {
      "id": 3,
      "comment": "call SA",
      "createdAt": "2022-09-10T23:36:19.361702",
      "updatedAt": "2022-09-10T23:36:19.361857"
    }
  ],
  "likes": [
    {
      "id": 8,
      "createdAt": "2022-09-10T23:40:50.846908",
      "updatedAt": "2022-09-10T23:40:50.846949",
      "liked": true
    }
  ]
}
```
