## What's that?

It is a small **akka** cluster (3 nodes on port 2551, 2552 and 2553) with java and scala [ClusterClient](http://doc.akka.io/docs/akka/snapshot/scala/cluster-client.html#cluster-client).

Cluster nodes joins cluster system with the name *ClusterSystem* and register Actor *Greeter* to be available thru the *Receptionist* for the client.

Java and Scala clients creates own Actor System each. They can communicate with the Actor System created on the cluster via ClusterClient.

Better description you can find in the akka documentation.

## What's inside?
There are 4 small projects:

* akka cluster (scala, sbt) 
* simple protocol used in cluster (java, sbt)
* ClusterClient implemented in scala (scala, sbt)
* ClusterClient implemented in java (java, maven)

## How to run?

### Precondition
You need to install **maven** first. It's possible to run all of examples with *sbt* but I want to test also java with maven project.

Steps to run

\#1 Publish protocol to maven local repository (**you have to do it once**)

```
cd akka-mini-cluster-protocol

sbt publish
```

\#2 Start cluster
 
```
cd akka-mini-cluster

sbt clean run
```

\#3-a Run Scala ClusterClient
 
```
cd akka-mini-cluster-scala-client

sbt clean run
```

\#3-b or run Java ClusterClient
 
```
cd akka-mini-cluster-java-client

mvn clean compile exec:java
```
 


