#!/usr/bin/env groovy
@Grab(group='org.slf4j', module='slf4j-simple', version='1.7.21')
@Grab(group='org.ajoberstar', module='grgit', version='1.8.0')

import org.ajoberstar.grgit.*
def grgit = Grgit.open(dir:'./')
def history = grgit.log {
   range 'HEAD', '2.0'
 }
history.each { commit ->
 printfln commit.shortMessage
}

def map = [COMPUTE_VAR2: grgit.head().abbreviatedId]

println map

def workspace = System.getenv("VersionName")

println workspace


File propsFile = new File('./version.properties')
def newProps = new Properties()

newProps.setProperty('SFTP_USER_HASH', 'woo')
newProps.setProperty('GD_SFTP_URI', 'ftp://woo.com')

propsFile.withWriterAppend( 'UTF-8' ) { fileWriter ->
    newProps.each { key, value ->
        fileWriter.writeLine "$key=$value"
    }
}
