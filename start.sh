progress=$(ps -ef | grep investment-manager-service | head -1)
result=$(echo $progress | grep 'java -jar investment-manager-service')
if [[ $result != "" ]]
then
  progressId=$(echo $result | cut -d' ' -f 2)
  kill -9 $progressId
fi
nohup java -jar investment-manager-service-1.0-SNAPSHOT.jar > /dev/null 2>&1 &
echo "Service started."