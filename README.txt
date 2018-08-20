Simple CRUD REST API as test task for Provectus.

In DBInitializer.java 2 users are created: admin(login/password => admin/admin), user(login/password => login/password).
You could use them to test API.

For authorization use POST /login with bode {"login": ${user_login}, "password": "${user_password}"}
In response header 'Authorization' you will receive token that should be used for authentication for next requests.

By default only one company is created(COMPANY_ONE) and simple user attached to it.
All endpoints except /companies/for-current and /users/current are closed to 'USER' role.
/users/current returns information about user, /companies/for-current returns information about user's company.

P.S.: I didn't use profiles, swagger, mapping to DTO, unit-tests and other useful things, because the are out of scope of the task.