#!/usr/bin/env groovy
@Grab(group='org.slf4j', module='slf4j-simple', version='1.7.21')
@Grab(group='org.ajoberstar', module='grgit', version='1.8.0')

import org.ajoberstar.grgit.*
def VersionName = 1.0.1
def Credentials = credential
credential.username = 'git'
credential.password = 'git'

def grgit = Grgit.open(dir:'./',crendential)

grgit.tag.add(name: "$VersionName", message: "Release daily build $VersionName",force: true)

grgit.push(tags: true)

newProps.setProperty('VersionName', "$VersionName")

grgit.close()
