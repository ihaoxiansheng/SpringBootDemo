# !/bin/bash

# 将当前目录下的jar包名称复制于此处
# 注意！如果修改jar包构建的版本等，及时修改此脚本

APP_NAME=xxxxx.jar
THISDIR=$(dirname $0)

cd $THISDIR
echo "================准备kill当前活跃的进程================"
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo '请求停止 pid: '$tpid
    kill -15 $tpid
fi
sleep 5
tpid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo '请求中断 pid: '$tpid
    kill -9 $tpid
else
    echo '终止成功!'
fi

echo "重启服务中..."
sleep 3
rm -rf logs
nohup java -jar $APP_NAME > out.log 2>&1 &

echo "重启服务完成! Jar包名称:================"$APP_NAME
tail -f out.log
