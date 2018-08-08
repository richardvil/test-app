package test.app

import grails.rest.Resource

@Resource(uri='/person')
class Person {

    String name
    Group group

    static constraints = {
        group nullable: true
    }
}
