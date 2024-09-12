all: compile run

compile:
	mkdir -p bin
	javac -d bin src/com/SaborGourmet/*.java

run: 
	java -cp bin SaborGourmet

clean:
	rm -rf bin/*.class
