#javac ./src/AntFSM.java
#mv ./src/AntFSM.class .
#javac -cp ./bin/junit-platform-console-standalone-1.8.2.jar ./src/FSMUnitTest.java
#mv ./src/*.class ./bin
#START=$(date +%s%N)

java -jar ./bin/junit-platform-console-standalone-1.8.2.jar \
	 -cp ".:./bin" \
	 -c=FSMUnitTest \
	 --details none \
	 --reports-dir="reports" \
	 --disable-banner \
	 --fail-if-no-tests | less

#RUNTIME=$((($(date +%s%N) - $START)/1000000))
#echo "Time taken: $RUNTIME milliseconds"