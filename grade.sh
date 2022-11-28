# Create your grading script here

rm -rf student-submission
git clone $1 student-submission
echo 'Finished cloning'

cp TestListExamples.java student-submission
cp -R lib student-submission

cd student-submission

if [[ -e ListExamples.java ]]
then  
  javac -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar *.java 2> error.txt
else
  echo "wrong file submitted"
  exit 1
fi

[ -s error.txt ]

if [[ $? -eq 0 ]]
then
  echo "compile error"
  exit 1
else
  java -cp .:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar org.junit.runner.JUnitCore TestListExamples > output.txt
fi

filter_tests=$(grep -c "testFilter" TestListExamples.java)
merge_tests=$(grep -c "testMerge" TestListExamples.java)

filter_failed=$(grep -c "TestListExamples.testFilter" output.txt)
merge_failed=$(grep -c "TestListExamples.testMerge" output.txt)

filter_passed=$(echo "$filter_tests-$filter_failed" | bc)
merge_passed=$(echo "$merge_tests-$merge_failed" | bc)

filter_failed_tests=$(grep " testFilter" output.txt)
merge_failed_tests=$(grep " testMerge" output.txt)

echo "Passed $filter_passed out of $filter_tests test for filter() method!"
echo "Failed $filter_failed out of $filter_tests test for filter() method!"
if ! [[ filter_failed -eq 0 ]]
then
  echo "The failed filter tests are"
  echo "$filter_failed_tests"
fi

echo "Passed $merge_passed out of $merge_tests test for merge() method!"
echo "Failed $merge_failed out of $merge_tests test for merge() method!"
if ! [[ merge_failed -eq 0 ]]
then
  echo "The failed merge tests are"
  echo "$merge_failed_tests"
fi