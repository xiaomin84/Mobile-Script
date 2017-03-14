#!/usr/bin/env groovy
@Grab(group='org.slf4j', module='slf4j-simple', version='1.7.21')
@Grab(group='org.ajoberstar', module='grgit', version='1.8.0')

import org.ajoberstar.grgit.*

def release = System.getenv("ReleaseName")
def grgit = Grgit.open(dir:'./')

def history = grgit.log {
   range "$release", 'HEAD'
 }

println 'number:'+ history.size()

def VersionName = "$release" + '-' + history.size() + '-' + grgit.head().abbreviatedId


println VersionName

File propsFile = new File('./version.properties')
def newProps = new Properties()

newProps.setProperty('VersionName', "$VersionName")

propsFile.withWriterAppend( 'UTF-8' ) { fileWriter ->
    newProps.each { key, value ->
        fileWriter.writeLine "$key=$value"
    }
}

grgit.close()
