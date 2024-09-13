javadoc src/main/java/nsu/chebotareva/HeapSort.java -d ./build

if [ $? -ne 0 ]; then
  echo "Javadoc error"
  exit 1
fi

javac src/main/java/nsu/chebotareva/HeapSort.java -d ./build

if [ $? -ne 0 ]; then
  echo "Compilation error"
  exit 2
fi

jar -cfm HeapSort.jar META-INF/MANIFEST.MF build/classes/java/main/nsu/chebotareva/HeapSort.class

if [ $? -ne 0 ]; then
  echo "Jar-file error"
  exit 3
fi

echo "Build success"
exit 0