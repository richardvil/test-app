package test.app

import grails.rest.Resource

@Resource(uri='/group')
class Group {

    String name

    static hasMany = [people: Person]

    static constraints = {
        people nullable: true
    }

    static mapping = {
        table '`group`'
    }
}
