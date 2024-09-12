javac src/main/java/nsu.chebotareva/HeapSort.java -d ./build
javadoc -d build/docs/javadoc -sourcepath src/main/java -subpackages nsu.chebotareva
jar -cfm HeapSort.jar META-INF/MANIFEST.MF src/main/java/nsu.chebotareva/HeapSort.class -d ./build
java -cp ./build nsu.chebotareva.HeapSort