# Classmates backend

Classmates - social network for photos and communication.

## Other parts:
[Frontend repository](https://pip.pypa.io/en/stable/)

[Design in figma](https://www.figma.com/file/0ircFNNzFCJgQgqTf5WosA/%D0%9E%D0%B4%D0%BD%D0%BE%D0%BA%D0%BB%D0%B0%D1%81%D1%81%D0%BD%D0%B8%D0%BA%D0%B8?node-id=0%3A1)


## Api methods

### Sign in for users

```
/api/auth/signin
```
```json
{
	"username": "user",
	"password": "password"
}
```
### Sign up for new users
```
/api/auth/signup
```
```json
{
	"username": "user",
	"password": "password",
        "email": "user@mail.com",
        "role": ["user"]
}
```
### Get special content for different roles (Request body is empty. AccessToken of authorised user needed)
```
/api/test/all
/api/test/user
/api/test/mod
/api/test/admin
```

## License
[MIT](https://github.com/ESHagibalov/Classmates/blob/master/LICENSE)
