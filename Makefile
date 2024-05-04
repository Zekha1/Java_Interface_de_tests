JC = javac
JCFLAGS = -d build -cp build -implicit:none -sourcepath "src:tmp"
JVM = java
JVMFLAGS = -cp "build;./mariadb-java-client.jar" # Faut remplacer ; par : sous Linux je crois
SRCPATHOUTIL = src/fr/iutfbleau/sae31/outil
BUILDPATHOUTIL = build/fr/iutfbleau/sae31/outil
SRCPATHSYNTHESE = src/fr/iutfbleau/sae31/synthese
BUILDPATHSYNTHESE = build/fr/iutfbleau/sae31/synthese


### Outil (et pas Hooty) .class

$(BUILDPATHOUTIL)/Menu.class: $(SRCPATHOUTIL)/Menu.java $(BUILDPATHOUTIL)/Option.class $(BUILDPATHOUTIL)/ButtonListener.class
	$(JC) $(JCFLAGS) $(SRCPATHOUTIL)/Menu.java

$(BUILDPATHOUTIL)/ButtonListener.class: $(SRCPATHOUTIL)/ButtonListener.java $(BUILDPATHOUTIL)/Protocole.class $(BUILDPATHOUTIL)/ArbreListener.class $(BUILDPATHOUTIL)/TestFini.class
	$(JC) $(JCFLAGS) $(SRCPATHOUTIL)/ButtonListener.java

$(BUILDPATHOUTIL)/TestFini.class: $(SRCPATHOUTIL)/TestFini.java $(BUILDPATHOUTIL)/Protocole.class
	$(JC) $(JCFLAGS) $(SRCPATHOUTIL)/TestFini.java

$(BUILDPATHOUTIL)/Option.class: $(SRCPATHOUTIL)/Option.java
	$(JC) $(JCFLAGS) $(SRCPATHOUTIL)/Option.java

$(BUILDPATHOUTIL)/Protocole.class: $(SRCPATHOUTIL)/Protocole.java
	$(JC) $(JCFLAGS) $(SRCPATHOUTIL)/Protocole.java

$(BUILDPATHOUTIL)/ListeOption.class: $(SRCPATHOUTIL)/ListeOption.java $(BUILDPATHOUTIL)/Option.class
	$(JC) $(JCFLAGS) $(SRCPATHOUTIL)/ListeOption.java

$(BUILDPATHOUTIL)/ArbreOption.class: $(SRCPATHOUTIL)/ArbreOption.java $(BUILDPATHOUTIL)/Option.class
	$(JC) $(JCFLAGS) $(SRCPATHOUTIL)/ArbreOption.java

$(BUILDPATHOUTIL)/ArbreListener.class: $(SRCPATHOUTIL)/ArbreListener.java
	$(JC) $(JCFLAGS) $(SRCPATHOUTIL)/ArbreListener.java

$(BUILDPATHOUTIL)/MissionAcceptee.class: $(SRCPATHOUTIL)/MissionAcceptee.java
	$(JC) $(JCFLAGS) $(SRCPATHOUTIL)/MissionAcceptee.java

$(BUILDPATHOUTIL)/Main.class: $(SRCPATHOUTIL)/Main.java $(BUILDPATHOUTIL)/Protocole.class $(BUILDPATHOUTIL)/ListeOption.class $(BUILDPATHOUTIL)/ArbreOption.class $(BUILDPATHOUTIL)/ArbreListener.class $(BUILDPATHOUTIL)/MissionAcceptee.class $(BUILDPATHOUTIL)/Menu.class
	$(JC) $(JCFLAGS) $(SRCPATHOUTIL)/Main.java

### Synthese .class

$(BUILDPATHSYNTHESE)/TestData.class: $(SRCPATHSYNTHESE)/TestData.java
	$(JC) $(JCFLAGS) $(SRCPATHSYNTHESE)/TestData.java

$(BUILDPATHSYNTHESE)/OptionData.class: $(SRCPATHSYNTHESE)/OptionData.java
	$(JC) $(JCFLAGS) $(SRCPATHSYNTHESE)/OptionData.java

$(BUILDPATHSYNTHESE)/CliqueEnregistreData.class: $(SRCPATHSYNTHESE)/CliqueEnregistreData.java
	$(JC) $(JCFLAGS) $(SRCPATHSYNTHESE)/CliqueEnregistreData.java

$(BUILDPATHSYNTHESE)/DonneesTests.class: $(SRCPATHSYNTHESE)/DonneesTests.java $(BUILDPATHSYNTHESE)/TestData.class $(BUILDPATHSYNTHESE)/CliqueEnregistreData.class $(BUILDPATHSYNTHESE)/OptionData.class 
	$(JC) $(JCFLAGS) $(SRCPATHSYNTHESE)/DonneesTests.java

$(BUILDPATHSYNTHESE)/CamembertPanel.class: $(SRCPATHSYNTHESE)/CamembertPanel.java
	$(JC) $(JCFLAGS) $(SRCPATHSYNTHESE)/CamembertPanel.java

$(BUILDPATHSYNTHESE)/BasculeListener.class: $(SRCPATHSYNTHESE)/BasculeListener.java
	$(JC) $(JCFLAGS) $(SRCPATHSYNTHESE)/BasculeListener.java

$(BUILDPATHSYNTHESE)/BasculeActionListener.class: $(SRCPATHSYNTHESE)/BasculeActionListener.java $(BUILDPATHSYNTHESE)/BasculeListener.class
	$(JC) $(JCFLAGS) $(SRCPATHSYNTHESE)/BasculeActionListener.java

$(BUILDPATHSYNTHESE)/Camembert.class: $(SRCPATHSYNTHESE)/Camembert.java $(BUILDPATHSYNTHESE)/DonneesTests.class $(BUILDPATHSYNTHESE)/CamembertPanel.class $(BUILDPATHSYNTHESE)/BasculeActionListener.class
	$(JC) $(JCFLAGS) $(SRCPATHSYNTHESE)/Camembert.java

$(BUILDPATHSYNTHESE)/Main.class: $(SRCPATHSYNTHESE)/Main.java $(BUILDPATHSYNTHESE)/DonneesTests.class $(BUILDPATHSYNTHESE)/Camembert.class 
	$(JC) $(JCFLAGS) $(SRCPATHSYNTHESE)/Main.java

### Outils .jar

jar-outil: $(BUILDPATHOUTIL)/Main.class
	jar cfm outil.jar Manifest.txt -C build fr

### Synthese .jar

jar-synthese: $(BUILDPATHSYNTHESE)/Main.class
	jar cfm synthese.jar Manifest.txt -C build fr


### Runs depuis les .class

run-outil: $(BUILDPATHOUTIL)/Main.class
	$(JVM) $(JVMFLAGS) fr.iutfbleau.sae31.outil.Main

run-synthese: $(BUILDPATHSYNTHESE)/Main.class
	$(JVM) $(JVMFLAGS) fr.iutfbleau.sae31.synthese.Main 

### Runs depuis les .jar
run-outil-jar: outil.jar
	$(JVM) -jar outil.jar

run-synthese-jar: synthese.jar
	$(JVM) -jar synthese.jar


### Cleans (Linux et Windows)

clean :
	rm -r build/ synthese.jar outil.jar

cleanW:
	del /q build\fr\iutfbleau\sae31\synthese\*.class build\fr\iutfbleau\sae31\outil\*.class synthese.jar outil.jar