# !/bin/bash

# 如果当前目录下有且仅有一个jar包，则无需修改下方APP_NAME
# 否则，请额外复制脚本，并在APP_NAME处指定当前目录下的jar包名称
# 由于环境限制，请确保当前目录下仅有一个jar包，且linux主机内没有其他同名且同虚拟机配置的jar包正在运行
APP_NAME=`find *.jar |awk '{print $1}'`
APP_PATH=`pwd`
JVM_OPT_S='Xms500m'
JVM_OPT_M='Xmx1500m'

echo "此脚本可用来启动亦或重启jar包"
echo "当前工作空间: "$APP_PATH
echo "结束活跃进程: "$APP_NAME
tpid=`ps -ef|grep $APP_NAME |grep $JVM_OPT_S|grep $JVM_OPT_M|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo '请求停止 pid: '$tpid
    kill -15 $tpid
fi
sleep 5
tpid=`ps -ef|grep $APP_NAME |grep $JVM_OPT_S|grep $JVM_OPT_M|grep -v grep|grep -v kill|awk '{print $2}'`
if [ ${tpid} ]; then
    echo '请求中断 pid: '$tpid
    kill -9 $tpid
else
    echo '终止成功!'
fi

echo "重启服务..."
sleep 3
rm -rf out.log
nohup java -jar -$JVM_OPT_S -$JVM_OPT_M $APP_NAME  >out.log 2>&1 &

echo "重启完成!"

tail -f out.log
