# Create your grading script here

set -e

rm -rf student-submission
git clone $1 student-submission

cp TestListExamples.java student-submission
cp -R lib student-submission

cd student-submission

if [[ -e ListExamples.java ]]
then
  javac -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java 2> error.txt
  java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore TestListExamples > output.txt
  echo error.txt output.txt
else
  echo "wrong file"
  exit 1
fi
