package test.app

import grails.plugin.json.builder.JsonOutput
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.testing.mixin.integration.Integration
import groovy.json.JsonSlurper
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@Integration
class ReplaceAssociationSpec extends Specification {

    @Shared
    RestBuilder rest = new RestBuilder()

    @Shared
    JsonSlurper jsonSlurper = new JsonSlurper()

    private String url(String path) {
        "http://localhost:${serverPort}${path}"
    }

    def "Verify a group can be saved"() {
        when:
        RestResponse resp = rest.get(url("/group"))

        then:
        resp.status == 200
        resp.json == []

        when:
        resp = rest.post(url("/group")) {
            json JsonOutput.toJson([name:"Test group 1"])
        }
        then:
        resp.status == 201

        when:
        resp = rest.get(url("/group"))

        then:
        resp.status == 200
        resp.json.size() == 1
    }

    def "Verify two persons can be saved"() {
        when:
        RestResponse resp = rest.get(url("/person"))

        then:
        resp.status == 200
        resp.json == []

        when:
        resp = rest.post(url("/person")) {
            json JsonOutput.toJson([name:"Person A"])
        }
        then:
        resp.status == 201

        when:
        resp = rest.get(url("/person"))

        then:
        resp.status == 200
        resp.json.size() == 1

        when:
        resp = rest.post(url("/person")) {
            json JsonOutput.toJson([name:"Person B"])
        }
        then:
        resp.status == 201

        when:
        resp = rest.get(url("/person"))

        then:
        resp.status == 200
        resp.json.size() == 2
    }

    def "verify multiple persons can be associated to a group"() {

        given:
        RestResponse resp = rest.get(url("/person"))
        List<Long> personIds = resp.json*.id
        resp = rest.get(url("/group"))
        Long groupId = resp.json.first().id

        expect:
        personIds.size() == 2
        groupId != null

        println JsonOutput.toJson([people: personIds])
        when:
        resp = rest.put(url("/group/$groupId")) {
            contentType("application/json")
            json JsonOutput.toJson([
                    people: personIds.collect { [id: it] }
            ])
        }

        then:
        resp.status == 200

        when:
        resp = rest.get(url("/group"))
        List<Long> personsAssociatedToGroup = resp.json.first().people*.id

        then:
        personsAssociatedToGroup.size() == 2

    }

    def "verify persons associated in a group can be replaced with a collection of less size"() {

        given:
        RestResponse resp = rest.get(url("/person"))
        List<Long> personIds = resp.json*.id
        resp = rest.get(url("/group"))
        Long groupId = resp.json.first().id

        expect:
        personIds.size() == 2
        groupId != null

        when:
        resp = rest.put(url("/group/$groupId")) {
            json JsonOutput.toJson([
                    people: [personIds.first()].collect { [id: it] }
            ])
        }

        then:
        resp.status == 200

        when:
        resp = rest.get(url("/group"))
        List<Long> personsAssociatedToGroup = resp.json.first().people*.id

        then:
        personsAssociatedToGroup.size() == 1
    }
}
