# Graphmetriken Klassenbibliothek
Dieses Repository beinhaltet eine Klassenbibliothek zur Erstellung von Graphen und zur Berechnung von Graphmetriken (Graph-Kennzahlen).

Die Klassenbilbiothek ist im Rahmen einer Studienarbeit der DBHW Stuttgart Campus Horb entstanden.
## Technologie
Das gewählte Build-Tool ist Maven und die Bibliothek wurde mittels der Sprache Java umgesetzt
## Funktionen
* Bereitstellung einer Graph-Interfaces und bestimmte Graph-Implementierungen
* Bereitstellung einer Schnittstelle zur Berechnung diverser Metriken für Graph, Knoten in Graph und Knoten zu Knoten
## Voraussetzungen
Um die Bibliothek verwenden zu können, müssen folgende Prämissen gegeben sein:
* Installiertes Java Development Kit (mit Java Runtime Enviroment) der Version 13 oder höher (OpenJDK empfohlen)
* Maven installiert <https://maven.apache.org/>
## Verwendung
1. Klonen Sie die Bibliothek mit Git in einen lokalen Ordner
2. Bauen Sie das JAR für die Bibliothek mit
```
mvn clean install
```
3. Legen Sie die JAR, die nun im entstandenden "target"-Ordner liegt, in einen Ornder Ihrer Wahl.
4. In einem neuen Java-Maven-Projekt kann die Bibliothek als folgende Dependency eingebaut werden:
```xml
<dependency>
  <groupId>dhbw.weichselbaum</groupId>
  <artifactId>graph_metrics_class_library</artifactId>
  <version>1.0</version>
  <scope>system</scope>
  <systemPath>'Pfad-zur-JAR'/graph_metrics_class_library-1.0.jar</systemPath>
</dependency>
```
5. Die Bibliothek kann nun im Projekt verwendet werden.
