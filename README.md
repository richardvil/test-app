# test-app

curl -X "POST" "http://localhost:8080/group" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Test group 1"}'
curl -X "GET" "http://localhost:8080/group"

curl -X "POST" "http://localhost:8080/person" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Person A"}'
curl -X "POST" "http://localhost:8080/person" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Person B"}'
curl -X "POST" "http://localhost:8080/person" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Person C"}'
curl -X "POST" "http://localhost:8080/person" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Person D"}'
curl -X "GET" "http://localhost:8080/person"

curl -X "POST" "http://localhost:8080/group" -H "Content-Type: application/json; charset=utf-8" -d '{"name":"Test group 2","people":[{"id":1},{"id":2}]}'
curl -X "GET" "http://localhost:8080/group/2"

curl -X "PUT" "http://localhost:8080/group/1" -H "Content-Type: application/json; charset=utf-8" -d '{"people":[{"id":3},{"id":4}]}'
curl -X "GET" "http://localhost:8080/group/1"
curl -X "PUT" "http://localhost:8080/group/1" -H "Content-Type: application/json; charset=utf-8" -d '{"people":[{"id":3}]}' -> Here is the problem
curl -X "GET" "http://localhost:8080/group/1"
