# test-app

```
curl -X "POST" "http://localhost:8080/group" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Test group 1"}'
response: {"id":1,"name":"Test group 1"}

curl -X "GET" "http://localhost:8080/group"
response: [{"id":1,"name":"Test group 1","people":[]}]
```

```
curl -X "POST" "http://localhost:8080/person" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Person A"}'
response: {"id":1,"name":"Person A"}

curl -X "POST" "http://localhost:8080/person" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Person B"}'
response: {"id":2,"name":"Person B"}

curl -X "POST" "http://localhost:8080/person" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Person C"}'
response: {"id":3,"name":"Person C"}

curl -X "POST" "http://localhost:8080/person" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Person D"}'
response: {"id":4,"name":"Person D"}

curl -X "GET" "http://localhost:8080/person"
response: [{"id":1,"name":"Person A"},{"id":2,"name":"Person B"},{"id":3,"name":"Person C"},{"id":4,"name":"Person D"}]
```

```
curl -X "POST" "http://localhost:8080/group" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Test group 2","people":[{"id":1},{"id":2}]}'
response: {"id":2,"name":"Test group 2","people":[{"id":2},{"id":1}]}

curl -X "GET" "http://localhost:8080/group/2"
response: {"id":2,"name":"Test group 2","people":[{"id":2},{"id":1}]}
```

```
curl -X "PUT" "http://localhost:8080/group/1" -H "Content-Type: application/json; charset=utf-8" -d '{"people":[{"id":3},{"id":4}]}'
response: {"id":1,"name":"Test group 1","people":[{"id":4},{"id":3}]}

curl -X "GET" "http://localhost:8080/group/1"
response: {"id":1,"name":"Test group 1","people":[{"id":4},{"id":3}]}

Here is the problem
curl -X "PUT" "http://localhost:8080/group/1" -H "Content-Type: application/json; charset=utf-8" -d '{"people":[{"id":3}]}'
response: {"id":1,"name":"Test group 1","people":[{"id":3},{"id":4}]}

curl -X "GET" "http://localhost:8080/group/1"
response: {"id":1,"name":"Test group 1","people":[{"id":3},{"id":4}]}
```
