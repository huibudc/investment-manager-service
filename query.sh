progress=$(ps -ef | grep investment-manager-service | head -1)
result=$(echo $progress | grep 'java -jar')
if [[ $result != "" ]]
then
  progressId=$(echo $result | cut -d' ' -f 2)
  echo "Service is running, progressId=${progressId}"
else
  echo 'No progress exist.'
fi