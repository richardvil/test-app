# test-app

Save Group: 

```
curl -X "POST" "http://localhost:8080/group" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Test group 1"}'

{"id":1,"name":"Test group 1"}

curl -X "GET" "http://localhost:8080/group"

[{"id":1,"name":"Test group 1","people":[]}]

```

Save some persons

```
curl -X "POST" "http://localhost:8080/person" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Person A"}'
{"id":1,"name":"Person A"}

curl -X "POST" "http://localhost:8080/person" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Person B"}'
{"id":2,"name":"Person B"}

curl -X "POST" "http://localhost:8080/person" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Person C"}'
{"id":3,"name":"Person C"}

curl -X "POST" "http://localhost:8080/person" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Person D"}'
{"id":4,"name":"Person D"}

curl -X "GET" "http://localhost:8080/person"

[{"id":1,"name":"Person A"},{"id":2,"name":"Person B"},{"id":3,"name":"Person C"},{"id":4,"name":"Person D"}]
```

Save other Group associated with two persons

```
curl -X "POST" "http://localhost:8080/group" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Test group 2","people":[{"id":1},{"id":2}]}'

curl -X "GET" "http://localhost:8080/group/2"
{"id":2,"name":"Test group 2","people":[{"id":1},{"id":2}]}
```

Modify group 1 which was orginally saved without persons. Associate it with two persons:

```
curl -X "PUT" "http://localhost:8080/group/1" -H "Content-Type: application/json; charset=utf-8" -d '{"people":[{"id":3},{"id":4}]}'
{"id":1,"name":"Test group 1","people":[{"id":4},{"id":3}]}

curl -X "GET" "http://localhost:8080/group/1"
{"id":1,"name":"Test group 1","people":[{"id":3},{"id":4}]}
```

Here is the problem:

```
curl -X "PUT" "http://localhost:8080/group/1" -H "Content-Type: application/json; charset=utf-8" -d '{"people":[{"id":3}]}'

{"id":1,"name":"Test group 1","people":[{"id":3},{"id":4}]}

curl -X "GET" "http://localhost:8080/group/1"

{"id":1,"name":"Test group 1","people":[{"id":4},{"id":3}]}
```

expected: 

```
{"id":1,"name":"Test group 1","people":[{"id":3}]}
```

instead of 

```
{"id":1,"name":"Test group 1","people":[{"id":4},{"id":3}]}
```
