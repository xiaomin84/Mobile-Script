#!/usr/bin/env groovy
@Grab(group='org.slf4j', module='slf4j-simple', version='1.7.21')
@Grab(group='org.ajoberstar', module='grgit', version='1.8.0')

import org.ajoberstar.grgit.*

def credentials = new Credentials('git', 'git')

def grgit = Grgit.open('./',credentials)

grgit.tag.add(name: "${1}", message: "Release daily build ${1}",force: true)

grgit.push(tags: true, remote: 'MainProject')

grgit.close()
