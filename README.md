## Classmates backend

Classmates - social network for photos and communication.

## Other parts:
[Frontend repository](https://pip.pypa.io/en/stable/)

[Design in figma](https://www.figma.com/file/0ircFNNzFCJgQgqTf5WosA/%D0%9E%D0%B4%D0%BD%D0%BE%D0%BA%D0%BB%D0%B0%D1%81%D1%81%D0%BD%D0%B8%D0%BA%D0%B8?node-id=0%3A1)


## API

### Sign in/Sign up actions
---
#### Sign in for users

```
POST /api/auth/signin
```
```json
{
	"username": "user",
	"password": "password"
}
```
#### Sign up for new users
```
POST /api/auth/signup
```
```json
{
	"username": "user",
	"password": "password",
        "email": "user@mail.com",
        "role": ["user"]
}
```
#### Get special content for different roles (AccessToken of authorised user needed)
```
GET /api/test/all
GET /api/test/user
GET /api/test/mod
GET /api/test/admin
```
### Search module
---
#### Get user by username
```
POST /api/search/user
```
```json
{
	"username": "user"
}
```

#### Get user by id
```
GET /api/profile/get/{id}
```

### Profile actions
---
#### Subscribe/unsubscribe user
```
POST /api/profile/subscribe/{channelId}
```

#### Get posts of other user by id
```
GET /api/profile/post/get/{id}
```

#### Delete post of current user
```
GET /api/profile/post/delete_my/{post_id}
```

#### Add post for current user
```
POST /api/profile/post/add_post
```
Request params:
```
String content
File image
```



## License
[MIT](https://github.com/ESHagibalov/Classmates/blob/master/LICENSE)
