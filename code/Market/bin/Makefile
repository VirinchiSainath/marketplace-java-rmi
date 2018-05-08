JC = javac -cp ".:./mysql-connector-java-5.1.46.jar"
J = java -cp ".:./mysql-connector-java-5.1.46.jar"
JFLAGS = -Djava.security.policy=policy
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	com/marketplace/controller/*.java \
	com/marketplace/entity/*.java \
	com/marketplace/model/*.java \
	com/marketplace/view/*.java \
	com/marketplace/dispatcher/*.java \
	com/marketplace/commands/*.java \
	com/marketplace/dispatcher/abstractFactory/*.java \
	com/marketplace/service/*.java \
	com/marketplace/daoservice/*.java \
	com/marketplace/db/*.java \

default:  clean classes

classes: $(CLASSES:.java=.class)
	
clean:
	$(RM) com/marketplace/controller/*.class
	$(RM) com/marketplace/entity/*.class
	$(RM) com/marketplace/model/*.class
	$(RM) com/marketplace/view/*.class
	$(RM) com/marketplace/dispatcher/*.class
	$(RM) com/marketplace/commands/*.class
	$(RM) com/marketplace/dispatcher/abstractFactory/*.class 
	$(RM) com/marketplace/service/*.class
	$(RM) com/marketplace/daoservice/*.class
	$(RM) com/marketplace/db/*.class
	
runServer:
	$(J) $(JFLAGS) com/marketplace/model/Server
	
runClient:
	$(J) $(JFLAGS) com/marketplace/view/View
	
runRegistry:
	rmiregistry 2323 &
