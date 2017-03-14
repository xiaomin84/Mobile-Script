@Grab(group='org.slf4j', module='slf4j-simple', version='1.7.21')
@Grab(group='org.ajoberstar', module='grgit', version='1.8.0')

import org.ajoberstar.grgit.*
def grgit = Grgit.open(dir:$WORKSPACE)
def map = [COMPUTE_VAR2: grgit.head().abbreviatedId]
print map
