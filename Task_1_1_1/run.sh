javac src/main/java/nsu_chebotareva/HeapSort.java -d ./build
javadoc -d build/docs/javadoc -sourcepath src/main/java -subpackages nsu_chebotareva
jar -cfm HeapSort.jar META-INF/MANIFEST.MF src/main/java/nsu_chebotareva/HeapSort.class -d ./build
java -cp ./build nsu_chebotareva.HeapSort