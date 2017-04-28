class TaskPool {  
        def map = [:]  
        def push(String name,Task t) {  
                map.put(name,t)  
        }  
  
        def get(String name) {  
                map.get(name)  
        }  
}  
  
taskPool = new TaskPool()  

class Task {  
        def name  
        def closure  
        def leftShift(c) {  
                this.closure = c  
                this  
        }
        def execute() {  
                println "task $name start >>"  
                closure()  
                println "task $name end <<"  
        }  
}  

def invokeMethod(String name,args) {  
        println "call method $name()"  
        t = taskPool.get(name)  
        if (t == null) {  
                if (args[0] instanceof Closure) {  
                        t = new Task(name:name,closure:args[0])  
                }
		else if(args[0] instanceof Map)
		{
                        t= args[0].type
			t.name = name
                        c= args[1] as Closure
			t.closure = c
                }  
        }  
        t  
}  

def getProperty(String name) { 
 

        def t  
        try {  
                t  = super.getProperty(name)  
        } catch(e) { 
               if(name == "copyFile")
{ 
                println "elfsf"
}  
                t = taskPool.get(name)  
                if (t == null) { 
                        t = new Task(name:name,closure:{println "dddddddddddd"})  
                }     
        }     
        t             
}    


def task(Task t) { 
 
        taskPool.push(t.name,t)  
        t.execute()  
	
}  
/*
task sayHello.leftShift {  
        println "hello!! david!"  
}  

task sayHello.leftShift {
        println "hello2!! david!"
}
*/


task copyFile (type:aaa, {
   name = hello
   println "copyiddddddddddFile $copyFile.name"
})

println copyFile.name


/*

class A {
  def callMethodA() {println "call A"}
}
class B {
  def callMethodB() {println "call B"}
}

A.metaClass.mixin (B)
a = new A()
a.callMethodA()
a.callMethodB()
assert (a instanceof A)
*/
